package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CadastroHospedagemServlet", urlPatterns = {"/cadastro-hospedagens"})
public class CadastroHospedagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CadastroHospedagemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redireciona para a página de cadastro de hospedagens
		request.getRequestDispatcher("cadastro-hospedagens.jsp").forward(request, response);
	}
}