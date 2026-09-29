<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />

<section class="container-fluid">
	<div class="row">
		<div class="col-md-6 image-side d-none d-md-flex"></div>
		<div class="col-md-6 login-side">
			<div class="login-container">
				<img src="imagens/Hotel-Scalabrin-(1).png"
					alt="Logo Hotel Scalabrin">
				<h1>Login</h1>

				<form id="loginForm" action="controlador-login" method="post">
					<div class="form-outline mb-4">
						<input type="text" name="txtUsuario" id="email"
							class="form-control" placeholder=" " aria-label="Usuário"
							autocomplete="on" value="${sessionScope.usuario}"> <label
							class="form-label" for="email">Usuário</label>
					</div>

					<div class="form-outline mb-4">
						<input type="password" name="txtSenha" id="password"
							class="form-control" placeholder=" " aria-label="Senha"
							autocomplete="current-password"> <label
							class="form-label" for="password">Senha</label>
					</div>

					<div class="form-check form-check-inline mb-4">
						<input class="form-check-input" type="checkbox" id="showPassword"
							aria-label="Mostrar senha" onclick="togglePassword()"> <label
							class="form-check-label" for="showPassword">Mostrar senha</label>
					</div>

					<input type="submit" name="ingressar" value="Login"
						class="btn btn-primary">
				</form>
			</div>
		</div>
	</div>
</section>

<!-- Script para alternar a visibilidade da senha -->
<script src="js/mostrarSenha.js"></script>

<!-- Script para exibir a mensagem de erro -->
<script>
	document.addEventListener("DOMContentLoaded", function() {
		const errorMessage = "${sessionScope.errorMessage}";
		if (errorMessage && errorMessage !== "") {
			Swal.fire({
				icon : 'error',
				title : 'Erro',
				text : errorMessage
			});
			// Limpa a mensagem de erro da sessão
			<%session.removeAttribute("errorMessage");%>
	}
			// Limpa o nome de usuário da sessão após usá-lo
			<%session.removeAttribute("usuario");%>
	});
</script>