package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.VendaRequest;
import model.VendasItem;
import dao.VendasDAO;
import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;

/**
 * Servlet implementation class ServletCadastroVendas
 */
@WebServlet(name = "ServletCadastroVendas", urlPatterns = {"/cadastrar-venda"})
public class ServletCadastroVendas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lendo os dados enviados em JSON
        StringBuilder requestData = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                requestData.append(line);
            }
        }

        // Converter os dados JSON para o objeto VendaRequest
        Gson gson = new Gson();
        VendaRequest vendaRequest = gson.fromJson(requestData.toString(), VendaRequest.class);

        // Verificando se o totalVenda e os itens são válidos
        if (vendaRequest.getTotalVenda() <= 0 || vendaRequest.getItems() == null || vendaRequest.getItems().isEmpty()) {
            sendJsonResponse(response, "Dados de venda inválidos.", "error");
            return;
        }

        // Verificando se o usuário está logado
        String nomeUsuarioLogado = (String) request.getSession().getAttribute("usuario");
        if (nomeUsuarioLogado == null || nomeUsuarioLogado.isEmpty()) {
            sendJsonResponse(response, "Erro: usuário não está logado.", "error");
            return;
        }

        // Conectar ao DAO e realizar a inserção
        VendasDAO vendaDAO = new VendasDAO();

        try {
            // Iniciar transação no banco
            vendaDAO.iniciarTransacao();

            // Inserir a venda na tabela `vendas` (certifique-se de incluir todos os campos obrigatórios)
            int codigoVenda = vendaDAO.cadastrarVenda(nomeUsuarioLogado, vendaRequest.getTotalVenda(), vendaRequest.getItems().get(0).getCodigoProduto());

            // Inserir os itens da venda na tabela `vendas_itens` e atualizar o estoque
            for (VendasItem item : vendaRequest.getItems()) {
                item.setNomeLogin(nomeUsuarioLogado); // Definindo o nome do usuário logado no item
                vendaDAO.cadastrarItemVenda(codigoVenda, item);
                vendaDAO.atualizarEstoqueProduto(item.getCodigoProduto(), item.getQuantidade());
            }

            // Se tudo ocorrer bem, fazer commit da transação
            vendaDAO.confirmarTransacao();

            // Resposta de sucesso
            sendJsonResponse(response, "Venda cadastrada com sucesso.", "success");

        } catch (Exception e) {
            // Se ocorrer qualquer erro, fazer rollback e retornar mensagem de erro
            try {
                vendaDAO.cancelarTransacao();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }

            sendJsonResponse(response, "Erro: " + e.getMessage(), "error");
            e.printStackTrace();
        }
    }

    // Método para enviar uma resposta JSON personalizada
    private void sendJsonResponse(HttpServletResponse response, String message, String status) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"" + status + "\", \"message\": \"" + message + "\"}");
    }
}