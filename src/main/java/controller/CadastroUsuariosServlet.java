package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CadastroUsuariosServlet", urlPatterns = {"/cadastro-usuarios"})
public class CadastroUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public CadastroUsuariosServlet() {
    	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redireciona para a página de cadastro de usuários
        request.getRequestDispatcher("cadastro-usuarios.jsp").forward(request, response);
    }
}