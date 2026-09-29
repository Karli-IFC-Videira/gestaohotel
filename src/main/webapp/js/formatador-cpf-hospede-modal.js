document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('editar-modal');
    const cpfInput = document.getElementById('cpfHospedeEdit');

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
        event.preventDefault(); // Impede o envio do formulário

        let valid = true; // Flag para validação

        // Função para mostrar o alerta
        const showAlert = (message) => {
            Swal.fire('Erro!', message, 'error');
        };

        // Limpa caracteres não numéricos do CPF
        const cpf = cpfInput.value.replace(/\D/g, '');

        if (!cpf) {
            showAlert('CPF é obrigatório.');
            valid = false;
        } else if (cpf.length !== 11) { // 11 para CPF sem formatação
            showAlert('CPF deve ter exatamente 11 dígitos.');
            valid = false;
        }

        // Validação de outros campos, se necessário
        // Exemplo: Validar se o valor é positivo
        const valor = valorInput.value; // Verifique se 'valorInput' está definido
        if (valor <= 0) {
            showAlert('O valor deve ser um número positivo.');
            valid = false;
        }

        // Impede o envio do formulário se houver erros
        if (!valid) {
            return; // Se não for válido, sai da função
        }

        // Adiciona o CPF limpo ao FormData
        const formData = new FormData(form);
        formData.set('cpfHospede', cpf); // Atualiza o CPF no FormData

        // Se válido, continua o envio do formulário
        fetch(form.action, {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na requisição');
            }
            return response.json();
        })
        .then(data => {
            if (data.status === "success") {
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