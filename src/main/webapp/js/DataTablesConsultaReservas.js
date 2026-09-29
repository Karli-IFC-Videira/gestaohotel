$(document).ready(function () {
    var table = $('#tabela-reservas').DataTable({
        "ajax": {
            "url": "api/consulta-reservas",
            "dataSrc": ""
        },
        "columns": [
            { "data": "codigo_Reserva" },
            { "data": "numero_Quarto" },
            { "data": "tipoQuartoReservado" },
            { "data": "valorQuarto" },
            { "data": "dataReserva" },
            { "data": "dataEntrada" },
            { "data": "dataSaida" },
            { "data": "nomeUsuario" }
        ],
        "searching": true,
        "paging": true,
        "info": true,
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
            { orderable: false, targets: [0, 2, 3, 4, 5, 6, 7] },
            { searchable: false, targets: [0, 2, 3, 4, 5, 6, 7] },
            { width: "10%", targets: [0] },
            { width: "10%", targets: [1] },
            { width: "12%", targets: [2] },
            { width: "12%", targets: [3] },
            { width: "13%", targets: [4] },
            { width: "13%", targets: [5] },
            { width: "13%", targets: [6] },
            { width: "17%", targets: [7] }
        ],
        dom: "Bfrtilp",
        buttons: [
            {
                extend: "excelHtml5",
                text: '<i class="fa-solid fa-file-excel"></i> Exportar a Excel',
                titleAttr: "Exportar a Excel",
                className: "btn btn-success",
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5, 6, 7]
                }
            },
            {
                extend: "pdfHtml5",
                text: '<i class="fa-solid fa-file-pdf"></i> Exportar a PDF',
                titleAttr: "Exportar a PDF",
                className: "btn btn-danger",
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5, 6, 7]
                }
            },
            {
                extend: "print",
                text: '<i class="fa-solid fa-print"></i> Imprimir',
                titleAttr: "Clique aqui para imprimir",
                className: "btn btn-info",
                exportOptions: {
                    columns: [0, 1, 2, 3, 4, 5, 6, 7]
                }
            }
        ]
    });
});
