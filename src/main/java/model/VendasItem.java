package model;

import lombok.Getter;
import lombok.Setter;

//Classe para representar um item da venda
@Getter
@Setter
public class VendasItem {
	private int codigoProduto;
	private String nomeProduto;
	private int quantidade;
	private double subtotal;
	private double valorVenda;
	private String nomeLogin;
	
	public VendasItem(int codigoProduto, String nomeProduto, int quantidade, double subtotal, double valorVenda,
			String nomeLogin) {
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.subtotal = subtotal;
		this.valorVenda = valorVenda;
		this.nomeLogin = nomeLogin;
	}
}