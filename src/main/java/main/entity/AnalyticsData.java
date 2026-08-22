package main.entity;

import java.math.BigDecimal;
import java.util.List;

public class AnalyticsData {

	private BigDecimal totalRevenue;
	private BigDecimal totalProfit;
	private long totalSales;
	private int itemSold;
	
	private List<Sales> recentSales;
	private List<SaleItems> topProducts;
	
	public AnalyticsData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnalyticsData(BigDecimal totalRevenue, BigDecimal totalProfit, long totalSales, int itemSold,
			List<Sales> recentSales, List<SaleItems> topProducts) {
		super();
		this.totalRevenue = totalRevenue;
		this.totalProfit = totalProfit;
		this.totalSales = totalSales;
		this.itemSold = itemSold;
		this.recentSales = recentSales;
		this.topProducts = topProducts;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(long totalSales) {
		this.totalSales = totalSales;
	}

	public int getItemSold() {
		return itemSold;
	}

	public void setItemSold(int itemSold) {
		this.itemSold = itemSold;
	}

	public List<Sales> getRecentSales() {
		return recentSales;
	}

	public void setRecentSales(List<Sales> recentSales) {
		this.recentSales = recentSales;
	}

	public List<SaleItems> getTopProducts() {
		return topProducts;
	}

	public void setTopProducts(List<SaleItems> topProducts) {
		this.topProducts = topProducts;
	}
	
	
}
