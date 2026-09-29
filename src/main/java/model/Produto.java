package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private int codigoProduto;
    private String nomeProduto;
    private int quantidade;
    private double precoCompra;
    private double precoVenda;
    private String marca;
    private String cpfUsuario;
    private String nomeUsuario;
    
    public Produto() {}
    
	public Produto(String nomeProduto, int quantidade, double precoCompra, double precoVenda, String marca,
			String cpfUsuario, String nomeUsuario) {
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.marca = marca;
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
	} 
}