package model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Vendas {
    private int codigoVenda;      
    private String nomeProduto;   
    private int quantidade;          
    private String marca;          
    private double precoVenda;   
    private LocalDate dataVenda; 
    private String nomeUsuario;
    
	public Vendas(int codigoVenda, String nomeProduto, int quantidade, String marca, double precoVenda,
			LocalDate dataVenda, String nomeUsuario) {
		this.codigoVenda = codigoVenda;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.marca = marca;
		this.precoVenda = precoVenda;
		this.dataVenda = dataVenda;
		this.nomeUsuario = nomeUsuario;
	}
}