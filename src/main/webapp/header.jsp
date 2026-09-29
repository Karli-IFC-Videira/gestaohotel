<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Hotel Scalabrin - O melhor lugar para sua hospedagem em Fraiburgo, Santa Catarina.">
<meta name="author" content="Hotel Scalabrin">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="icon" href="imagens/hotel.png" type="image/x-icon">
<title>Hotel Scalabrin - O melhor lugar para sua hospedagem em Fraiburgo</title>
<script src="js/MenuResponsivo.js"></script>
<link rel="stylesheet" href="estilos/estilo-index.css">
<link rel="stylesheet" href="estilos/estilo-pacotes.css">
<link rel="stylesheet" href="estilos/estilo-contato.css">
<link rel="stylesheet" href="estilos/estilo-sobre-o-hotel.css">
<link rel="stylesheet" href="estilos/estilo-login.css">
<link rel="stylesheet" href="estilos/estilo-login-paisagem.css">
<link rel="stylesheet" href="estilos/sweetalert.css">
</head>
<body>
<header id="header" role="banner">
    <div class="logo">
        <img src="imagens/hotel.png" alt="Logo do Hotel Scalabrin" />
    </div>
    <nav id="nav" role="navigation">
        <button aria-label="Abrir Menu" id="btn-mobile" aria-haspopup="true" aria-controls="menu"
            aria-expanded="false">Menu
            <span id="hamburger"></span>
        </button>
        <ul id="menu" role="menu">
            <li><a class="opcoes" href="index">Inicio</a></li>
            <li><a class="opcoes" href="sobre">Sobre o Hotel</a></li>
            <li><a class="opcoes" href="pacotes">Pacotes</a></li>
            <li><a class="opcoes" href="contato">Contacta-nos</a></li>
            <li><a class="reservacao" href="login">Login</a></li>
        </ul>
    </nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>