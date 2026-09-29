package controller;

import java.io.IOException;
import com.google.gson.JsonObject;
import dao.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

@WebServlet(name = "ServletVerificarEstoque", urlPatterns = {"/verificar-estoque"})
public class ServletVerificarEstoque extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        // Obtém os parâmetros enviados pelo AJAX
        String codigoProdutoStr = request.getParameter("codigoProduto");
        String quantidadeStr = request.getParameter("quantidade");

        // Verifica se ambos os parâmetros estão presentes
        if (codigoProdutoStr == null || codigoProdutoStr.isEmpty() || quantidadeStr == null || quantidadeStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"erro\": \"Código do produto e quantidade são obrigatórios.\"}");
            return;
        }

        try {
            int codigoProduto = Integer.parseInt(codigoProdutoStr);
            int quantidadeSolicitada = Integer.parseInt(quantidadeStr);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.buscarProdutoPorCodigo(codigoProduto);

            // Verifica se o produto foi encontrado
            if (produto == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"erro\": \"Produto não encontrado.\"}");
                return;
            }

            // Verifica se o estoque é suficiente
            if (produto.getQuantidade() < quantidadeSolicitada) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"erro\": \"Estoque insuficiente.\"}");
                return;
            }

            // Cria o JSON de resposta com os dados para a tabela de detalhes da venda
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("codigoProduto", produto.getCodigoProduto());
            jsonResponse.addProperty("nomeProduto", produto.getNomeProduto());
            jsonResponse.addProperty("precoVenda", produto.getPrecoVenda());
            jsonResponse.addProperty("quantidade", quantidadeSolicitada); // Retorna apenas a quantidade inserida pelo operador

            // Envia a resposta JSON
            response.getWriter().write(jsonResponse.toString());

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"erro\": \"Parâmetros inválidos. Certifique-se de que o código do produto e a quantidade são números inteiros.\"}");
        }
    }
}