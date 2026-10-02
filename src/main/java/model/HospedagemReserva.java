package model;

import java.time.LocalDate;

public class HospedagemReserva {
    private int codigoReserva;
    private LocalDate dataReserva;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String cpfUsuario;
    private String nomeUsuario;

    public HospedagemReserva(int codigoReserva, LocalDate dataReserva, LocalDate dataEntrada, LocalDate dataSaida, String cpfUsuario, String nomeUsuario) {
        this.codigoReserva = codigoReserva;
        this.dataReserva = dataReserva;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.cpfUsuario = cpfUsuario;
        this.nomeUsuario = nomeUsuario;
    }

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDate dataReserva) {
		this.dataReserva = dataReserva;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
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
}