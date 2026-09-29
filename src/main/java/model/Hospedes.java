package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hospedes {
	private String cpfHospede;
	private String nomeHospede;
	private String sobrenomeHospede;
	private String empresa;
	private String cpfUsuario;
    private String nomeUsuario;
	private int codigoReserva;
	
	public Hospedes(String cpfHospede, String nomeHospede, String sobrenomeHospede, String empresa,String nomeUsuario, String cpfUsuario,
			int codigoReserva) {
		this.cpfHospede = cpfHospede;
		this.nomeHospede = nomeHospede;
		this.sobrenomeHospede = sobrenomeHospede;
		this.empresa = empresa;
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
		this.codigoReserva = codigoReserva;
	}
}