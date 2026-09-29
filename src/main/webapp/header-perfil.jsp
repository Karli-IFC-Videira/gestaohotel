<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String nivelAcessoHeader = (String) session.getAttribute("nivelAcesso");
    String headerPerfil = "Atendente".equalsIgnoreCase(nivelAcessoHeader == null ? "" : nivelAcessoHeader.trim())
            ? "header-atendente.jsp"
            : "header-administrador.jsp";
%>
<jsp:include page="<%= headerPerfil %>" />