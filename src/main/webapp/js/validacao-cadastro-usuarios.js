document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('cadastro-usuarios');
    const celularInput = document.getElementById('celular');
    const cpfInput = document.getElementById('cpf');

    // Função para formatar o celular
	celularInput.addEventListener('input', function (e) {
	    let value = e.target.value.replace(/\D/g, ''); // Remove todos os caracteres não numéricos
	    let formattedValue = '';

	    if (value.length > 0) {
	        formattedValue = '(' + value.substring(0, 2) + ')'; // Código de área

	        if (value.length > 2) {
	            formattedValue += value.substring(2, 7); // Próximos cinco dígitos

	            if (value.length > 7) {
	                formattedValue += '-' + value.substring(7, 11); // Últimos quatro dígitos
	            }
	        }
	    }

	    e.target.value = formattedValue; // Atualiza o valor do input
	});

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

	form.addEventListener('submit', function(event) {
	    let valid = true; // Flag para validação

	    // Função para mostrar o alerta
	    const showAlert = (message) => {
	        Swal.fire('Erro!', message, 'error');
	    };

		// Validação de Nome e Sobrenome
		const nome = document.getElementById('nome').value; 
		const sobrenome = document.getElementById('sobrenome').value; 
		const nomeSobrenomeRegex = /^[A-Za-zÀ-ÖØ-ÿ\s]+$/; // Regex que aceita apenas letras e espaços

		if (!nome) {
		    showAlert('Nome é obrigatório.');
		    valid = false;
		} else if (!nomeSobrenomeRegex.test(nome)) {
		    showAlert('Nome deve conter apenas letras e espaços.');
		    valid = false;
		}

		if (!sobrenome) {
		    showAlert('Sobrenome é obrigatório.');
		    valid = false;
		} else if (!nomeSobrenomeRegex.test(sobrenome)) {
		    showAlert('Sobrenome deve conter apenas letras e espaços.');
		    valid = false;
		}


	    // Validação de CPF
	    const cpf = cpfInput.value;

	    if (!cpf) {
	        showAlert('CPF é obrigatório.');
	        valid = false;
	    } else if (cpf.length !== 14) { // 14 para 123.456.789-10
	        showAlert('CPF deve ter exatamente 14 caracteres no formato 123.456.789-10.');
	        valid = false;
	    }

	    // Validação de Celular
	    const celular = document.getElementById('celular').value;

	    if (!celular) {
	        showAlert('Celular é obrigatório.');
	        valid = false;
	    } else if (!/^\(\d{2}\)\d{5}-\d{4}$/.test(celular)) {
	        showAlert('Formato do celular inválido. Use (XX)XXXXX-XXXX.');
	        valid = false;
	    }

	    // Impede o envio do formulário se houver erros
	    if (!valid) {
	        event.preventDefault();
	    }
	});
})