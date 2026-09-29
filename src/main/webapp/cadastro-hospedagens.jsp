<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.Quartos"%>
<jsp:include page="header-perfil.jsp"/>

<section class="form-container">   
    <h1>Cadastro de Hospedagens/Reservas</h1>
    <p class="required-message">(*) São campos obrigatórios para preenchimento</p>

    <form id="cadastro-hospedagens" action="cadastrar-hospedagens" method="post" accept-charset="UTF-8">
        <div class="input-group">
            <div class="form-item">
                <label for="nome">*Nome</label>
                <input type="text" id="nome" name="nome" required autocomplete="off">
            </div>

            <div class="form-item">
                <label for="sobrenome">*Sobrenome</label>
                <input type="text" id="sobrenome" name="sobrenome" required autocomplete="off">
            </div>

            <div class="form-item">
                <label for="cpfHospede">CPF <span class="required">*</span></label>
                <input type="text" id="cpfHospede" name="cpfHospede" maxlength="14" required placeholder="123.456.789-00" autocomplete="off">
            </div>

            <div class="form-item">
                <label for="empresa">*Empresa</label>
                <input type="text" id="empresa" name="empresa" required>
            </div>

            <div class="form-item">
                <label for="dReserva">*Data de Reserva</label>
                <input type="text" id="dReserva" name="dReserva" required>
            </div>

            <div class="form-item">
                <label for="dEntrada">*Data de Entrada</label>
                <input type="text" id="dEntrada" name="dEntrada" required>
            </div>

            <div class="form-item">
                <label for="dSaida">*Data de Saída</label>
                <input type="text" id="dSaida" name="dSaida" required>
            </div>

            <div class="form-group">
                <label class="quarto" for="tQuarto">*Tipo de Quarto</label>
                <select id="tQuarto" name="tQuarto" required>
    				<option value="">Selecione</option>
    				<c:forEach var="quarto" items="${quartos}">
        				<option value="${quarto.tipoQuarto}">${quarto.tipoQuarto}</option>
    				</c:forEach>
				</select>                       
            </div>

            <div class="form-group">
                <label class="nquart" for="nQuarto">*Número do Quarto</label>
                <select id="nQuarto" name="nQuarto" required>
                    <option value="" disabled selected>Selecione</option>
                </select>
            </div>

            <div class="form-item">
                <label for="valorDiaria">*Valor</label>
                <input type="number" id="valorDiaria" name="valorDiaria" required min="1" step="1">
            </div>
            
            <div class="form-item">
                    <label for="statusQuarto">Status do Quarto</label>
                    <input type="text" id="statusQuarto" name="statusQuarto" readonly>
            </div>
         </div>

        <div class="button-group">
            <div class="btn-group-right">
                <button type="submit" class="btn cadastrar-hospede">Reservar</button>
            </div>
        </div>
    </form>
</section>

<section class="container-tabela-hospedes">
    <table id="tabela-hospedagens" class="display tabela-centralizada">
        <thead>
            <tr>
                <th>Código Reserva</th>
                <th>Nome</th>
                <th>Sobrenome</th>
                <th>CPF</th>
                <th>Data de Reserva</th>
                <th>Data de Entrada</th>
                <th>Data de Saída</th>
                <th>Tipo Quarto</th>
                <th>Nro do Quarto</th>
                <th>Status</th>
                <th>Opções</th>
            </tr>
        </thead>
        <tbody>
            <!-- Os dados serão preenchidos via DataTables -->
        </tbody>
    </table>
</section>

