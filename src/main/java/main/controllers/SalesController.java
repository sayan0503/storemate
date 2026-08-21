package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.entity.Products;
import main.entity.SaleRequest;
import main.entity.Stores;
import main.entity.User;
import main.services.AllSalesService;
import main.services.ProductService;
import main.services.StoreService;

@Controller
public class SalesController {

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AllSalesService service;
	
	@GetMapping("/store/{id}/sell")
	public String sellPage(@PathVariable Long id, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		User u = (User) session.getAttribute("user");
		if(u == null) {
			return "redirect:/goLogin";
		}
		
		System.out.println(id);
		Stores s = storeService.getStoresbyId(id);
		if(s == null) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("store", s);
		model.addAttribute("request", new SaleRequest());
		return "sell";
	}
	
	@GetMapping("/store/{storeId}/sell/product/{productId}")
	@ResponseBody
	public Products getProductForSale(@PathVariable long storeId, @PathVariable Long productId, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		User u = (User) session.getAttribute("user");
		if(u == null) {
			return null;
		}
		
		Stores s = storeService.getStoresbyId(storeId);
		Products p = productService.getProducts(productId);
		
		if(p == null) {
			return null;
		}
		
		if(!p.getStore().getId().equals(s.getId())) {
			return null;
		}
		
		return p;
	}
	
	@PostMapping("/store/{storeId}/sell")
	@ResponseBody
	public String completeSale(@PathVariable long storeId, @RequestBody SaleRequest request, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		
		if(session==null) {
			return "LOGIN";
		}
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "LOGIN";
		}
		
		Stores s = storeService.getStoresbyId(storeId);
		
		if(s==null) {
			return "Store not found";
		}
		try {
			boolean status = service.completeSell(s, request.getProducts());
			if(status) {
				return "SUCCESS";
			}
			return "FAILED";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	
}
