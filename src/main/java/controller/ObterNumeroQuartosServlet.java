package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dao.ConexaoDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ObterNumeroQuartosServlet", urlPatterns = {"/obter-numero-quartos"})
public class ObterNumeroQuartosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura o tipo de conteúdo para JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Obtém o parâmetro 'tipoQuarto' da requisição
        String tipoQuarto = request.getParameter("tQuarto");

        // Busca no banco de dados com base no tipo de quarto
        List<Integer> numerosQuartos = obterNumerosDeQuartos(tipoQuarto);

        // Converter a lista de números de quartos para JSON
        String json = new Gson().toJson(numerosQuartos);

        // Enviar a resposta JSON para o cliente
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    // Método que simula a busca de números de quartos no banco de dados
    private List<Integer> obterNumerosDeQuartos(String tipoQuarto) {
        List<Integer> numeros = new ArrayList<>();
        String sql = "SELECT numero_Quarto FROM quartos WHERE tipoQuarto = ? AND estatus = 'Disponível'";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoQuarto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numeros.add(rs.getInt("numero_Quarto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return numeros;
    }
}