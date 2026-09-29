package controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AutorizacaoPerfilFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String pagina = httpRequest.getServletPath();

        if (!paginaProtegida(pagina)) {
            chain.doFilter(request, response);
            return;
        }

        String nivelAcesso = (String) httpRequest.getSession().getAttribute("nivelAcesso");

        if (nivelAcesso == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        if ("/header-administrador.jsp".equals(pagina) && !"Administrador".equalsIgnoreCase(nivelAcesso.trim())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso permitido somente para administradores.");
            return;
        }

        if ("/header-atendente.jsp".equals(pagina) && !podeAcessarPaginaAtendente(nivelAcesso)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Perfil sem permissao para esta pagina.");
            return;
        }

        if (paginaAdministrativa(pagina) && !"Administrador".equalsIgnoreCase(nivelAcesso.trim())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso permitido somente para administradores.");
            return;
        }

        if (paginaAtendente(pagina) && !podeAcessarPaginaAtendente(nivelAcesso)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Perfil sem permissao para esta pagina.");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean paginaAdministrativa(String pagina) {
        return pagina.contains("cadastro-usuarios")
                || pagina.contains("cadastro-produtos")
                || pagina.contains("consulta-funcionarios")
                || pagina.contains("consulta-vendas")
                || pagina.contains("relatorio-")
                || pagina.contains("CadastroUsuariosServlet")
                || pagina.contains("CadastroProdutoServlet")
                || pagina.contains("ListarProdutosServlet")
                || pagina.contains("BuscarProdutoServlet")
                || pagina.contains("AtualizarProdutoServlet")
                || pagina.contains("ExcluirProdutoServlet")
                || pagina.contains("ServletCadastroUsuarios")
                || pagina.contains("ServletCadastroProdutos")
                || pagina.contains("controlador-cadastros");
    }

    private boolean paginaAtendente(String pagina) {
        return pagina.contains("cadastro-hospedagens")
                || pagina.contains("cadastro-vendas")
                || pagina.contains("consulta-hospedes")
                || pagina.contains("consulta-reservas");
    }

    private boolean podeAcessarPaginaAtendente(String nivelAcesso) {
        return "Administrador".equalsIgnoreCase(nivelAcesso.trim())
            || "Atendente".equalsIgnoreCase(nivelAcesso.trim());
    }

    private boolean paginaProtegida(String pagina) {
        return paginaAdministrativa(pagina)
                || paginaAtendente(pagina)
                || pagina.contains("header-administrador.jsp")
                || pagina.contains("header-atendente.jsp");
    }
}