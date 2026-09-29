<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Hotel Scalabrin - O melhor lugar para sua hospedagem em Fraiburgo, Santa Catarina.">
    <meta name="author" content="Hotel Scalabrin">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>	
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">    
    <link href="https://cdn.datatables.net/2.1.7/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/3.1.2/css/buttons.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/fixedheader/4.0.1/css/fixedHeader.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/keytable/2.12.1/css/keyTable.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/responsive/3.0.3/css/responsive.bootstrap5.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="icon" href="imagens/hotel.png" type="image/x-icon">
    <title>Hotel Scalabrin</title>
    <link rel="stylesheet" href="estilos/estilo-header-principal.css">
    <link rel="stylesheet" href="estilos/estilo-cadastro-usuarios.css">
    <link rel="stylesheet" href="estilos/estilo-cadastro-produtos.css">
    <link rel="stylesheet" href="estilos/estilo-cadastro-hospedagens.css">
    <link rel="stylesheet" href="estilos/estilo-cadastro-venda.css">
</head>

<body>
    <header id="header-menu" role="banner">
        <div class="logo">
            <img src="imagens/hotel.png" alt="Logo do Hotel Scalabrin"/>
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
                        <li><a class="dropdown-item" href="cadastro-usuarios">Usuários</a></li>
                        <li><a class="dropdown-item" href="cadastro-produtos">Produtos</a></li>
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
                        <li>
                        	<a class="dropdown-item" href="cadastro-vendas">Vender Produto</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="icones" alt="Icone Consultas" src="imagens/icone-consultar.webp" width="30"
                            height="30" /> Consultas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown3">
						<li><a class="dropdown-item" href="relatorio-estoque">Estoque</a></li>
						<li><a class="dropdown-item" href="consulta-funcionarios">Funcionários</a></li>
                        <li><a class="dropdown-item" href="consulta-hospedes">Hóspedes</a></li>
						<li><a class="dropdown-item" href="consulta-reservas">Reservas</a></li>
                        <li><a class="dropdown-item" href="relatorio-vendas">Vendas</a></li>
                    </ul>
                </li>
                <!-- 
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        <img id="icones" alt="Icone Relatórios" src="imagens/icone-relatorios.webp" width="30"
                            height="30" /> Relatórios
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown4">
                        <li><a class="dropdown-item" href="consulta-vendas">Vendas</a></li>  
                    </ul>
                </li>
                 -->

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
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/pt.js"></script>