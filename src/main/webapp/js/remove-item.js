$(document).ready(function() {
    // Carregar os dados da tabela do localStorage quando a página for carregada
    carregarTabelaDoStorage();

    // Evento para o botão "Adicionar Produto"
    $("#adicionarProduto").on("click", function() {
        var codigoProduto = $("#codigoProduto").val();
        var quantidade = $("#quantidade").val();

        // Verifica se os campos foram preenchidos
        if (!codigoProduto || !quantidade || quantidade <= 0) {
            Swal.fire({
                icon: 'warning',
                title: 'Campos obrigatórios',
                text: 'Por favor, preencha o código do produto e a quantidade válida.',
                confirmButtonText: 'OK'
            });
            return;
        }

        // Envia os dados via AJAX para verificar estoque
        $.ajax({
            url: '/GestaoHotel/verificar-estoque',
            type: 'POST',
            dataType: 'json',
            data: {
                codigoProduto: codigoProduto,
                quantidade: quantidade
            },
            success: function(data) {
                if (data.erro) {
                    // Caso haja algum erro de estoque ou outro
                    Swal.fire({
                        icon: 'error',
                        title: 'Erro',
                        text: data.erro,
                        confirmButtonText: 'OK'
                    });
                } else {
                    // Verifica se a quantidade do estoque é suficiente
                    if (quantidade > data.quantidadeEmEstoque) {
                        Swal.fire({
                            icon: 'warning',
                            title: 'Estoque Insuficiente',
                            text: 'Não há estoque suficiente para o produto ' + data.nomeProduto + '. Estoque disponível: ' + data.quantidadeEmEstoque,
                            confirmButtonText: 'OK'
                        });
                    } else {
                        // Se o estoque for suficiente, adiciona à tabela
                        adicionarProdutoTabela(data, quantidade);
                    }
                }
            },
            error: function(xhr, status, error) {
                Swal.fire({
                    icon: 'warning',
                    title: 'Advertência',
                    text: 'Não há estoque suficiente do código: ' + codigoProduto,
                    confirmButtonText: 'OK'
                });
            }
        });
    });

    // Função para adicionar produto à tabela
    function adicionarProdutoTabela(data, quantidade) {
        var precoVenda = data.precoVenda;
        var codigoProduto = data.codigoProduto;
        var nomeProduto = data.nomeProduto;

        var subtotal = quantidade * precoVenda;

        var novaLinha = "<tr>" +
            "<td>" + codigoProduto + "</td>" +
            "<td>" + nomeProduto + "</td>" +
            "<td>" + precoVenda.toFixed(2) + "</td>" +
            "<td>" + quantidade + "</td>" +
            "<td>" + subtotal.toFixed(2) + "</td>" +
            "<td><button class='removerProduto'>🗑️</button></td>" +
            "</tr>";

        $(".tabela-vendas tbody").append(novaLinha);
        atualizarTotalVenda();

        // Salva os dados no localStorage sempre que um item é adicionado
        salvarTabelaNoStorage();

        // Limpa os campos de código e quantidade
        $("#codigoProduto").val('');
        $("#quantidade").val('');
    }

    // Função para atualizar o total da venda
    function atualizarTotalVenda() {
        var totalVenda = 0;
        $(".tabela-vendas tbody tr").each(function() {
            var subtotal = parseFloat($(this).find("td:eq(4)").text());
            totalVenda += subtotal;
        });
        $("#totalVenda").val(totalVenda.toFixed(2));
    }

    // Evento para remover produto da tabela
    $(document).on("click", ".removerProduto", function() {
        $(this).closest("tr").remove();
        atualizarTotalVenda();

        // Salva os dados atualizados no localStorage após remover um item
        salvarTabelaNoStorage();
    });

    // Quando clica para cadastrar a venda
	$("#cadastrarVenda").on("click", function() {
	    var items = [];
	    $(".tabela-vendas tbody tr").each(function() {
	        var codigoProduto = $(this).find("td:eq(0)").text();
	        var nomeProduto = $(this).find("td:eq(1)").text();
	        var precoVenda = parseFloat($(this).find("td:eq(2)").text());
	        var quantidade = parseInt($(this).find("td:eq(3)").text());
	        var subtotal = parseFloat($(this).find("td:eq(4)").text());

	        // **Alteração aqui**: Passando o subtotal para o campo valorVenda
	        var valorVenda = subtotal; // Agora o valorVenda é o subtotal, não o precoVenda

	        items.push({
	            codigoProduto: codigoProduto,
	            nomeProduto: nomeProduto,
	            precoVenda: precoVenda,
	            quantidade: quantidade,
	            subtotal: subtotal,
	            valorVenda: valorVenda // Passando subtotal para valorVenda
	        });
	    });

	    var totalVenda = parseFloat($("#totalVenda").val());
	    var nomeUsuario = $("#nomeUsuario").val();  // Pega o nome do usuário de um campo oculto ou variável global

	    // Verifica se a venda possui itens antes de tentar cadastrar
	    if (items.length === 0) {
	        Swal.fire({
	            icon: 'warning',
	            title: 'Nenhum item na venda',
	            text: 'Adicione ao menos um produto antes de cadastrar a venda.',
	            confirmButtonText: 'OK'
	        });
	        return;
	    }

	    // Envia os dados via AJAX para cadastrar a venda
	    $.ajax({
	        url: '/GestaoHotel/cadastrar-venda',  // URL do seu servlet ou endpoint
	        type: 'POST',
	        contentType: 'application/json',
	        data: JSON.stringify({
	            totalVenda: totalVenda,
	            nomeUsuario: nomeUsuario,
	            items: items
	        }),
	        success: function(response) {
	            if (response.status === 'success') {
	                Swal.fire({
	                    icon: 'success',
	                    title: 'Venda cadastrada com sucesso!',
	                    text: 'A venda foi realizada.',
	                    confirmButtonText: 'OK'
	                });

	                // Chama a função de impressão
	                imprimirVenda(items, totalVenda, nomeUsuario);

	                $(".tabela-vendas tbody").empty();
	                $("#totalVenda").val('');

	                // Limpa os dados no localStorage após a venda ser cadastrada
	                localStorage.removeItem("vendaProdutos");
	            } else {
	                Swal.fire({
	                    icon: 'error',
	                    title: 'Erro',
	                    text: 'Não foi possível cadastrar a venda. Tente novamente.',
	                    confirmButtonText: 'OK'
	                });
	            }
	        },
	        error: function(xhr, status, error) {
	            Swal.fire({
	                icon: 'error',
	                title: 'Erro no servidor',
	                text: 'Houve um problema ao tentar registrar a venda.',
	                confirmButtonText: 'OK'
	            });
	        }
	    });
	});

    // Função para imprimir a venda
    function imprimirVenda(items, totalVenda, nomeLogin) {
        var printContent = `
            <html>
            <head>
                <title>Impressão de Venda</title>
                <style>
                    /* Define margens da página de impressão */
                    @page {
                        margin-top: 3cm;
                        margin-left: 3cm;
                        margin-right: 2cm;
                        margin-bottom: 2cm;
                    }

                    /* Estilo geral */
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        color: #333;
                    }

                    h2 {
                        text-align: center;
                        font-size: 22px;
                        color: #0056b3;
                        margin-top: 20px;
                    }

                    p {
                        font-size: 16px;
                        line-height: 1.6;
                    }

                    table {
                        width: 100%;
                        margin-top: 20px;
                        border-collapse: collapse;
                    }

                    th, td {
                        padding: 10px;
                        text-align: left;
                        border: 1px solid #ddd;
                    }

                    th {
                        background-color: #f2f2f2;
                        color: #0056b3;
                    }

                    tr:nth-child(even) {
                        background-color: #f9f9f9;
                    }

                    .table-header {
                        font-weight: bold;
                        font-size: 16px;
                    }

                    .total {
                        font-size: 18px;
                        font-weight: bold;
                        margin-top: 20px;
                        text-align: right;
                    }

                    .operator {
                        font-size: 16px;
                        font-weight: bold;
                    }

                    .logo {
                        display: block;
                        margin: 0 auto;
                        width: 150px; /* Ajuste a largura conforme necessário */
                        margin-top: 20px;
                    }
                </style>
            </head>
            <body>
                <!-- Adiciona o logo -->
                <img src="imagens/hotel.png" alt="Logo da Empresa" class="logo">
                
                <h2>Venda Realizada</h2>
                <p class="operator"><strong>Operador:</strong> ${nomeLogin}</p>
                <table>
                    <thead>
                        <tr>
                            <th class="table-header">Código</th>
                            <th class="table-header">Descrição</th>
                            <th class="table-header">Preço Unitário</th>
                            <th class="table-header">Quantidade</th>
                            <th class="table-header">Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>`;

        // Preenche os dados da venda na tabela
        items.forEach(function(item) {
            printContent += `
                <tr>
                    <td>${item.codigoProduto}</td>
                    <td>${item.nomeProduto}</td>
                    <td>R$ ${item.precoVenda.toFixed(2)}</td>
                    <td>${item.quantidade}</td>
                    <td>R$ ${item.subtotal.toFixed(2)}</td>
                </tr>`;
        });

        printContent += `
                    </tbody>
                </table>
                <div class="total">
                    <p><strong>Total da Venda:</strong> R$ ${totalVenda.toFixed(2)}</p>
                </div>
            </body>
            </html>
        `;

        // Cria uma janela de impressão com o conteúdo gerado
        var printWindow = window.open('', '', 'height=600,width=800');
        printWindow.document.write(printContent);
        printWindow.document.close();
        printWindow.print();
    }

    // Evento para o botão "Cancelar" (Limpar a venda)
    $("#cancelar").on("click", function() {
        // Confirmação antes de cancelar a venda
        Swal.fire({
            icon: 'warning',
            title: 'Cancelar Venda',
            text: 'Tem certeza que deseja cancelar a venda? Todos os produtos adicionados serão removidos.',
            showCancelButton: true,
            confirmButtonText: 'Sim, cancelar',
            cancelButtonText: 'Não, manter'
        }).then((result) => {
            if (result.isConfirmed) {
                // Limpa a tabela e o total da venda
                $(".tabela-vendas tbody").empty();
                $("#totalVenda").val('');

                // Limpa o localStorage após o cancelamento
                localStorage.removeItem("vendaProdutos");

                Swal.fire({
                    icon: 'success',
                    title: 'Venda cancelada!',
                    text: 'A venda foi cancelada com sucesso!',
                    confirmButtonText: 'OK'
                });
            }
        });
    });

    // Função para salvar os dados da tabela no localStorage
    function salvarTabelaNoStorage() {
        var tabelaData = [];
        $(".tabela-vendas tbody tr").each(function() {
            var codigoProduto = $(this).find("td:eq(0)").text();
            var nomeProduto = $(this).find("td:eq(1)").text();
            var precoVenda = parseFloat($(this).find("td:eq(2)").text());
            var quantidade = parseInt($(this).find("td:eq(3)").text());
            var subtotal = parseFloat($(this).find("td:eq(4)").text());

            tabelaData.push({
                codigoProduto: codigoProduto,
                nomeProduto: nomeProduto,
                precoVenda: precoVenda,
                quantidade: quantidade,
                subtotal: subtotal
            });
        });
        localStorage.setItem("vendaProdutos", JSON.stringify(tabelaData));
    }

    // Função para carregar os dados da tabela do localStorage
    function carregarTabelaDoStorage() {
        var tabelaData = JSON.parse(localStorage.getItem("vendaProdutos"));
        if (tabelaData && tabelaData.length > 0) {
            tabelaData.forEach(function(item) {
                var novaLinha = "<tr>" +
                                "<td>" + item.codigoProduto + "</td>" +
                                "<td>" + item.nomeProduto + "</td>" +
                                "<td>" + item.precoVenda.toFixed(2) + "</td>" +
                                "<td>" + item.quantidade + "</td>" +
                                "<td>" + item.subtotal.toFixed(2) + "</td>" +
                                "<td><button class='removerProduto'>🗑️</button></td>" +
                                "</tr>";

                $(".tabela-vendas tbody").append(novaLinha);
            });
            atualizarTotalVenda();
        }
    }
});