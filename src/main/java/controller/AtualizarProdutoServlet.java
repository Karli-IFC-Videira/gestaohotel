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

@WebServlet("/atualizar-produto")
public class AtualizarProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AtualizarProdutoServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String produto = request.getParameter("produto");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double precoCompra = Double.parseDouble(request.getParameter("preco_compra"));
        double precoVenda = Double.parseDouble(request.getParameter("preco_venda"));
        String marca = request.getParameter("marca");

        try (Connection conn = ConexaoDB.getConexao()) {
            String sql = "UPDATE produtos SET nomeProduto = ?, quantidade = ?, precoCompra = ?, precoVenda = ?, marca = ? WHERE codigo_Produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto);
            stmt.setInt(2, quantidade);
            stmt.setDouble(3, precoCompra);
            stmt.setDouble(4, precoVenda);
            stmt.setString(5, marca);
            stmt.setInt(6, Integer.parseInt(codigo)); // Código do produto como condição

            stmt.executeUpdate();
            response.setStatus(HttpServletResponse.SC_OK); // Retorna um status de sucesso
            response.getWriter().write("Produto atualizado com sucesso"); // Mensagem opcional

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Retorna um erro se falhar
        }
    }
}