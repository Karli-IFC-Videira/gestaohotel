package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirHospedagemServlet", urlPatterns = {"/excluir-hospedagens"})
public class ExcluirHospedagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String codigoReserva = request.getParameter("id");
	    
	    if (codigoReserva == null || codigoReserva.isEmpty()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("Código da reserva não informado.");
	        return;
	    }

	    Connection conn = null;
	    PreparedStatement stmtSelectQuarto = null;
	    PreparedStatement stmtDeleteHospede = null;
	    PreparedStatement stmtDeleteReserva = null;
	    PreparedStatement stmtUpdateQuarto = null;
	    String numeroQuarto = null;

	    try {
	        conn = ConexaoDB.getConexao();
	        
	        // Primeiro, obtenha o número do quarto associado à reserva
	        String sqlSelectQuarto = "SELECT numero_Quarto FROM hospedagens_reservas WHERE codigo_Reserva = ?";
	        stmtSelectQuarto = conn.prepareStatement(sqlSelectQuarto);
	        stmtSelectQuarto.setString(1, codigoReserva);
	        ResultSet rs = stmtSelectQuarto.executeQuery();

	        if (rs.next()) {
	            numeroQuarto = rs.getString("numero_Quarto");
	        } else {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            response.getWriter().write("Reserva não encontrada.");
	            return;
	        }

	        // Excluir registro da tabela hospedes
	        String sqlDeleteHospede = "DELETE FROM hospedes WHERE codigo_Reserva = ?";
	        stmtDeleteHospede = conn.prepareStatement(sqlDeleteHospede);
	        stmtDeleteHospede.setString(1, codigoReserva);
	        stmtDeleteHospede.executeUpdate();

	        // Excluir registro da tabela hospedagens_reservas
	        String sqlDeleteReserva = "DELETE FROM hospedagens_reservas WHERE codigo_Reserva = ?";
	        stmtDeleteReserva = conn.prepareStatement(sqlDeleteReserva);
	        stmtDeleteReserva.setString(1, codigoReserva);
	        stmtDeleteReserva.executeUpdate();

	        // Atualizar o status do quarto para 'Disponível'
	        String sqlUpdateQuarto = "UPDATE quartos SET estatus = 'Disponível' WHERE numero_Quarto = ?";
	        stmtUpdateQuarto = conn.prepareStatement(sqlUpdateQuarto);
	        stmtUpdateQuarto.setString(1, numeroQuarto);
	        stmtUpdateQuarto.executeUpdate();

	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().write("Hospedagem e hóspede excluídos e quarto atualizado com sucesso.");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("Erro ao excluir a hospedagem: " + e.getMessage());
	    } finally {
	        // Fechar recursos
	        try { if (stmtSelectQuarto != null) stmtSelectQuarto.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (stmtDeleteHospede != null) stmtDeleteHospede.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (stmtDeleteReserva != null) stmtDeleteReserva.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (stmtUpdateQuarto != null) stmtUpdateQuarto.close(); } catch (SQLException e) { e.printStackTrace(); }
	        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	    }
	}
}