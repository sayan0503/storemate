package main.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.entity.Products;
import main.entity.SaleItems;
import main.entity.SaleProductRequest;
import main.entity.Sales;
import main.entity.Stores;
import main.repositories.ProductRepository;
import main.repositories.SaleItemsRepository;
import main.repositories.SalesRepository;

@Service
public class SalesServiceAccess implements AllSalesService{

	@Autowired
	private SalesRepository salesRepo;
	
	@Autowired
	private SaleItemsRepository itemRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional
	public boolean completeSell(Stores store, List<SaleProductRequest> products) {
		try {
			Sales sale = new Sales();
			sale.setStore(store);
			sale.setSaleDate(LocalDateTime.now());
			sale.setTotalAmount(BigDecimal.ZERO);
			
			BigDecimal total = BigDecimal.ZERO;
			
			for(SaleProductRequest request: products) {
				Products p = productRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
				int quantity = request.getQuantity();
				
				if(quantity <= 0) {
					throw new RuntimeException("Invalid Quantity");
				}
				
				if(p.getQuantity() < quantity) {
					throw new RuntimeException("Not enough stock for "+p.getName());
				}
				
				BigDecimal price = p.getSellPrice();
				BigDecimal subtotal = price.multiply(BigDecimal.valueOf(quantity));
				
				SaleItems item = new SaleItems();
				item.setSale(sale);
				item.setProduct(p);
				item.setQuantity(quantity);
				item.setPrice(price);
				item.setSubTotal(subtotal);
				
				itemRepo.save(item);
				p.setQuantity(p.getQuantity()-quantity);
				productRepo.save(p);
				total = total.add(subtotal);
			}
			
			sale.setTotalAmount(total);
			salesRepo.save(sale);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
