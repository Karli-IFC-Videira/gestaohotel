document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('cadastro-hospedagens');
    const cpfInput = document.getElementById('cpfHospede');
    const dReservaInput = document.getElementById("dReserva");
    const dEntradaInput = document.getElementById("dEntrada");
    const dSaidaInput = document.getElementById("dSaida");
    const valorInput = document.getElementById("valorDiaria");
    const nomeInput = document.getElementById('nome'); // Adicione o campo nome
    const sobrenomeInput = document.getElementById('sobrenome'); // Adicione o campo sobrenome
    const empresaInput = document.getElementById('empresa'); // Adicione o campo empresa
    const tipoQuartoInput = document.getElementById('tipoQuarto'); // Adicione o campo tipo quarto
    const numeroQuartoInput = document.getElementById('nQuarto'); // Adicione o campo numero quarto
    const statusQuartoInput = document.getElementById('statusQuarto'); // Adicione o campo status do quarto

    // Função para formatar o CPF
    cpfInput.addEventListener('input', function (e) {
        let value = e.target.value.replace(/\D/g, ''); // Remove não-dígitos
        let formattedValue = '';

        if (value.length > 0) {
            formattedValue = value.substring(0, 3); // Primeiro bloco

            if (value.length > 3) {
                formattedValue += '.' + value.substring(3, 6); // Segundo bloco

                if (value.length > 6) {
                    formattedValue += '.' + value.substring(6, 9); // Terceiro bloco

                    if (value.length > 9) {
                        formattedValue += '-' + value.substring(9, 11); // Dígitos finais
                    }
                }
            }
        }

        e.target.value = formattedValue; // Atualiza o valor do input
    });

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Impede o envio do formulário para tratar o cadastro

        let valid = true; // Flag para validação

        // Função para mostrar o alerta
        const showAlert = (message) => {
            Swal.fire('Erro!', message, 'error');
        };

        // Validação de CPF
        const cpf = cpfInput.value;

        if (!cpf) {
            showAlert('CPF é obrigatório.');
            valid = false;
        } else if (cpf.length !== 14) { // 11 para 123.456.789-10
            showAlert('O campo CPF deve ter 11 caracteres!');
            valid = false;
        }

        // Adicione validações para outros campos obrigatórios aqui
        // Exemplo: Validar se o valor é positivo
        const valor = valorInput.value;
        if (valor <= 0) {
            showAlert('O valor deve ser um número positivo.');
            valid = false;
        }

        // Impede o envio do formulário se houver erros
        if (!valid) {
            return; // Se não for válido, sai da função
        }

        // Se válido, continua o envio do formulário
        fetch(form.action, {
            method: 'POST',
            body: new FormData(form)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro no cadastro dos dados!');
            }
            return response.json();
        })
        .then(data => {
            if (data.status === "success") {
                // Limpa os campos desejados
                nomeInput.value = ""; // Limpa nome
                sobrenomeInput.value = ""; // Limpa sobrenome
                cpfInput.value = ""; // Limpa CPF
                empresaInput.value = ""; // Limpa empresa
                tipoQuartoInput.value = ""; // Limpa tipo quarto
                numeroQuartoInput.value = ""; // Limpa numero quarto
                statusQuartoInput.value = ""; // Limpa status do quarto
                valorInput.value = ""; // Limpa valor

                // Mantém os campos de data intactos
                dReservaInput.value = dReservaInput.value; // Mantém dReserva
                dEntradaInput.value = dEntradaInput.value; // Mantém dEntrada
                dSaidaInput.value = dSaidaInput.value; // Mantém dSaida

                Swal.fire('Sucesso!', data.message, 'success'); // Mensagem de sucesso
            } else {
                showAlert(data.message); // Exibir mensagem de erro
            }
        })
        .catch(error => {
            console.error('Erro ao processar a requisição:', error);
            showAlert("Erro ao processar a requisição.");
        });
    });
});
