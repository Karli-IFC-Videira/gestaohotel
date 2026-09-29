package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {
	
	// Método para verificar se o CPF já está cadastrado
    public boolean cpfJaCadastrado(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE cpf_Usuario = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Retorna true se o CPF já existe
            }
        }
        return false;
    }

    // Método para verificar se o email já está cadastrado
    public boolean emailJaCadastrado(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // Retorna true se o email já existe
            }
        }
        return false;
    }
    
    public boolean celularJaCadastrado(String celular) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE celular = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, celular);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Se o resultado for maior que 0, o celular já está cadastrado
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao verificar celular: " + e.getMessage());
        }
        return false;
    }
	
    public void inserir(Usuario usuario) throws SQLException {
    	
    	String insercao = "INSERT INTO usuario (cpf_Usuario, nomeUsuario, sobrenomeUsuario, dataNascimento, nivelAcesso, email, celular, nomeLogin, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexaoDB.getConexao();
             PreparedStatement stmt = connection.prepareStatement(insercao)) {
            stmt.setString(1, usuario.getCpfUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getSobrenomeUsuario());
            stmt.setString(4, usuario.getDataNascimento());
            stmt.setString(5, usuario.getNivelAcesso());
            stmt.setString(6, usuario.getEmail());
            stmt.setString(7, usuario.getCelular());
            stmt.setString(8, usuario.getNomeLogin());
            stmt.setString(9, usuario.getSenha());

            stmt.executeUpdate();
        }
    }
}