<!-- Modal de Edição -->
<div class="modal fade" id="editarModal" tabindex="-1" role="dialog" aria-labelledby="editarModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editarModalLabel">Editar Hospedagem</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input type="hidden" id="id" name="id"> <!-- Para armazenar o ID do produto -->
                <div class="form-group">
                    <label for="codigoReserva">Código reserva</label>
                    <input type="number" name="codigoReserva" class="form-control" id="codigoReserva" required readonly>
                </div>
                <div class="form-group">
                    <label for="nomeHospede">Nome</label>
                    <input type="text" class="form-control" id="nomeHospede" required>
                </div>
                <div class="form-group">
                    <label for="sobrenomeHospede">Sobrenome</label>
                    <input type="text" class="form-control" id="sobrenomeHospede" required>
                </div>
                
                <div class="form-item">
                	<label for="cpfHospedeEdit">CPF <span class="required">*</span></label>
                	<input type="text" class="cpf-hospede" id="cpfHospedeEdit" name="cpfHospedeEdit" maxlength="14" required placeholder="123.456.789-00" autocomplete="off">
            	</div>
                
                <div class="form-item">
                    <label for="dReservaEdit">*Data de Reserva</label>
                    <input type="date" id="dReservaEdit" name="dReservaEdit" required readonly>
                </div>
                <div class="form-item">
                    <label for="dEntradaEdit">*Data de Entrada</label>
                    <input type="date" id="dEntradaEdit" name="dEntradaEdit" required>
                </div>

                <div class="form-item">
                    <label for="dSaidaEdit">*Data de Saída</label>
                    <input type="date" id="dSaidaEdit" name="dSaidaEdit" required>
                </div>
                
                <div class="form-group">
                <label class="quarto" for="tiposQuarto">*Tipo de Quarto</label>
                <select id="tiposQuarto" name="tiposQuarto" required>
    				<option value="">Selecione</option>
    				<c:forEach var="resevas" items="${hospedagens_reservas}">
        				<option value="${resevas.tipoQuartoReservado}">${resevas.tipoQuartoReservado}</option>
    				</c:forEach>
				</select>                       
            	</div>
            	
            	<div class="form-group">
                	<label class="nquart" for="nroQuarto">*Nro do Quarto</label>
                		<select id="nroQuarto" name="nroQuarto" required>
                    		<option value="" disabled selected>Selecione</option>
                		</select>
            	</div>
            	
            	<div class="form-item">
                	<label for="valorQuarto">*Valor</label>
                	<input type="number" id="valorQuarto" name="valorQuarto" required min="1" step="1">
            	</div>
                
                <div class="form-group">
                    <label for="status">Estatus</label>
                    <input type="text" class="status" id="status" required readonly>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary fechar" data-bs-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary" id="salvarMudancas" data-codigo-reserva="${codigo_Reserva}">Salvar Mudanças</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal de Confirmação -->
<div class="modal fade" id="confirmacaoModal" tabindex="-1" aria-labelledby="confirmacaoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmacaoModalLabel">Confirmação</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Tem certeza de que deseja excluir este registro?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Não</button>
                <button type="button" class="btn btn-danger" id="confirmarExcluir">Sim</button>
            </div>
        </div>
    </div>
</div>

<% 
    String mensagem = (String) request.getSession().getAttribute("msg");
    String estatus = request.getSession().getAttribute("estatus") != null ? request.getSession().getAttribute("estatus").toString() : "success";

    if (mensagem != null) { 
%>
    <script>
        Swal.fire({
            title: '<%= estatus.equals("success") ? "Sucesso!" : "Erro!" %>',
            text: '<%= mensagem %>',
            icon: '<%= estatus.equals("success") ? "success" : "error" %>',
            confirmButtonText: 'Ok'
        });
    </script>
<% 
        request.getSession().removeAttribute("msg");
        request.getSession().removeAttribute("estatus");
    } 
%>

<!-- DataTables-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/2.1.7/js/dataTables.min.js"></script>
<script src="https://cdn.datatables.net/2.1.7/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/3.1.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.bootstrap5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.colVis.min.js"></script>
<script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.print.min.js"></script>
<script src="https://cdn.datatables.net/keytable/2.12.1/js/dataTables.keyTable.min.js"></script>
<script src="https://cdn.datatables.net/responsive/3.0.3/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/3.0.3/js/responsive.bootstrap5.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/DataTablesHospedagens.js" type="text/javascript"></script>
<script src="js/formatador-datas.js" type="text/javascript"></script>
<script src="js/formatador-cpf-hospedes.js" type="text/javascript"></script>
<script src="js/formatador-cpf-hospede-modal.js" type="text/javascript"></script>
<script src="js/numero-e-tipo-quarto-modal.js" type="text/javascript"></script>
<script src="js/estatus-quartos-modal.js" type="text/javascript"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const nQuartoSelect = document.getElementById('nQuarto');
        
        // Removido o evento que impede a interação
       // nQuartoSelect.addEventListener('mousedown', function (e) {
       //      e.preventDefault(); 
        // });
    });
