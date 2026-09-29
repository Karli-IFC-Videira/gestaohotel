$(document).ready(function() {
    // Carregar tipos de quartos ao abrir o modal
    $('#editarModal').on('show.bs.modal', function() {
        $.ajax({
            url: 'obter-tipo-quartos',
            method: 'GET',
            success: function(response) {
                var tiposQuarto = response;
                var select = $('#tiposQuarto');
                select.empty(); // Limpa opções atuais

                // Adiciona opções de tipos de quartos
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

    // Evento para quando o tipo de quarto for alterado
    $('#tiposQuarto').on('change', function() {
        var tipoQuartoSelecionado = $(this).val();

        if (tipoQuartoSelecionado) {
            // Fazendo requisição para obter os números de quartos
            $.ajax({
                url: 'obter-numero-quartos', // URL para obter números de quartos, deve ser ajustada
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
                    console.error('Erro ao buscar os números de quartos:', xhr);
                    Swal.fire({
                        title: 'Erro!',
                        text: 'Não foi possível carregar os números de quartos.',
                        icon: 'error',
                        confirmButtonText: 'Ok'
                    });
                }
            });
        } else {
            $('#nroQuarto').empty();
            $('#nroQuarto').append('<option value="" disabled selected>Selecione</option>');
        }
    });
});