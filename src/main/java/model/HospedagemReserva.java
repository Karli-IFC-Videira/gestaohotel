package model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}