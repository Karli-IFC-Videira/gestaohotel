package model;

import java.util.List;

public class VendaRequest {
    private double totalVenda;
    private List<VendasItem> items;
    
	public VendaRequest(double totalVenda, List<VendasItem> items) {
		this.totalVenda = totalVenda;
		this.items = items;
	}

	public double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(double totalVenda) {
		this.totalVenda = totalVenda;
	}

	public List<VendasItem> getItems() {
		return items;
	}

	public void setItems(List<VendasItem> items) {
		this.items = items;
	}
}