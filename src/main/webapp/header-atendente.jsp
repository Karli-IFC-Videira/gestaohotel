<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description"
        content="Hotel Scalabrin - O melhor lugar para sua hospedagem em Fraiburgo, Santa Catarina.">
    <meta name="author" content="Hotel Scalabrin">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/inputmask/5.0.6/jquery.inputmask.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="icon" href="imagens/hotel.png" type="image/x-icon">
    <title>Hotel Scalabrin - O melhor lugar para sua hospedagem em Fraiburgo</title>
    <link rel="stylesheet" href="estilos/estilo-header-principal.css">
</head>

<body>
    <header id="header-menu" role="banner">
        <div class="logo">
            <img src="imagens/hotel.png" alt="Logo do Hotel Scalabrin" />
        </div>
        <nav id="nav" role="navigation">
            <button aria-label="Abrir Menu" id="btn-mobile" aria-haspopup="true" aria-controls="menu"
                aria-expanded="false">
                Menu <span id="hamburger"></span>
            </button>

            <ul id="menu-admin" role="menu" class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="icones" alt="Icone Cadastros" src="imagens/icone-cadastrar.webp" width="30"
                            height="30" /> Cadastros
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="cadastro-hospedagens">Hospedagens/Reservas</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="icones" alt="Icone Vendas" src="imagens/icone-vendas.webp" width="30" height="30" />
                        Vendas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <li><a class="dropdown-item" href="cadastro-vendas">Vender Produto</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="icones" alt="Icone Consultas" src="imagens/icone-consultar.webp" width="30"
                            height="30" /> Consultas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <li><a class="dropdown-item" href="consulta-hospedes">Hospedes</a></li>
                        <li><a class="dropdown-item" href="cadastro-hospedagens">Hospedagens/Reservas</a></li>
                        <li><a class="dropdown-item" href="consulta-reservas">Quartos disponíveis</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown5" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="icones" alt="Icone Sair" src="imagens/icone-sair.webp" width="30" height="30" /> Sair
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown5">
                        <li><a class="dropdown-item" id="saida" href="#"><strong>${nivelAcesso}</strong></a></li>
                        <li><a class="dropdown-item" id="saida" href="#">${usuario}</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" id="saida" href="login.jsp">Sair</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/MenuResponsivo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>