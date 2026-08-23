package main.entity;

import java.math.BigDecimal;

public class TopSellingProduct {

	private String productName;
	private Long totalQty;
	private BigDecimal revenue;
	
	public TopSellingProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopSellingProduct(String productName, Long totalQty, BigDecimal revenue) {
		super();
		this.productName = productName;
		this.totalQty = totalQty;
		this.revenue = revenue;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Long totalQty) {
		this.totalQty = totalQty;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	
	
}
