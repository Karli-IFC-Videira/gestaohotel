<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header-perfil.jsp"/>
<link rel="stylesheet" href="estilos/estilo-cadastro-vendas.css">

<h1>Área de vendas</h1>

<section class="container-vendas">
    <form class="dados-venda" id="cadastro-vendas" action="cadastro-vendas" method="POST" accept-charset="UTF-8">
        <div>
            <h2>Dados da Venda</h2>

            <label class="codProd" for="codigoProduto" aria-label="Código do produto">Código do produto</label>
            <input type="number" id="codigoProduto" placeholder="Insira aqui o código do produto" aria-label="Código do produto" min="1" required>

            <label class="codProd" for="quantidade" aria-label="Quantidade de produto">Quantidade</label>
            <input type="number" id="quantidade" min="1" placeholder="Quantidade" aria-label="Quantidade de produto" required>

            <button type="button" id="adicionarProduto" aria-label="Adicionar produto à venda">Adicionar Produto</button>
        </div>
    </form>

    <div class="dados-tabela">
        <h2>Detalhes da Venda</h2>

        <table class="tabela-vendas">
            <thead>
                <tr class="cabecalho-tabela-vendas">
                    <th>Código</th>
                    <th>Descrição</th>
                    <th>Preço Unitário</th>
                    <th>Quantidade</th>
                    <th>Subtotal</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tbody>
                <!-- Linhas serão adicionadas dinamicamente -->
            </tbody>
        </table>

        <div class="botoes">
            <button id="cadastrarVenda" class="btn-verde" aria-label="Cadastrar venda">Cadastrar Venda</button>
            <button id="cancelar" class="btn-vermelho" aria-label="Cancelar venda">Cancelar</button>
        </div>

        <label class="valVenda" for="totalVenda" aria-label="Total da venda">Total Venda:</label>
        <input type="number" id="totalVenda" readonly aria-label="Total da venda" />
        <input type="hidden" id="nomeUsuario" value="${usuario}" />
    </div>
</section>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/MenuResponsivo.js"></script>
    <script src="js/remove-item.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>