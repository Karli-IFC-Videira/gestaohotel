package model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaRequest {
    private double totalVenda;
    private List<VendasItem> items;
    
	public VendaRequest(double totalVenda, List<VendasItem> items) {
		this.totalVenda = totalVenda;
		this.items = items;
	}
}