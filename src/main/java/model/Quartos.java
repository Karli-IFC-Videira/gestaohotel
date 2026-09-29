package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quartos {
    private int numeroQuarto;
    private String tipoQuarto;
    private double valorDiaria; 
    private String estatus;
    
    public Quartos() {}

    public Quartos(int numeroQuarto, String tipoQuarto, double valorDiaria, String estatus) {
        this.numeroQuarto = numeroQuarto;
        this.tipoQuarto = tipoQuarto;
        this.valorDiaria = valorDiaria;
        this.estatus = estatus;
    }
}