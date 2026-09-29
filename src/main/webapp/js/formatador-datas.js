document.addEventListener('DOMContentLoaded', function () {
    const now = new Date(); // Obtém a data e hora atuais
    const formattedDate = now.toISOString().slice(0, 16); // Formato: YYYY-MM-DDTHH:MM
    const today = now.toISOString().split('T')[0]; // Formato: YYYY-MM-DD (apenas a data)

    // Definindo a data reserva com o valor atual
    document.getElementById("dReserva").value = formattedDate;

    // Configurando o Flatpickr para entrada de data e hora, mas sem permitir seleção
    flatpickr("#dReserva", {
        enableTime: true,
        dateFormat: "Y-m-d H:i",
        locale: "pt",
        defaultDate: now, // Define a data e hora atual como padrão
        allowInput: false, // Desabilita a entrada manual
        onReady: function (selectedDates, dateStr, instance) {
            instance.setDate(now); // Define a data e hora atuais ao abrir
            instance.set('disable', ['today']); // Desabilita a seleção
        },
        // Opção que impede que o calendário apareça
        clickOpens: false // Impede a abertura do calendário
    });
    flatpickr("#dEntrada", {
        enableTime: true,
        dateFormat: "Y-m-d H:i",
        locale: "pt",
        defaultDate: now, // Define a data e hora atual como padrão
        allowInput: false, // Desabilita a entrada manual
        minDate: today, // Data mínima (hoje)
    });
    flatpickr("#dSaida", {
        enableTime: true,
        dateFormat: "Y-m-d H:i",
        locale: "pt",
        defaultDate: now, // Define a data e hora atual como padrão
        allowInput: false, // Desabilita a entrada manual
        minDate: today, // Data mínima (hoje)
    });
});