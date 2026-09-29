package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Hospedagem;

@WebServlet(name = "ObterDadosHospedagemServlet", urlPatterns = { "/listar-hospedagens" })
public class ObterDadosHospedagemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hospedagem> hospedagens = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao()) {
        	String sql = "SELECT hr.codigo_Reserva, hr.dataReserva, hr.dataEntrada, hr.dataSaida, " +
                    "       h.nomeCliente, h.sobrenomeCliente, h.empresa, h.cpf_hospede, " +
                    "       hr.numero_Quarto, hr.tipoQuartoReservado, hr.valorQuarto, " +
                    "       q.estatus " +
                    "FROM hospedagens_reservas hr " +
                    "JOIN hospedes h ON hr.codigo_Reserva = h.codigo_Reserva " +
                    "JOIN quartos q ON hr.numero_Quarto = q.numero_Quarto " +
                    "WHERE hr.codigo_Reserva = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                	 Hospedagem hospedagem = new Hospedagem();
                     hospedagem.setCodigoReserva(rs.getInt("codigo_Reserva"));
                     hospedagem.setDataReserva(rs.getDate("dataReserva").toLocalDate());
                     hospedagem.setDataEntrada(rs.getDate("dataEntrada").toLocalDate());
                     hospedagem.setDataSaida(rs.getDate("dataSaida").toLocalDate());
                     hospedagem.setNomeCliente(rs.getString("nomeCliente"));
                     hospedagem.setSobrenomeCliente(rs.getString("sobrenomeCliente"));
                     hospedagem.setCpfHospede(rs.getString("cpf_hospede"));
                     hospedagem.setNumeroQuarto(rs.getInt("numero_Quarto"));
                     hospedagem.setTipoQuartoReservado(rs.getString("tipoQuartoReservado"));
                     hospedagem.setValorQuarto(rs.getBigDecimal("valorQuarto"));
                     hospedagem.setStatusQuarto(rs.getString("estatus"));
                	
                    hospedagens.add(hospedagem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String json = gson.toJson(hospedagens);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}