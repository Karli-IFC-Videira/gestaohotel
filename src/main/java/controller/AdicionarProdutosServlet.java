package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdicionarProdutosServlet", urlPatterns = {"/cadastro-vendas"})
public class AdicionarProdutosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdicionarProdutosServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redireciona para a página de cadastro de hospedagens
		request.getRequestDispatcher("/cadastro-vendas.jsp").forward(request, response);
	}
}