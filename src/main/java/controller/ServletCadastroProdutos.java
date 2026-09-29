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
import model.Produto;

@WebServlet(name = "ServletCadastroProdutos", urlPatterns = {"/cadastro-produtos"})
public class ServletCadastroProdutos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeProduto = request.getParameter("produto");
        String quantidadeStr = request.getParameter("quantidade");
        String precoCompraStr = request.getParameter("preco_compra");
        String precoVendaStr = request.getParameter("preco_venda");
        String marca = request.getParameter("marca");

        // Recuperando o CPF do usuário logado da sessão
        String cpfUsuarioLogado = (String) request.getSession().getAttribute("cpfUsuario");

        // Verificação se o CPF está disponível na sessão
        if (cpfUsuarioLogado == null || cpfUsuarioLogado.isEmpty()) {
            response.sendRedirect("cadastro-produtos.jsp?msg=Erro: usuário não está logado.");
            return;
        }
        
        // Recuperando o nome do usuário logado da sessão
        String nomeUsuarioLogado = (String) request.getSession().getAttribute("usuario");

        // Verificação se o nome está disponível na sessão
        if (nomeUsuarioLogado == null || nomeUsuarioLogado.isEmpty()) {
            response.sendRedirect("cadastro-produtos.jsp?msg=Erro: usuário não está logado.");
            return;
        }

        // Verificações de entrada
        if (nomeProduto == null || nomeProduto.isEmpty() || 
            quantidadeStr == null || quantidadeStr.isEmpty() ||
            precoCompraStr == null || precoCompraStr.isEmpty() || 
            precoVendaStr == null || precoVendaStr.isEmpty() ||
            marca == null || marca.isEmpty()) {

            // Redireciona com a mensagem de erro
            response.sendRedirect("cadastro-produtos.jsp?msg=Todos os campos são obrigatórios!");
            return;
        }

        // Parseando os valores
        int quantidade = 0;
        double precoCompra = 0.0;
        double precoVenda = 0.0;
        
        try {
            quantidade = Integer.parseInt(quantidadeStr);
            precoCompra = Double.parseDouble(precoCompraStr);
            precoVenda = Double.parseDouble(precoVendaStr);
        } catch (NumberFormatException e) {
            // Redireciona se a conversão falhar
            response.sendRedirect("cadastro-produtos.jsp?msg=Valores numéricos inválidos!");
            return;
        }

        // Criando o objeto produto
        Produto produto = new Produto(nomeProduto, quantidade, precoCompra, precoVenda, marca, cpfUsuarioLogado, nomeUsuarioLogado);

        // Salvando no banco de dados
        try (Connection con = ConexaoDB.getConexao(); 
             PreparedStatement stmt = con.prepareStatement("INSERT INTO produtos (nomeProduto, quantidade, precoCompra, precoVenda, marca, cpf_Usuario, nomeUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, produto.getNomeProduto());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPrecoCompra());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setString(5, produto.getMarca());
            stmt.setString(6, cpfUsuarioLogado);
            stmt.setString(7, produto.getNomeUsuario());

            stmt.executeUpdate();
            request.getSession().setAttribute("msg", "Produto cadastrado com sucesso!");
            response.sendRedirect("cadastro-produtos");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("cadastro-produtos.jsp?msg=Erro ao cadastrar produto: " + e.getMessage());
        }
    }
}