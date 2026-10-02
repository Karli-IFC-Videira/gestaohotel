package model;

//Classe para representar um item da venda
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

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
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

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getNomeLogin() {
		return nomeLogin;
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}
}