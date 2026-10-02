package model;

public class Usuario {
	private String cpfUsuario;
	private String nomeUsuario;
	private String sobrenomeUsuario;
	private String dataNascimento;
	private String nivelAcesso;
	private String email;
	private String celular;
	private String nomeLogin;
	private String senha;
	
	public Usuario() {}
	
	public Usuario(String cpfUsuario, String nomeUsuario, String sobrenomeUsuario, String dataNascimento, String nivelAcesso,
			String email, String celular, String nomeLogin, String senha) {
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
		this.sobrenomeUsuario = sobrenomeUsuario;
		this.dataNascimento = dataNascimento;
		this.nivelAcesso = nivelAcesso;
		this.email = email;
		this.celular = celular;
		this.nomeLogin = nomeLogin;
		this.senha = senha;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSobrenomeUsuario() {
		return sobrenomeUsuario;
	}

	public void setSobrenomeUsuario(String sobrenomeUsuario) {
		this.sobrenomeUsuario = sobrenomeUsuario;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNomeLogin() {
		return nomeLogin;
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}