package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.Gson;
import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

@WebServlet("/buscar-produto")
public class BuscarProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BuscarProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null || id.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("ID não fornecido");
			return;
		}

		Produto produto = null;

		try (Connection conn = ConexaoDB.getConexao()) {
			String sql = "SELECT codigo_Produto, nomeProduto, quantidade, precoCompra, precoVenda, marca, nomeUsuario FROM produtos WHERE codigo_Produto = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				produto = new Produto();
				produto.setCodigoProduto(rs.getInt("codigo_Produto"));
				produto.setNomeProduto(rs.getString("nomeProduto"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPrecoCompra(rs.getDouble("precoCompra"));
				produto.setPrecoVenda(rs.getDouble("precoVenda"));
				produto.setMarca(rs.getString("marca"));
				produto.setNomeUsuario(rs.getString("nomeUsuario"));
				// Adicione outros campos conforme necessário
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(produto)); // Usando Gson para converter objeto para JSON
	}
}