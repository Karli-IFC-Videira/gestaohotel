package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Hospedagem;

@WebServlet(name = "AtualizarHospedagemServlet", urlPatterns = { "/atualizar-hospedagens" })
public class AtualizarHospedagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AtualizarHospedagemServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuilder jsonBuffer = new StringBuilder();
		String line;
		while ((line = request.getReader().readLine()) != null) {
			jsonBuffer.append(line);
		}
		String jsonData = jsonBuffer.toString();

		// Deserializar o JSON
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule()); // Registrar o módulo
		Hospedagem dados = objectMapper.readValue(jsonData, Hospedagem.class);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConexaoDB.getConexao();
			conn.setAutoCommit(false); // Iniciar transação

			// Obtem o número do quarto anterior
			String sqlQuartoAnterior = "SELECT numero_Quarto FROM hospedagens_reservas WHERE codigo_Reserva = ?";
			stmt = conn.prepareStatement(sqlQuartoAnterior);
			stmt.setInt(1, dados.getCodigoReserva());
			rs = stmt.executeQuery();

			int numeroQuartoAnterior = -1;
			if (rs.next()) {
				numeroQuartoAnterior = rs.getInt("numero_Quarto");
			}

			// Atualiza a tabela hospedagens_reservas
			String sqlHospedagem = "UPDATE hospedagens_reservas SET tipoQuartoReservado=?, numero_Quarto=?, valorQuarto=?, dataReserva=?, dataEntrada=?, dataSaida=? WHERE codigo_Reserva=?";
			stmt = conn.prepareStatement(sqlHospedagem);
			stmt.setString(1, dados.getTipoQuartoReservado());
			stmt.setInt(2, dados.getNumeroQuarto());
			stmt.setBigDecimal(3, dados.getValorQuarto());
			stmt.setObject(4, dados.getDataReserva());
			stmt.setObject(5, dados.getDataEntrada());
			stmt.setObject(6, dados.getDataSaida());
			stmt.setInt(7, dados.getCodigoReserva());
			stmt.executeUpdate();

			// Atualiza a tabela hospedes
			String sqlHospede = "UPDATE hospedes SET cpf_hospede=?, nomeCliente=?, sobrenomeCliente=? WHERE codigo_Reserva=?";
			stmt = conn.prepareStatement(sqlHospede);
			stmt.setString(1, dados.getCpfHospede());
			stmt.setString(2, dados.getNomeCliente());
			stmt.setString(3, dados.getSobrenomeCliente());
			stmt.setInt(4, dados.getCodigoReserva());
			stmt.executeUpdate();

			// Atualiza o status do quarto anterior para "Disponível" se ele mudou
			if (numeroQuartoAnterior != -1 && numeroQuartoAnterior != dados.getNumeroQuarto()) {
				String sqlOldRoom = "UPDATE quartos SET estatus='Disponível' WHERE numero_Quarto=?";
				stmt = conn.prepareStatement(sqlOldRoom);
				stmt.setInt(1, numeroQuartoAnterior);
				stmt.executeUpdate();
			}

			// Atualiza o status do novo quarto para "Ocupado"
			String sqlNovoQuarto = "UPDATE quartos SET estatus='Ocupado' WHERE numero_Quarto=?";
			stmt = conn.prepareStatement(sqlNovoQuarto);
			stmt.setInt(1, dados.getNumeroQuarto());
			stmt.executeUpdate();

			conn.commit(); // Confirmar transação
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("Hospedagem atualizada com sucesso.");
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback(); // Reverter transação em caso de erro
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Erro ao atualizar a hospedagem.");
		} finally {
			// Fechar recursos
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}