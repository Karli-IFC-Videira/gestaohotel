package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EstatusQuartosServlet", urlPatterns = {"/obter-status-quartos"})
public class EstatusQuartosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroQuarto = request.getParameter("numeroQuarto");

        // Verifica se o número do quarto foi enviado corretamente
        if (numeroQuarto != null && !numeroQuarto.isEmpty()) {
            try {
                // Query para buscar o status e o valor da diária do quarto
                String query = "SELECT valorDiaria, estatus FROM quartos WHERE numero_Quarto = ?";
                
                Connection conn = ConexaoDB.getConexao(); // Conexão ao banco de dados
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(numeroQuarto));
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    String status = rs.getString("estatus");
                    double valorDiaria = rs.getDouble("valorDiaria");
                    
                    // Retorna JSON com status e valor da diária
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"status\":\"" + status + "\", \"valorDiaria\":\"" + valorDiaria + "\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Quarto não encontrado\"}");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"Erro ao buscar o status\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Número do quarto inválido\"}");
        }
    }
}
