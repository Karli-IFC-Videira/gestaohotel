package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}