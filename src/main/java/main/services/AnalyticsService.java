package main.services;

import main.entity.AnalyticsData;

public interface AnalyticsService {

	public AnalyticsData getAnalytics(Long storeId);
}
