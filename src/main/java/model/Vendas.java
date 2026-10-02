package model;

import java.time.LocalDate;

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

	public int getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(int codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}