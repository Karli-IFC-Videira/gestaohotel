package model;

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

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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