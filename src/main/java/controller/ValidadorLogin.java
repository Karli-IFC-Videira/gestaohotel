package controller;

import java.io.IOException;
import dao.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet(name = "ValidadorLogin", urlPatterns = {"/controlador-login"})
public class ValidadorLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ValidadorLogin() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeLogin = request.getParameter("txtUsuario");
        String senha = request.getParameter("txtSenha");

        if (nomeLogin == null || nomeLogin.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "O campo de usuário está vazio!");
            response.sendRedirect("login");
            return;
        }

        if (senha == null || senha.isEmpty()) {
            request.getSession().setAttribute("errorMessage", "O campo de senha está vazio!");
            request.getSession().setAttribute("usuario", nomeLogin);  // Mantém o nome do usuário no formulário
            response.sendRedirect("login");
            return;
        }

        LoginDAO loginDAO = new LoginDAO();
        Usuario usuario = null;

        try {
            usuario = loginDAO.login(nomeLogin, senha);
            if (usuario != null) {
                String nivelAcesso = usuario.getNivelAcesso() == null
                        ? ""
                        : usuario.getNivelAcesso().trim();
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario.getNomeLogin());
                session.setAttribute("nivelAcesso", nivelAcesso);
             // Adiciona o CPF do usuário na sessão
                request.getSession().setAttribute("cpfUsuario", usuario.getCpfUsuario());

                // Redireciona baseado no cargo
                if ("Administrador".equalsIgnoreCase(nivelAcesso)) {
                    response.sendRedirect("cadastro-hospedagens");
                } else if ("Atendente".equalsIgnoreCase(nivelAcesso)) {
                    response.sendRedirect("cadastro-hospedagens");
                }
            } else {
                request.getSession().setAttribute("errorMessage", "Usuário ou senha inválidos!");
                request.getSession().setAttribute("usuario", nomeLogin);
                response.sendRedirect("login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Erro no sistema. Tente novamente mais tarde.");
            request.getSession().setAttribute("usuario", nomeLogin);
            response.sendRedirect("login");
        }
    }
}