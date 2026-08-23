package main.services;

import java.util.List;

import main.entity.AnalyticsData;
import main.entity.Stores;
import main.entity.TopSellingProduct;

public interface AnalyticsService {

	public AnalyticsData getAnalytics(Long storeId);
	List<TopSellingProduct> getTopSellingProducts(Stores store);
}
