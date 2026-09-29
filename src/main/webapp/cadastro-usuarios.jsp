<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header-administrador.jsp"/>

<section class="container-cadastro">
    <form id="cadastro-usuarios" action="controlador-cadastros" method="post" accept-charset="UTF-8">
        <div class="container">
            <h1>Formulário de Cadastro</h1>
            <p style="color: red;">(*) São campos obrigatórios para preenchimento</p>

            <div class="form-group">
                <label for="nome">Nome <span class="required">*</span></label>
                <input type="text" id="nome" name="nomeUsuario" value="${nomeUsuario != null ? nomeUsuario : ''}" required>
            </div>

            <div class="form-group">
                <label for="sobrenome">Sobrenome <span class="required">*</span></label>
                <input type="text" id="sobrenome" name="sobrenomeUsuario" value="${sobrenomeUsuario != null ? sobrenomeUsuario : ''}" required>
            </div>

            <div class="form-group">
                <label for="cpf">CPF <span class="required">*</span></label>
                <input type="text" id="cpf" name="cpfUsuario" value="${cpfUsuario != null ? cpfUsuario : ''}" required placeholder="123.456.789-10">
            </div>

            <div class="form-group">
                <label for="data-nascimento">Data de nascimento <span class="required">*</span></label>
                <input type="date" id="data-nascimento" name="dataNascimento" value="${dataNascimento != null ? dataNascimento : ''}" required>
            </div>

            <div class="form-group">
                <label for="nivel-acesso">Nível de acesso <span class="required">*</span></label>
                <select id="nivelAcesso" name="nivelAcesso" required>
                    <option value="" disabled selected >Selecione</option>
                    <option value="Administrador">Administrador</option>
                    <option value="Atendente">Atendente</option>
                </select>
            </div>

            <div class="form-group">
                <label for="email">Email <span class="required">*</span></label>
                <input type="email" id="email" name="email" value="${email != null ? email : ''}" required>
            </div>

            <div class="form-group">
                <label for="celular">Celular <span class="required">*</span></label>
                <input type="tel" id="celular" name="celular" value="${celular != null ? celular : ''}" required pattern="^\(\d{2}\)\d{5}-\d{4}$" 
           placeholder="(01)23456-7890" maxlength="15">
            </div>

            <div class="form-group">
                <label for="usuario">Usuário <span class="required">*</span></label>
                <input type="text" id="usuario" name="nomeLogin" value="${nomeLogin != null ? nomeLogin : ''}" required>
            </div>

            <div class="form-group">
                <label for="senha">Senha <span class="required">*</span></label>
                <input type="password" id="senha" name="senha" required>
            </div>

            <div class="button-container">
                <button type="submit" class="btn">Cadastrar</button>
            </div>
        </div>
    </form>
</section>
    <!-- Script feedback ao usuário -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="js/validacao-cadastro-usuarios.js" type="text/javascript"></script>

    <!-- Verificação de sucesso no servlet -->
    <% 
        String message = (String) request.getAttribute("message");
        String status = (String) request.getAttribute("status");
    %>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const status = "<%= status %>";
            const message = "<%= message %>";
                       
            // Exibe o SweetAlert2 somente se houver uma mensagem válida
            if (status && message && message !== "null") {
                Swal.fire({
                    icon: status === "success" ? "success" : "error",
                    title: status === "success" ? "Sucesso!" : "Erro!",
                    text: message
                });
            }
        });
    </script>
    
    
    <script type="text/javascript">
    function verificarUnico(tipo, valor, campo) {
        $.ajax({
            url: 'controlador-cadastros',  // URL do servlet
            method: 'GET',
            data: { action: 'verificarUnico', tipo: tipo, valor: valor },
            success: function(response) {
                if (response === 'true') {
                    $(campo).css('border-color', 'red');
                    $(campo).next('.erro').remove();  // Remove qualquer mensagem anterior
                    $(campo).after('<span class="erro" style="color:red;">' + tipo.charAt(0).toUpperCase() + tipo.slice(1) + ' já cadastrado!</span>');
                } else {
                    $(campo).css('border-color', 'green');
                    $(campo).next('.erro').remove();  // Remove a mensagem de erro se o valor for válido
                }
            },
            error: function() {
                console.error('Erro ao verificar ' + tipo);
            }
        });
    }  		
	</script>