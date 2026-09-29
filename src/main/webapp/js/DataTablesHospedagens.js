$(document).ready(function() {
	var table = $('#tabela-hospedagens').DataTable({
		"ajax": {
			"url": "obter-dados-hospedagens",
			"dataSrc": ""
		},
		"columns": [
			{ "data": "codigoReserva" },
			{ "data": "nomeCliente" },
			{ "data": "sobrenomeCliente" },
			{ "data": "cpfHospede" },
			{ "data": "dataReserva" },
			{ "data": "dataEntrada" },
			{ "data": "dataSaida" },
			{ "data": "tipoQuartoReservado" },
			{ "data": "numeroQuarto" },
			{ "data": "statusQuarto" },
			{
				"data": null,
				"render": function(data, type, row) {
					return '<span class="btn-editar" data-id="' + row.codigoReserva + '">✏️</span>' +
						'<span class="btn-excluir" data-id="' + row.codigoReserva + '">🗑️</span>';
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
			{ orderable: false, targets: [0, 2, 5, 6, 7, 8, 9, 10] },
			{ searchable: false, targets: [0, 2, 5, 6, 7, 8, 9, 10] },
			{ width: "5%", targets: [0] },
			{ width: "10%", targets: [1] },
			{ width: "10%", targets: [2] },
			{ width: "10%", targets: [3] },
			{ width: "10%", targets: [4] },
			{ width: "10%", targets: [5] },
			{ width: "15%", targets: [6] },
			{ width: "10%", targets: [7] },
			{ width: "10%", targets: [8] },
			{ width: "7%", targets: [9] },
			{ width: "5%", targets: [10] }
		],
		dom: "Bfrtilp",
		buttons: [
			{
				extend: "excelHtml5",
				text: '<i class="fa-solid fa-file-excel"></i> Exportar a Excel',
				titleAttr: "Exportar a Excel",
				className: "btn btn-success",
				exportOptions: {
					columns: [0, 1, 2, 3, 4, 5, 6, 7, 8]
				}
			},
			{
				extend: "pdfHtml5",
				text: '<i class="fa-solid fa-file-pdf"></i> Exportar a PDF',
				titleAttr: "Exportar a PDF",
				className: "btn btn-danger",
				exportOptions: {
					columns: [0, 1, 2, 3, 4, 5, 6, 7, 8]
				}
			},
			{
				extend: "print",
				text: '<i class="fa-solid fa-print"></i> Imprimir',
				titleAttr: "Clique aqui para imprimir",
				className: "btn btn-info",
				exportOptions: {
					columns: [0, 1, 2, 3, 4, 5, 6, 7, 8]
				}
			}
		]
	});

	var hospedagem; // Variável para armazenar os dados da hospedagem

	$('#editarModal').on('show.bs.modal', function() {

		$.ajax({
			url: 'obter-tipo-quartos',
			method: 'GET',
			success: function(response) {
				var tiposQuarto = response;
				var select = $('#tiposQuarto');
				select.empty();
				select.append('<option value="">Selecione</option>');
				tiposQuarto.forEach(function(tipo) {
					var option = $('<option></option>').attr('value', tipo).text(tipo);
					select.append(option);
				});
			},
			error: function(xhr) {
				console.error('Erro ao buscar os tipos de quartos:', xhr);
				alert('Não foi possível carregar os tipos de quartos.');
			}
		});
	});

	$('#tabela-hospedagens').on('click', '.btn-editar', function() {
		var reservaId = $(this).data('id');
		$.ajax({
			url: 'obter-dados-hospedagens?id=' + reservaId,
			method: 'GET',
			success: function(data) {
				hospedagem = data; // Armazena os dados da hospedagem
				$('#codigoReserva').val(hospedagem.codigoReserva);
				$('#nomeHospede').val(hospedagem.nomeCliente);
				$('#sobrenomeHospede').val(hospedagem.sobrenomeCliente);
				$('#cpfHospedeEdit').val(hospedagem.cpfHospede);
				$('#dReservaEdit').val(hospedagem.dataReserva);
				$('#dEntradaEdit').val(hospedagem.dataEntrada);
				$('#dSaidaEdit').val(hospedagem.dataSaida);
				$('#tiposQuarto').val(hospedagem.tipoQuartoReservado);
				$('#nroQuarto').val(hospedagem.numeroQuarto);
				$('#status').val(hospedagem.statusQuarto);
				$('#valorQuarto').val(hospedagem.valorQuarto);
				$('#editarModal').modal('show');

				// Carrega números de quartos
				$.ajax({
					url: 'obter-numero-quartos',
					method: 'GET',
					data: { tQuarto: hospedagem.tipoQuartoReservado },
					success: function(response) {
						$('#nroQuarto').empty();
						$('#nroQuarto').append('<option value="" disabled>Selecione</option>');

						// Adiciona todos os números de quartos
						response.forEach(function(numeroQuarto) {
							$('#nroQuarto').append('<option value="' + numeroQuarto + '">' + numeroQuarto + '</option>');
						});

						// Inclui o quarto ocupado se não estiver na lista
						if (!response.includes(hospedagem.numeroQuarto)) {
							$('#nroQuarto').append('<option value="' + hospedagem.numeroQuarto + '">' + hospedagem.numeroQuarto + '</option>');
						}

						// Seleciona o número do quarto atual
						$('#nroQuarto').val(hospedagem.numeroQuarto);
					},
					error: function(xhr) {
						console.error('Erro ao buscar os números de quartos:', xhr);
						alert('Não foi possível carregar os números de quartos.');
					}
				});
			},
			error: function() {
				alert('Erro ao buscar a hospedagem.');
			}
		});
	});

	// Evento para quando o tipo de quarto for alterado
	$('#tiposQuarto').on('change', function() {
		var tipoQuartoSelecionado = $(this).val();

		if (tipoQuartoSelecionado) {
			$.ajax({
				url: 'obter-numero-quartos',
				method: 'GET',
				data: { tQuarto: tipoQuartoSelecionado },
				success: function(response) {
					$('#nroQuarto').empty();
					$('#nroQuarto').append('<option value="" disabled selected>Selecione</option>');
					response.forEach(function(numeroQuarto) {
						$('#nroQuarto').append('<option value="' + numeroQuarto + '">' + numeroQuarto + '</option>');
					});
				},
				error: function(xhr) {
					console.error('Erro ao buscar números de quartos:', xhr);
				}
			});
		} else {
			$('#nroQuarto').empty();
			$('#nroQuarto').append('<option value="" disabled selected>Selecione</option>');
		}
	});

	// Ao selecionar o número do quarto, busca o status e valor
	$("#nroQuarto").change(function() {
		var numeroQuarto = $(this).val();
		if (numeroQuarto) {
			$.ajax({
				url: 'obter-status-quartos',
				method: "GET",
				data: { numeroQuarto: numeroQuarto },
				dataType: "json",
				success: function(response) {
					if (response && response.status && response.valorDiaria) {
						$("#status").val(response.status);
						$("#valorQuarto").val(parseFloat(response.valorDiaria).toFixed(2));
					} else {
						$("#status").val('');
						$("#valorQuarto").val('');
					}
				},
				error: function(xhr) {
					console.error('Erro ao buscar status e valor da diária:', xhr);
					Swal.fire({
						title: 'Erro!',
						text: 'Não foi possível carregar os dados do quarto.',
						icon: 'error',
						confirmButtonText: 'Ok'
					});
				}
			});
		} else {
			$("#status").val('');
			$("#valorQuarto").val('');
		}
	});

	// Salvar alterações da hospedagem
	$('#salvarMudancas').on('click', function() {
		var id = $('#codigoReserva').val();
		var nome = $('#nomeHospede').val();
		var sobrenome = $('#sobrenomeHospede').val();
		var cpfHospede = $('#cpfHospedeEdit').val().replace(/[.\-]/g, '');
		var dReserva = $('#dReservaEdit').val();
		var dEntrada = $('#dEntradaEdit').val();
		var dSaida = $('#dSaidaEdit').val();
		var tQuarto = $('#tiposQuarto').val();
		var numeroQuarto = $('#nroQuarto').val();
		var valorQuar = $('#valorQuarto').val();
		var status = $('#status').val();

		// Verifique se os campos obrigatórios estão preenchidos
		if (!id || !numeroQuarto || !valorQuar) {
			Swal.fire({
				title: 'Atenção!',
				text: 'Código de reserva, número do quarto e valor da diária são obrigatórios.',
				icon: 'warning',
				confirmButtonText: 'Ok'
			});
			return; // Interrompe a execução se campos obrigatórios não estão preenchidos
		}

		// Verifica se o CPF tem 11 dígitos
		if (cpfHospede.length < 11) {
			Swal.fire({
				title: 'Atenção!',
				text: 'O CPF deve ter 11 dígitos. Por favor, verifique o CPF e tente novamente.',
				icon: 'warning',
				confirmButtonText: 'Ok'
			});
			return; // Interrompe a execução se o CPF for inválido
		}

		// Criando objeto com os dados
		var dadosHospedagem = {
			codigoReserva: id,
			nomeCliente: nome,
			sobrenomeCliente: sobrenome,
			cpfHospede: cpfHospede,
			dataReserva: dReserva,
			dataEntrada: dEntrada,
			dataSaida: dSaida,
			tipoQuartoReservado: tQuarto,
			numeroQuarto: numeroQuarto,
			valorQuarto: valorQuar,
			statusQuarto: status
		};

		$.ajax({
			url: 'atualizar-hospedagens',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(dadosHospedagem),
			success: function() {
				table.ajax.reload();
				$('#editarModal').modal('hide');
				Swal.fire({
					title: 'Sucesso!',
					text: 'Hospedagem atualizada com sucesso!',
					icon: 'success',
					confirmButtonText: 'Ok'
				});
			},
			error: function(xhr) {
				console.error('Erro ao atualizar a hospedagem:', xhr);
				alert('Erro ao atualizar a hospedagem.');
			}
		});
	});

	// Excluir hospedagem
	$('#tabela-hospedagens').on('click', '.btn-excluir', function() {
		var reservaId = $(this).data('id');
		Swal.fire({
			title: 'Você tem certeza?',
			text: 'Esta ação não poderá ser desfeita!',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Sim, excluir!',
			cancelButtonText: 'Cancelar'
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url: 'excluir-hospedagens?id=' + reservaId,
					method: 'DELETE',
					success: function() {
						table.ajax.reload();
						Swal.fire('Excluído!', 'A hospedagem foi excluída com sucesso!.', 'success');
					},
					error: function(xhr) {
						console.error('Erro ao excluir a hospedagem:', xhr.responseText);
						Swal.fire('Erro', xhr.responseText || 'Erro ao excluir a hospedagem.', 'error');
					}
				});
			}
		});
	});
});