</script>

<script type="text/javascript">
$(document).ready(function() {
    $('#cadastro-hospedagens').on('submit', function(event) {
        event.preventDefault(); // Evita o envio do formulário tradicional
        
        $.ajax({
            url: 'cadastrar-hospedagens',
            method: 'POST',
            data: $(this).serialize(),
            success: function(response) {
                if (response.status === "success") {
                    Swal.fire({
                        title: 'Sucesso!',
                        text: response.message,
                        icon: 'success',
                        confirmButtonText: 'Ok'
                    }).then(() => {
                        $('#tabela-hospedagens').DataTable().ajax.reload(); // Recarregar a tabela
                    });
                }
            },
            error: function(xhr) {
                var response = JSON.parse(xhr.responseText);
                Swal.fire({
                    title: 'Erro!',
                    text: response.message,
                    icon: 'error',
                    confirmButtonText: 'Ok'
                });
            }
        });
    });
});
</script>

<script type="text/javascript">
$(document).ready(function () {
    // Fazer a requisição para obter os tipos de quartos
    $.ajax({
        url: 'obter-tipo-quartos', // URL do servlet que retorna os dados
        method: 'GET',
        success: function(response) {
            // 'response' contém o array de tipos de quartos
            var tiposQuarto = response;
            
            // Seleciona o combobox pelo ID
            var select = $('#tQuarto');
            
            // Itera sobre os tipos de quartos e adiciona as opções ao combobox
            tiposQuarto.forEach(function(tipo) {
                // Cria uma nova opção e adiciona ao combobox
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

</script>

<script type="text/javascript">
$(document).ready(function() {
    // Evento para quando o tipo de quarto for alterado
    $('#tQuarto').on('change', function() {
        var tipoQuartoSelecionado = $(this).val();

        if (tipoQuartoSelecionado) {
            // Fazendo requisição para obter os números de quartos
            $.ajax({
                url: 'obter-numero-quartos',
                method: 'GET',
                data: { tQuarto: tipoQuartoSelecionado },
                success: function(response) {
                    // Limpa as opções atuais do select de número de quarto
                    $('#nQuarto').empty();
                    $('#nQuarto').append('<option value="" disabled selected>Selecione</option>');

                    // Adiciona os números de quartos retornados no select
                    response.forEach(function(numeroQuarto) {
                        $('#nQuarto').append('<option value="' + numeroQuarto + '">' + numeroQuarto + '</option>');
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
            // Se o tipo de quarto não estiver selecionado, reseta o select de número de quartos
            $('#nQuarto').empty();
            $('#nQuarto').append('<option value="" disabled selected>Selecione</option>');
        }
    });
});

</script>

<script type="text/javascript">
$("#nQuarto").change(function() {
    var numeroQuarto = $(this).val();

    if (numeroQuarto) {
        $.ajax({
            url: 'obter-status-quartos',  // URL do servlet
            method: "GET",
            data: { numeroQuarto: numeroQuarto },  // Envia o número do quarto
            dataType: "json",  // Espera uma resposta JSON
            success: function(response) {
                // Verifica se a resposta contém 'status' e 'valorDiaria'
                if (response && response.status && response.valorDiaria) {
                    // Converte o valor da diária para número antes de aplicar toFixed
                    var valorDiaria = parseFloat(response.valorDiaria);
                    
                    // Preenche o campo de status e valor
                    $("#statusQuarto").val(response.status);
                    $("#valorDiaria").val(valorDiaria.toFixed(2));
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
    }
});

</script>
