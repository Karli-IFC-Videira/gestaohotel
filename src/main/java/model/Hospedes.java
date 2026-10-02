package model;

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

	public String getCpfHospede() {
		return cpfHospede;
	}

	public void setCpfHospede(String cpfHospede) {
		this.cpfHospede = cpfHospede;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public String getSobrenomeHospede() {
		return sobrenomeHospede;
	}

	public void setSobrenomeHospede(String sobrenomeHospede) {
		this.sobrenomeHospede = sobrenomeHospede;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
}