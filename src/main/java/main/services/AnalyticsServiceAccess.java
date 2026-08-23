package main.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.entity.AnalyticsData;
import main.entity.SaleItems;
import main.entity.Sales;
import main.entity.Stores;
import main.entity.TopSellingProduct;
import main.repositories.SaleItemsRepository;
import main.repositories.SalesRepository;
import main.repositories.StoreRepository;

@Service
public class AnalyticsServiceAccess implements AnalyticsService {

	@Autowired
	private SalesRepository salesRepo;
	
	@Autowired
	private SaleItemsRepository itemRepo;
		
	
	@Override
	public AnalyticsData getAnalytics(Long storeId) {
		
		List<Sales> sales = salesRepo.findAllByStoreIdOrderBySaleDateDesc(storeId);
		List<SaleItems> items = itemRepo.findAllBySale_StoreId(storeId);
		
		BigDecimal revenue = BigDecimal.ZERO;
		BigDecimal profit = BigDecimal.ZERO;
		
		int itemSold = 0;
		
		
		for(SaleItems item: items) {
			revenue = revenue.add(item.getSubTotal());
			
			itemSold += item.getQuantity();
			
			BigDecimal itemProfit = item.getPrice().subtract(item.getCostPrice()).multiply(BigDecimal.valueOf(item.getQuantity()));
			profit = profit.add(itemProfit);
		}
		
		AnalyticsData data = new AnalyticsData();
		data.setTotalRevenue(revenue);
		data.setTotalProfit(profit);
		data.setTotalSales(sales.size());
		data.setItemSold(itemSold);
		
		data.setRecentSales(sales.stream().limit(5).toList());
		data.setTopProducts(items.stream().sorted((a,b) -> Integer.compare(b.getQuantity(),a.getQuantity())).limit(5).toList());
		
		
		return data;
	}


	@Override
	public List<TopSellingProduct> getTopSellingProducts(Stores store) {
		
		return itemRepo.findTopSellingProducts(store);
	}

}
