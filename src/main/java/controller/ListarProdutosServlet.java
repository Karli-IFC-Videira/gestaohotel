package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

@WebServlet("/listar-produtos")
public class ListarProdutosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListarProdutosServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Produto> produtos = new ArrayList<>();

		try (Connection conn = ConexaoDB.getConexao()) {
			String sql = "SELECT codigo_Produto, nomeProduto, quantidade, precoCompra, precoVenda, marca, nomeUsuario FROM produtos";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setCodigoProduto(rs.getInt("codigo_Produto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPrecoCompra(rs.getDouble("precoCompra"));
				produto.setPrecoVenda(rs.getDouble("precoVenda"));
				produto.setMarca(rs.getString("marca"));
				produto.setNomeUsuario(rs.getString("nomeUsuario"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(produtos)); // Usando Gson para converter a lista para JSON
	}
}