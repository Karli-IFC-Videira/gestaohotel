$(document).ready(function () {
    var table = $('#tabela-produtos').DataTable({
        "ajax": {
            "url": "listar-produtos", // URL do servlet que retorna dados em JSON
            "dataSrc": ""
        },
        "columns": [
            { "data": "codigoProduto" },
            { "data": "nomeProduto" },
            { "data": "quantidade" },
            { "data": "precoCompra" },
            { "data": "precoVenda" },
            { "data": "marca" },
            { "data": "nomeUsuario" },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<span class="btn-editar" data-id="'+ row.codigoProduto +'">✏️</span>' +
                           '<span class="btn-excluir" data-id="'+ row.codigoProduto +'">🗑️</span>';
                }
            }
        ],
        "searching": true,
        "paging": true,
        "info": false,
        language: {
            "decimal": ",",
            "emptyTable": "Nenhum dado disponível na tabela",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
            "infoEmpty": "Mostrando 0 a 0 de 0 entradas",
            "infoFiltered": "(filtrado de _MAX_ entradas totais)",
            "lengthMenu": "Mostrar _MENU_ entradas",
            "loadingRecords": "Carregando...",
            "processing": "Processando...",
            "search": "Pesquisar:",
            "zeroRecords": "Nenhum resultado encontrado",
            "paginate": {
                "first": "Primeiro",
                "last": "Último",
                "next": "Próximo",
                "previous": "Anterior"
            }
        },
        columnDefs: [
            { orderable: false, targets: [0, 2, 3, 4, 5, 7] }, // Colunas que não devem ser ordenáveis
            { searchable: false, targets: [0, 2, 3, 4, 5, 7] },
            { width: "7%", targets: [0] },
            { width: "15%", targets: [1] },
            { width: "10%", targets: [2] },
            { width: "15%", targets: [3] },
            { width: "15%", targets: [4] },
            { width: "13%", targets: [5] },
            { width: "15%", targets: [6] },
            { width: "25%", targets: [7] }
        ],
        dom: "Bfrtilp",
        buttons: [
            {
                extend: "excelHtml5",
                text: '<i class="fa-solid fa-file-excel"></i> Exportar a Excel',
                titleAttr: "Exportar a Excel",
                className: "btn btn-success",
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5, 6] // Ajuste as colunas que serão exportadas
                }
            },
            {
                extend: "pdfHtml5",
                text: '<i class="fa-solid fa-file-pdf"></i> Exportar a PDF',
                titleAttr: "Exportar a PDF",
                className: "btn btn-danger",
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5, 6] // Ajuste das colunas que serão exportadas
                }
            },
            {
                extend: "print",
                text: '<i class="fa-solid fa-print"></i> Imprimir',
                titleAttr: "Clique aqui para imprimir",
                className: "btn btn-info",
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5, 6] // Ajuste as colunas que serão exportadas
                }
            }
        ]
    });

	// Edição de produtos
	$('#tabela-produtos').on('click', '.btn-editar', function () {
	    var produtoId = $(this).data('id');

	    $.ajax({
	        url: `buscar-produto?id=${produtoId}`,
	        method: 'GET',
	        success: function(produto) {
				$('#id').val(produto.codigoProduto);
				    $('#codigoProduto').text(produto.codigoProduto);
				    $('#produt').val(produto.nomeProduto);
				    $('#qtd').val(produto.quantidade);
				    $('#pCompra').val(produto.precoCompra);
				    $('#pVenda').val(produto.precoVenda);
				    $('#marcas').val(produto.marca);
				    $('#usuario').text(produto.nomeUsuario);
				    $('#editarModal').modal('show');
	        },
	        error: function() {
	            alert('Erro ao buscar o produto.');
	        }
	    });
	});

    // Salvar alterações do produto
    $('#salvarMudancas').on('click', function() {
        var id = $('#id').val();
        var produto = $('#produt').val();
        var quantidade = $('#qtd').val();
		var precoCompra = parseFloat($('#pCompra').val());
		var precoVenda = parseFloat($('#pVenda').val());
        var marca = $('#marcas').val();
		
		if (isNaN(precoCompra) || isNaN(precoVenda)) {
		    alert("Por favor, insira valores válidos para preço de compra e venda.");
		    return; // Interrompe a execução se os valores não forem válidos
		}
		
		// Exibir os dados no console para verificar
		    console.log('Dados a serem enviados:', {
		        codigo: id,
		        produto: produto,
		        quantidade: quantidade,
		        preco_compra: precoCompra,
		        preco_venda: precoVenda,
		        marca: marca,
		    });

        $.ajax({
            url: `atualizar-produto`,
            method: 'POST',
            data: {
                codigo: id,
                produto: produto,
                quantidade: quantidade,
                preco_compra: precoCompra,
                preco_venda: precoVenda,
                marca: marca,
            },
            success: function() {
                table.ajax.reload(); // Recarregar a tabela
                $('#editarModal').modal('hide'); // Fecha o modal
				Swal.fire({
				        title: 'Sucesso!',
				        text: 'Produto atualizado com sucesso!',
				        icon: 'success',
				        confirmButtonText: 'Ok'
				    });
            },
			error: function(xhr) {
			        console.error('Erro ao atualizar o produto:', xhr); // Log do erro
			        alert('Erro ao atualizar o produto.');
			    }
        });
    });

	// Excluir produto
	$('#tabela-produtos').on('click', '.btn-excluir', function () {
	    var produtoId = $(this).data('id');
	    $('#confirmarExcluir').data('id', produtoId);
	    $('#confirmacaoModal').modal('show');
	});

	// Confirmar exclusão
	$('#confirmarExcluir').on('click', function () {
	    var produtoId = $(this).data('id'); // Corrigido para data-id

	    $.ajax({
	        url: `excluir-produto?id=${produtoId}`,
	        method: 'DELETE',
	        success: function() {
	            table.ajax.reload(); // Recarregar a tabela
	            $('#confirmacaoModal').modal('hide');
				$('#confirmacaoModal').modal('hide'); // Fecha o modal
				    Swal.fire({
				        title: 'Sucesso!',
				        text: 'Produto excluído com sucesso!',
				        icon: 'success',
				        confirmButtonText: 'Ok'
				    });
	        },
	        error: function() {
	            alert('Erro ao excluir o registro.');
	        }
	    });
	});
})