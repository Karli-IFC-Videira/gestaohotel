package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class ServletSistema
 */
@WebServlet(name = "ServletSistema", urlPatterns = {"/ServletSistema", "/index", "/sobre", "/pacotes", "/contato", "/login"})
public class ServletSistema extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Mapeamento de rotas para páginas JSP
    private Map<String, String> routes;

    public ServletSistema() {
        super();
        
        // Inicializa as rotas no construtor
        routes = new HashMap<>();
        routes.put("/index", "index.jsp");
        routes.put("/sobre", "sobre-o-hotel.jsp");
        routes.put("/pacotes", "pacotes.jsp");
        routes.put("/contato", "contato.jsp");
        routes.put("/login", "login.jsp");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        // Pega a página correspondente da rota
        String pagina = routes.get(action);

        if (pagina != null) {
            // Encaminha a requisição para a página JSP sem redirecionar
            request.getRequestDispatcher(pagina).forward(request, response);
        } else {
            // Caso a rota não seja encontrada, retorna erro 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada.");
        }
    }
}