$(document).ready(function () {
    // Exibir o modal de edição
    $('#tabela-produtos').on('click', '#editar', function () {
        var produtoId = $(this).data('codigo_Produto');
        // Aqui você pode preencher o modal com os dados do produto
        $('#editarModal').modal('show');
    });

    // Exibir o modal de confirmação de exclusão
    $('#tabela-produtos').on('click', '#excluir', function () {
        var produtoId = $(this).data('codigo_Produto');
        // Aqui você pode armazenar o ID do produto a ser excluído
        $('#confirmacaoModal').modal('show');
    });

    // Confirmar exclusão
    $('#confirmarExcluir').on('click', function () {
        // Lógica para excluir o produto usando o ID armazenado
        $('#confirmacaoModal').modal('hide'); // Fecha o modal de confirmação
        alert('Registro excluído!'); // Exemplo de feedback
    });
});