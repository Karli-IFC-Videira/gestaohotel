package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletCadastroHospedagem", urlPatterns = {"/cadastrar-hospedagens"})
public class ServletCadastroHospedagem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCadastroHospedagem() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recebe os parâmetros do formulário
        String nomeHospede = request.getParameter("nome");
        String sobrenomeHospede = request.getParameter("sobrenome");
        String cpfHospede = request.getParameter("cpfHospede");
        String empresa = request.getParameter("empresa");
        String dReserva = request.getParameter("dReserva");
        String dEntrada = request.getParameter("dEntrada");
        String dSaida = request.getParameter("dSaida");
        String tipoQuartoReservado = request.getParameter("tQuarto");
        int numeroQuarto = Integer.parseInt(request.getParameter("nQuarto"));
        String cpfUsuarioLogado = (String) request.getSession().getAttribute("cpfUsuario");
        
        // Remover caracteres não numéricos do CPF
        cpfHospede = cpfHospede.replaceAll("\\D", "");
        
     // Validação do CPF
        if (cpfHospede.length() != 11) {
            sendJsonResponse(response, "Erro: O campo CPF deve ter 11 dígitos.", "error");
            return;
        }

        // Verifica se o usuário está logado
        if (cpfUsuarioLogado == null || cpfUsuarioLogado.isEmpty()) {
            sendJsonResponse(response, "Erro: usuário não está logado.", "error");
            return;
        }

        String nomeUsuarioLogado = (String) request.getSession().getAttribute("usuario");
        if (nomeUsuarioLogado == null || nomeUsuarioLogado.isEmpty()) {
            sendJsonResponse(response, "Erro: usuário não está logado.", "error");
            return;
        }

        Connection conn = null; 
        try {
            conn = ConexaoDB.getConexao();

            // 1. Verificar se o quarto está disponível
            String sqlQuartoDisponivel = "SELECT estatus, valorDiaria FROM quartos WHERE numero_Quarto = ?";
            BigDecimal valorQuarto = null;
            try (PreparedStatement psCheck = conn.prepareStatement(sqlQuartoDisponivel)) {
                psCheck.setInt(1, numeroQuarto);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next()) {
                    if ("Ocupado".equals(rs.getString("estatus"))) {
                        sendJsonResponse(response, "Erro: quarto já está ocupado.", "error");
                        return;
                    }
                    valorQuarto = rs.getBigDecimal("valorDiaria");
                }
            }

            // 2. Atualizar status do quarto
            String sqlUpdateQuarto = "UPDATE quartos SET estatus = 'Ocupado' WHERE numero_Quarto = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateQuarto)) {
                psUpdate.setInt(1, numeroQuarto);
                psUpdate.executeUpdate();
            }

            // 3. Inserir na tabela hospedagens_reservas com o novo campo tipoQuartoReservado
            String sqlInsertReserva = "INSERT INTO hospedagens_reservas (numero_Quarto, valorQuarto, dataReserva, dataEntrada, dataSaida, tipoQuartoReservado, cpf_Usuario, nomeUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psReserva = conn.prepareStatement(sqlInsertReserva, Statement.RETURN_GENERATED_KEYS)) {
                psReserva.setInt(1, numeroQuarto);
                psReserva.setBigDecimal(2, valorQuarto);
                psReserva.setString(3, dReserva);
                psReserva.setString(4, dEntrada);
                psReserva.setString(5, dSaida);
                psReserva.setString(6, tipoQuartoReservado);
                psReserva.setString(7, cpfUsuarioLogado);
                psReserva.setString(8, nomeUsuarioLogado);
                psReserva.executeUpdate();

                ResultSet rs = psReserva.getGeneratedKeys();
                if (rs.next()) {
                    int codigoReserva = rs.getInt(1);

                    // 4. Inserir na tabela hospedes
                    String sqlInsertHospede = "INSERT INTO hospedes (cpf_hospede, nomeCliente, sobrenomeCliente, empresa, cpf_Usuario, codigo_Reserva, nomeUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement psHospede = conn.prepareStatement(sqlInsertHospede)) {
                        psHospede.setString(1, cpfHospede);
                        psHospede.setString(2, nomeHospede);
                        psHospede.setString(3, sobrenomeHospede);
                        psHospede.setString(4, empresa);
                        psHospede.setString(5, cpfUsuarioLogado);
                        psHospede.setInt(6, codigoReserva);
                        psHospede.setString(7, nomeUsuarioLogado);

                        psHospede.executeUpdate();
                    }
                }
            }

            // Mensagem de sucesso
            sendJsonResponse(response, "Reserva realizada com sucesso!", "success");

        } catch (SQLException e) {
            e.printStackTrace();
            sendJsonResponse(response, "Erro ao realizar a reserva: " + e.getMessage(), "error");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendJsonResponse(HttpServletResponse response, String message, String status) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonResponse = String.format("{\"status\":\"%s\", \"message\":\"%s\"}", status, message);
        response.getWriter().write(jsonResponse);
    }
}