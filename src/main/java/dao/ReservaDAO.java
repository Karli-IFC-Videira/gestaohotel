package dao;

import model.HospedagemReserva;
import model.Hospedes;
import model.Quartos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarHospedagem(HospedagemReserva reserva, Hospedes hospede, Quartos quarto) throws SQLException {
        String sqlReserva = "INSERT INTO hospedagens_reservas (dataReserva, dataEntrada, dataSaida, cpf_Usuario) VALUES (?, ?, ?, ?)";
        String sqlHospede = "INSERT INTO hospedes (cpf_hospede, nomeCliente, sobrenomeCliente, empresa, cpf_Usuario, codigo_Reserva) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlAtualizaQuarto = "UPDATE quartos SET estatus = 'Ocupado' WHERE numero_Quarto = ?";

        try (PreparedStatement stmtReserva = connection.prepareStatement(sqlReserva, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtHospede = connection.prepareStatement(sqlHospede);
             PreparedStatement stmtAtualizaQuarto = connection.prepareStatement(sqlAtualizaQuarto)) {

            // Inserir reserva
            stmtReserva.setDate(1, java.sql.Date.valueOf(reserva.getDataReserva()));
            stmtReserva.setDate(2, java.sql.Date.valueOf(reserva.getDataEntrada()));
            stmtReserva.setDate(3, java.sql.Date.valueOf(reserva.getDataSaida()));
            stmtReserva.setString(4, reserva.getCpfUsuario());
            stmtReserva.executeUpdate();

            // Recuperar o ID gerado da reserva
            try (var generatedKeys = stmtReserva.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int codigoReserva = generatedKeys.getInt(1);
                    // Inserir hospede
                    stmtHospede.setString(1, hospede.getCpfHospede());
                    stmtHospede.setString(2, hospede.getNomeHospede());
                    stmtHospede.setString(3, hospede.getSobrenomeHospede());
                    stmtHospede.setString(4, hospede.getEmpresa());
                    stmtHospede.setString(5, hospede.getCpfUsuario());
                    stmtHospede.setInt(6, codigoReserva);
                    stmtHospede.executeUpdate();

                    // Atualizar status do quarto
                    stmtAtualizaQuarto.setInt(1, quarto.getNumeroQuarto());
                    stmtAtualizaQuarto.executeUpdate();
                }
            }
        }
    }
}