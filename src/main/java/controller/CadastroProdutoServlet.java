package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastroProdutoServlet", urlPatterns = {"/cadastro-produtos"})
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CadastroProdutoServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redireciona para a página de cadastro de produtos
		request.getRequestDispatcher("cadastro-produtos.jsp").forward(request, response);
	}
}