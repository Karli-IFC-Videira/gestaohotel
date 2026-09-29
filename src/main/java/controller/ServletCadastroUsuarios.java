package controller;

import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet(name = "ServletCadastroUsuarios", urlPatterns = {"/controlador-cadastros"})
public class ServletCadastroUsuarios extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCadastroUsuarios() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
        	// Captura os parâmetros
            String cpf_Usuario = request.getParameter("cpfUsuario");
            
            // Remove pontos e traço do CPF
            cpf_Usuario = cpf_Usuario.replaceAll("\\D", ""); // Mantém apenas os números
            
            // Captura dos parâmetros
            String cpfUsuario = request.getParameter("cpfUsuario");
            String nomeUsuario = request.getParameter("nomeUsuario");
            String sobrenomeUsuario = request.getParameter("sobrenomeUsuario");
            String dataNascimentoStr = request.getParameter("dataNascimento");
            String nivelAcesso = request.getParameter("nivelAcesso");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            String nomeLogin = request.getParameter("nomeLogin");
            String senha = request.getParameter("senha");

            // Validação básica
            if (cpfUsuario == null || nomeUsuario == null || sobrenomeUsuario == null || 
                dataNascimentoStr == null || nivelAcesso == null || email == null || 
                celular == null || nomeLogin == null || senha == null) {

                // Mensagem de erro de campos obrigatórios
                request.setAttribute("status", "error");
                request.setAttribute("message", "Todos os campos são obrigatórios.");

                // Preserva os dados no formulário
                preserveFormData(request, cpf_Usuario, nomeUsuario, sobrenomeUsuario, dataNascimentoStr, nivelAcesso, email, celular, nomeLogin);

                request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
                return;
            }

            // Instância do DAO
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Verifica se o CPF já está cadastrado
            if (usuarioDAO.cpfJaCadastrado(cpf_Usuario)) {
                request.setAttribute("status", "error");
                request.setAttribute("message", "O CPF informado já está cadastrado.");
                
                // Preserva os dados no formulário
                preserveFormData(request, cpf_Usuario, nomeUsuario, sobrenomeUsuario, dataNascimentoStr, nivelAcesso, email, celular, nomeLogin);

                request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
                return;
            }

            // Verifica se o email já está cadastrado
            if (usuarioDAO.emailJaCadastrado(email)) {
                request.setAttribute("status", "error");
                request.setAttribute("message", "O email informado já está cadastrado.");
                
                // Preserva os dados no formulário
                preserveFormData(request, cpf_Usuario, nomeUsuario, sobrenomeUsuario, dataNascimentoStr, nivelAcesso, email, celular, nomeLogin);

                request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
                return;
            }
            
         // Verifica se o celular já está cadastrado
            if (usuarioDAO.celularJaCadastrado(celular)) {
                request.setAttribute("status", "error");
                request.setAttribute("message", "Celular já cadastrado!");
                
                // Preserva os dados no formulário
                preserveFormData(request, cpf_Usuario, nomeUsuario, sobrenomeUsuario, dataNascimentoStr, nivelAcesso, email, celular, nomeLogin);
               
                request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
                return;
            }

            // Hash da senha usando BCrypt
            String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());

            // Criação do objeto Usuario
            Usuario usuario = new Usuario(cpf_Usuario, nomeUsuario, sobrenomeUsuario, dataNascimentoStr, nivelAcesso, email, celular, nomeLogin, senhaHash);

            // Inserção usuário no banco de dados
            usuarioDAO.inserir(usuario);

            // Mensagem de sucesso
            request.setAttribute("status", "success");
            request.setAttribute("message", "Usuário cadastrado com sucesso!");
            request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);

        } catch (SQLException e) {
            // Mensagem de erro para dados duplicados
            request.setAttribute("status", "error");
            request.setAttribute("message", "Erro de banco de dados: " + e.getMessage());

            // Preserva os dados no formulário em caso de erro
            preserveFormData(request, request.getParameter("cpfUsuario"), request.getParameter("nomeUsuario"), request.getParameter("sobrenomeUsuario"), 
                request.getParameter("dataNascimento"), request.getParameter("nivelAcesso"), request.getParameter("email"), request.getParameter("celular"), request.getParameter("nomeLogin"));

            request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("status", "error");
            request.setAttribute("message", "Erro: " + e.getMessage());
            request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            request.setAttribute("message", "Erro inesperado: " + e.getMessage());
            request.getRequestDispatcher("/cadastro-usuarios.jsp").forward(request, response);
        }
    }

    private void preserveFormData(HttpServletRequest request, String cpf, String nome, String sobrenome, String dataNascimento, String nivelAcesso, String email, String celular, String nomeLogin) {
        request.setAttribute("cpfUsuario", cpf);
        request.setAttribute("nomeUsuario", nome);
        request.setAttribute("sobrenomeUsuario", sobrenome);
        request.setAttribute("dataNascimento", dataNascimento);
        request.setAttribute("nivelAcesso", nivelAcesso);
        request.setAttribute("email", email);
        request.setAttribute("celular", celular);
        request.setAttribute("nomeLogin", nomeLogin);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Método não permitido");
    }
}