package model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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
}