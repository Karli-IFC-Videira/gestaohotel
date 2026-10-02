package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hospedagem {
    private Integer codigoReserva; 
    private LocalDate dataReserva;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String nomeCliente;
    private String sobrenomeCliente;
    private String empresa;
    private String cpfHospede; 
    private int numeroQuarto;
    private String tipoQuartoReservado;
    private String statusQuarto;
    private BigDecimal valorQuarto; 

    // Construtor padrão
    public Hospedagem() {}

    // Construtor com parâmetros usando anotações do Jackson
    @JsonCreator
    public Hospedagem(
            @JsonProperty("codigoReserva") Integer codigoReserva, 
            @JsonProperty("dataReserva") LocalDate dataReserva,
            @JsonProperty("dataEntrada") LocalDate dataEntrada,
            @JsonProperty("dataSaida") LocalDate dataSaida,
            @JsonProperty("nomeCliente") String nomeCliente,
            @JsonProperty("sobrenomeCliente") String sobrenomeCliente,
            @JsonProperty("empresa") String empresa,
            @JsonProperty("cpfHospede") String cpfHospede, 
            @JsonProperty("numeroQuarto") int numeroQuarto,
            @JsonProperty("tipoQuartoReservado") String tipoQuartoReservado,
            @JsonProperty("statusQuarto") String statusQuarto,
            @JsonProperty("valorQuarto") BigDecimal valorQuarto) {
        this.codigoReserva = codigoReserva;
        this.dataReserva = dataReserva;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.empresa = empresa;
        this.cpfHospede = cpfHospede;
        this.numeroQuarto = numeroQuarto;
        this.tipoQuartoReservado = tipoQuartoReservado;
        this.statusQuarto = statusQuarto;
        this.valorQuarto = valorQuarto;
    }

	public Integer getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(Integer codigoReserva) {
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getSobrenomeCliente() {
		return sobrenomeCliente;
	}

	public void setSobrenomeCliente(String sobrenomeCliente) {
		this.sobrenomeCliente = sobrenomeCliente;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCpfHospede() {
		return cpfHospede;
	}

	public void setCpfHospede(String cpfHospede) {
		this.cpfHospede = cpfHospede;
	}

	public int getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(int numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public String getTipoQuartoReservado() {
		return tipoQuartoReservado;
	}

	public void setTipoQuartoReservado(String tipoQuartoReservado) {
		this.tipoQuartoReservado = tipoQuartoReservado;
	}

	public String getStatusQuarto() {
		return statusQuarto;
	}

	public void setStatusQuarto(String statusQuarto) {
		this.statusQuarto = statusQuarto;
	}

	public BigDecimal getValorQuarto() {
		return valorQuarto;
	}

	public void setValorQuarto(BigDecimal valorQuarto) {
		this.valorQuarto = valorQuarto;
	}
}