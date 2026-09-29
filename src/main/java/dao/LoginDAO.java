package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDAO {

    public Usuario login(String nomeLogin, String senha) {
        Usuario usuario = null;
        String consulta = "SELECT * FROM usuario WHERE nomeLogin = ?"; // Não verificar a senha aqui

        try (Connection conect = ConexaoDB.getConexao();
             PreparedStatement ps = conect.prepareStatement(consulta)) {
             
            ps.setString(1, nomeLogin);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedSenha = rs.getString("senha"); // Obtenha a senha hash do banco
                    if (BCrypt.checkpw(senha, hashedSenha)) { // Verifique a senha
                        usuario = new Usuario();
                        usuario.setNomeLogin(rs.getString("nomeLogin"));
                        usuario.setNivelAcesso(rs.getString("nivelAcesso")); // Adiciona a recuperação do nível de acesso
                        // Adicionar mais campos aqui conforme necessário
                        usuario.setCpfUsuario(rs.getString("cpf_Usuario"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario; // Retorna o objeto Usuario ou null
    }
}