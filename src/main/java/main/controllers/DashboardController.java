package main.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.entity.Products;
import main.entity.StoreReport;
import main.entity.Stores;
import main.entity.TopSellingProduct;
import main.entity.User;
import main.repositories.SaleItemsRepository;
import main.services.ProductService;
import main.services.StoreService;

@Controller
public class DashboardController {

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleItemsRepository itemRepo;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		List<Stores> stores = storeService.allStores(u);
		List<Products> products = productService.getAllProductsFromAllStores(stores);
		model.addAttribute("stores", stores);
		model.addAttribute("products", products);
		
		return "dashboard";
	}
	
	@GetMapping("/allInventory")
	public String allInventory(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		List<Stores> stores = storeService.allStores(u);
		
		Map<Long, Long> soldQty = new HashMap<>();
		Map<Long, List<Products>> storeProducts = new HashMap<>();
		
		int totalProduct = 0;
		for(Stores store: stores) {
			
			List<Products> products = productService.allProducts(store);
			storeProducts.put(store.getId(), products);
			totalProduct += products.size();
		
			for(Products product: products) {
				Long sold = itemRepo.getTotalSoldByProduct(product.getId());
				soldQty.put(product.getId(), sold);
			}
		}
		model.addAttribute("soldQuantity", soldQty);
		model.addAttribute("stores", stores);
		model.addAttribute("storeProducts", storeProducts);
		model.addAttribute("totalProducts", totalProduct);
		
		return "allInventory";
	}
	
	@GetMapping("/reports")
	public String reports(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		List<Stores> stores = storeService.allStores(u);
		BigDecimal totalRevenue = itemRepo.getTotalRevenue(stores);
		BigDecimal totalProfit =  itemRepo.getTotalProfit(stores);
		List<TopSellingProduct> topProducts = itemRepo.findMostSellingProduct(stores);
		TopSellingProduct topProduct = null;
		
		if(!topProducts.isEmpty()) {
			topProduct = topProducts.get(0);
		}
		
		Map<Long, StoreReport> storeReport = new HashMap<>();
		for(Stores store: stores) {
			BigDecimal revenue=itemRepo.getRevenueByStore(store);
			BigDecimal profit=itemRepo.getProfitByStore(store);
			List<TopSellingProduct> top=itemRepo.findTopSellingProducts(store);
			
			String itemName = "No Sales Yet";
			Long qty = 0L;
			
			if(!top.isEmpty()) {
				itemName=top.get(0).getProductName();
				qty=top.get(0).getTotalQty();
			}
			
			StoreReport report = new StoreReport(store.getName(), revenue, profit, itemName, qty);
			storeReport.put(store.getId(), report);
		}
		
		model.addAttribute("totalRevenue", totalRevenue);
		model.addAttribute("totalProfit", totalProfit);
		model.addAttribute("mostSellingProduct", topProduct);
		model.addAttribute("storeReport", storeReport);
		model.addAttribute("stores", stores);
		
		return "reports";
	}
}
