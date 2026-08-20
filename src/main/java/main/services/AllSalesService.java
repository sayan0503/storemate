package main.services;

import java.util.List;

import main.entity.SaleItems;
import main.entity.SaleProductRequest;
import main.entity.Sales;
import main.entity.Stores;

public interface AllSalesService {

	public boolean completeSell(Stores s, List<SaleProductRequest> list );
}
