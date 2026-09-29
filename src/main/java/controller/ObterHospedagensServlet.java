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

@WebServlet(name = "ObterHospedagensServlet", urlPatterns = { "/obter-dados-hospedagens" })
public class ObterHospedagensServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reservaId = request.getParameter("id");
        List<Hospedagem> hospedagens = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao()) {
            String sql;
            PreparedStatement ps;

            if (reservaId != null && !reservaId.isEmpty()) {
                // Validação do ID da reserva
                int id;
                try {
                    id = Integer.parseInt(reservaId);
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("ID de reserva inválido.");
                    return;
                }

                sql = "SELECT hr.codigo_Reserva, hr.dataReserva, hr.dataEntrada, hr.dataSaida, h.nomeCliente, " +
                      "h.sobrenomeCliente, h.empresa, h.cpf_hospede, q.numero_Quarto, q.tipoQuarto, q.estatus, hr.valorQuarto " +
                      "FROM hospedagens_reservas hr " +
                      "JOIN hospedes h ON hr.codigo_Reserva = h.codigo_Reserva " +
                      "JOIN quartos q ON hr.numero_Quarto = q.numero_Quarto " +
                      "WHERE hr.codigo_Reserva = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
            } else {
                sql = "SELECT hr.codigo_Reserva, hr.dataReserva, hr.dataEntrada, hr.dataSaida, h.nomeCliente, " +
                      "h.sobrenomeCliente, h.empresa, h.cpf_hospede, q.numero_Quarto, q.tipoQuarto, q.estatus, hr.valorQuarto " +
                      "FROM hospedagens_reservas hr " +
                      "JOIN hospedes h ON hr.codigo_Reserva = h.codigo_Reserva " +
                      "JOIN quartos q ON hr.numero_Quarto = q.numero_Quarto";
                ps = conn.prepareStatement(sql);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hospedagem hospedagem = new Hospedagem(
                            rs.getInt("codigo_Reserva"),
                            rs.getDate("dataReserva").toLocalDate(),
                            rs.getDate("dataEntrada").toLocalDate(),
                            rs.getDate("dataSaida").toLocalDate(),
                            rs.getString("nomeCliente"),
                            rs.getString("sobrenomeCliente"),
                            rs.getString("empresa"),
                            rs.getString("cpf_hospede"),
                            rs.getInt("numero_Quarto"),
                            rs.getString("tipoQuarto"),
                            rs.getString("estatus"),
                            rs.getBigDecimal("valorQuarto")
                    );
                    hospedagens.add(hospedagem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao acessar o banco de dados.");
            return;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        response.setContentType("application/json");

        if (reservaId != null && !reservaId.isEmpty()) {
            if (!hospedagens.isEmpty()) {
                String json = gson.toJson(hospedagens.get(0));
                response.getWriter().write(json);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Reserva não encontrada.");
            }
        } else {
            String json = gson.toJson(hospedagens);
            response.getWriter().write(json);
        }
    }
} 