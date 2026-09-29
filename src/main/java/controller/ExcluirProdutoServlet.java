package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/excluir-produto")
public class ExcluirProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); // Mudança aqui

        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID não fornecido");
            return;
        }

        try (Connection conn = ConexaoDB.getConexao()) {
            String sql = "DELETE FROM produtos WHERE codigo_Produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Produto não encontrado");
            } else {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Retorna 204 No Content
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir produto");
        }
    }
}