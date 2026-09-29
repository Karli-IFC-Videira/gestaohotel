package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import dao.ConexaoDB;
import model.Produto; // Classe Produto que representa os dados do produto

@WebServlet("/buscar-produtos")
public class ServletAdicionarProduto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoProduto = request.getParameter("codigoProduto");

        if (codigoProduto != null && !codigoProduto.isEmpty()) {
            try (Connection conn = ConexaoDB.getConexao()) {
                // Consulta para buscar o produto pelo código
                String sql = "SELECT codigo_Produto, nomeProduto, precoVenda FROM produtos WHERE codigo_Produto = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(codigoProduto));

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Criando o objeto Produto com os dados obtidos do banco
                    Produto produto = new Produto();
                    produto.setCodigoProduto(rs.getInt("codigo_Produto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setPrecoVenda(rs.getDouble("precoVenda"));

                    // Enviando a resposta em JSON com os dados necessários
                    Map<String, Object> responseData = new HashMap<>();
                    responseData.put("codigoProduto", produto.getCodigoProduto());
                    responseData.put("nomeProduto", produto.getNomeProduto());
                    responseData.put("precoVenda", produto.getPrecoVenda());

                    String jsonResponse = new Gson().toJson(responseData);
                    response.setContentType("application/json");
                    response.getWriter().write(jsonResponse);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Produto não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar o produto.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Código do produto não fornecido.");
        }
    }
}