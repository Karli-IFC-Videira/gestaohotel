package dao;

import java.sql.*;
import model.VendasItem;

public class VendasDAO {

    private Connection connection;

    public VendasDAO() {
        // Obtém a conexão com o banco de dados
        try {
			this.connection = ConexaoDB.getConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Iniciar transação
    public void iniciarTransacao() throws SQLException {
        connection.setAutoCommit(false);
    }

    // Confirmar transação
    public void confirmarTransacao() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    // Cancelar transação
    public void cancelarTransacao() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    // Cadastrar venda na tabela `vendas`
    public int cadastrarVenda(String nomeUsuario, double valorVenda, int codigo_Produto) throws SQLException {
        String sql = "INSERT INTO vendas (valorVenda, dataVenda, nomeUsuario, codigo_Produto) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, valorVenda);  // Valor total da venda
            stmt.setString(2, nomeUsuario);  // Nome do usuário logado (nomeLogin)
            stmt.setInt(3, codigo_Produto);   // Código do produto
            stmt.executeUpdate();

            // Obtém o código da venda gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  // Código da venda gerado automaticamente
            } else {
                throw new SQLException("Erro ao gerar código da venda.");
            }
        }
    }

    // Cadastrar item na tabela `vendas_itens`
    public void cadastrarItemVenda(int codigoVenda, VendasItem item) throws SQLException {
        String sql = "INSERT INTO vendas_itens (codigo_Venda, codigo_Produto, nomeProduto, quantidade_Itens, subtotal, valorVenda, nomeUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigoVenda);
            stmt.setInt(2, item.getCodigoProduto());
            stmt.setString(3, item.getNomeProduto());
            stmt.setInt(4, item.getQuantidade());
            stmt.setDouble(5, item.getSubtotal());
            stmt.setDouble(6, item.getValorVenda());          
            stmt.setString(7, item.getNomeLogin());
            stmt.executeUpdate();
        }
    }

    // Atualizar o estoque de produto na tabela `produtos`
    public void atualizarEstoqueProduto(int codigo_Produto, int quantidade) throws SQLException {
        String sql = "UPDATE produtos SET quantidade = quantidade - ? WHERE codigo_Produto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);  // Subtrai a quantidade vendida
            stmt.setInt(2, codigo_Produto);
            
            // Executa a atualização
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated == 0) {
                throw new SQLException("Estoque insuficiente para o produto com código " + codigo_Produto);
            }
        }
    }
}