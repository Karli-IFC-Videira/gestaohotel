package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Hospedagem;

public class HospedagemDAO {

    public Hospedagem buscarHospedagemPorId(String idReserva) {
        Hospedagem hospedagem = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoDB.getConexao(); // Implementa sua conexão com o banco
            String sql = "SELECT hr.codigo_Reserva, hr.dataReserva, hr.dataEntrada, hr.dataSaida, " +
                         "h.nomeCliente, h.sobrenomeCliente, h.empresa, h.cpf_hospede, " +
                         "q.numero_Quarto, q.tipoQuarto, q.estatus, hr.valorQuarto " +
                         "FROM hospedagens_reservas hr " +
                         "JOIN hospedes h ON hr.codigo_Reserva = h.codigo_Reserva " +
                         "JOIN quartos q ON hr.numero_Quarto = q.numero_Quarto " +
                         "WHERE hr.codigo_Reserva = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idReserva);
            rs = stmt.executeQuery();

            if (rs.next()) {
                hospedagem = new Hospedagem(
                    rs.getInt("codigo_Reserva"),
                    rs.getObject("dataReserva", LocalDate.class),
                    rs.getObject("dataEntrada", LocalDate.class),
                    rs.getObject("dataSaida", LocalDate.class),
                    rs.getString("nomeCliente"),
                    rs.getString("sobrenomeCliente"),
                    rs.getString("empresa"),
                    rs.getString("cpf_hospede"),
                    rs.getInt("numero_Quarto"),
                    rs.getString("tipoQuarto"),
                    rs.getString("estatus"),
                    rs.getBigDecimal("valorQuarto")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hospedagem;
    }
}