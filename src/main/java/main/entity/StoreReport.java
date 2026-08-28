package main.entity;

import java.math.BigDecimal;

public class StoreReport {

	private String storeName;
    private BigDecimal revenue;
    private BigDecimal profit;
    private String mostSellingItem;
    private Long mostSellingQuantity;
	
    public StoreReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreReport(String storeName, BigDecimal revenue, BigDecimal profit, String mostSellingItem,
			Long mostSellingQuantity) {
		super();
		this.storeName = storeName;
		this.revenue = revenue;
		this.profit = profit;
		this.mostSellingItem = mostSellingItem;
		this.mostSellingQuantity = mostSellingQuantity;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getMostSellingItem() {
		return mostSellingItem;
	}

	public void setMostSellingItem(String mostSellingItem) {
		this.mostSellingItem = mostSellingItem;
	}

	public Long getMostSellingQuantity() {
		return mostSellingQuantity;
	}

	public void setMostSellingQuantity(Long mostSellingQuantity) {
		this.mostSellingQuantity = mostSellingQuantity;
	}
    
    
}
