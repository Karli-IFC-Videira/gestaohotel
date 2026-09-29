package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Quartos;

public class QuartosDAO {
    private Connection connection;

    public QuartosDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para obter os tipos de quartos distintos
    public List<String> obterTiposDeQuartos() throws SQLException {
        List<String> tiposQuartos = new ArrayList<>();
        String sql = "SELECT DISTINCT tipoQuarto FROM quartos";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tiposQuartos.add(rs.getString("tipoQuarto"));
            }
        }
        return tiposQuartos;
    }

    // Método para obter quartos disponíveis por tipo
    public List<Quartos> obterQuartosDisponiveis(String tipoQuarto) throws SQLException {
        List<Quartos> quartosDisponiveis = new ArrayList<>();
        String sql = "SELECT numero_Quarto, estatus FROM quartos WHERE tipoQuarto = ? AND estatus = 'Disponível'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipoQuarto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quartos quarto = new Quartos();
                quarto.setNumeroQuarto(rs.getInt("numero_Quarto"));
                quarto.setEstatus(rs.getString("estatus"));
                quartosDisponiveis.add(quarto);
            }
        }
        return quartosDisponiveis;
    }
    
 // Método para obter tipos de quartos reservados
    public List<String> obterTiposDeQuartosReservados() throws SQLException {
        List<String> tiposQuartos = new ArrayList<>();
        String sql = "SELECT DISTINCT tipoQuartoReservado FROM hospedagens_reservas";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tiposQuartos.add(rs.getString("tipoQuartoReservado"));
            }
        }
        return tiposQuartos;
    }
}