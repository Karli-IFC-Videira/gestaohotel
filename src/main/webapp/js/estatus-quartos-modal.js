$("#nroQuarto").change(function() {
    var numeroQuarto = $(this).val();

    if (numeroQuarto) {
        $.ajax({
            url: 'obter-status-quartos',  // URL do servlet
            method: "GET",
            data: { numeroQuarto: numeroQuarto },  // Envia o número do quarto
            dataType: "json",  // Espera uma resposta JSON
            success: function(response) {
                // Verifica se a resposta contém 'status'
                if (response && response.status) {
                    // Preenche o campo de status
                    $("#status").val(response.status);  // Atualiza o campo de status
                }
            },
            error: function(xhr) {
                console.error('Erro ao buscar status do quarto:', xhr);
                Swal.fire({
                    title: 'Erro!',
                    text: 'Não foi possível carregar o status do quarto.',
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }
        });
    } else {
        // Se não houver número de quarto selecionado, limpa o campo de status
        $("#status").val('');
    }
});