package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ConexaoDB;
import dao.QuartosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ObterTipoQuartosServlet", urlPatterns = {"/obter-tipo-quartos"})
public class ObterTipoQuartosServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ObterTipoQuartosServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Conexão com o banco de dados
        try (Connection connection = ConexaoDB.getConexao()) {
            QuartosDAO quartosDAO = new QuartosDAO(connection);
            List<String> tiposDeQuartos = quartosDAO.obterTiposDeQuartos();

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(tiposDeQuartos));  // Retorna a lista de tipos de quartos como JSON

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}