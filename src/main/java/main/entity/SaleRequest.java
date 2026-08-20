package main.entity;

import java.util.List;

public class SaleRequest {

	private List<SaleProductRequest> products;

	public SaleRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleRequest(List<SaleProductRequest> products) {
		super();
		this.products = products;
	}

	public List<SaleProductRequest> getProducts() {
		return products;
	}

	public void setProducts(List<SaleProductRequest> products) {
		this.products = products;
	}
	
	
}
