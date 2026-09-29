package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produtos";

        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

        	while (rs.next()) {
        	    Produto produto = new Produto();
        	    produto.setNomeProduto(rs.getString("nomeProduto"));
        	    produto.setQuantidade(rs.getInt("quantidade"));
        	    produto.setPrecoCompra(rs.getDouble("precoCompra"));
        	    produto.setPrecoVenda(rs.getDouble("precoVenda"));
        	    produto.setMarca(rs.getString("marca"));
        	    produto.setNomeUsuario(rs.getString("nomeUsuario"));
        	    produtos.add(produto);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }
    
    public Produto buscarProdutoPorCodigo(int codigoProduto) {
        Produto produto = null;
        // A consulta já está correta, pois usa codigo_Produto
        String query = "SELECT * FROM produtos WHERE codigo_Produto = ?";

        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, codigoProduto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    // Aqui você deve usar o nome correto da coluna: 'codigo_Produto'
                    produto.setCodigoProduto(rs.getInt("codigo_Produto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setPrecoCompra(rs.getDouble("precoCompra"));
                    produto.setPrecoVenda(rs.getDouble("precoVenda"));
                    produto.setMarca(rs.getString("marca"));
                    produto.setNomeUsuario(rs.getString("nomeUsuario"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }
    
    public boolean verificarEstoque(int codigoProduto, int quantidadeSolicitada) {
        String query = "SELECT quantidade FROM produtos WHERE codigo_Produto = ?";
        boolean estoqueSuficiente = false;

        try (Connection con = ConexaoDB.getConexao();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, codigoProduto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int quantidadeDisponivel = rs.getInt("quantidade");
                    estoqueSuficiente = quantidadeSolicitada <= quantidadeDisponivel;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estoqueSuficiente;
    }
}