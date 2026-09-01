package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import main.services.ProductService;
import main.services.StoreService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private StoreService storeService;
	
	@GetMapping("/store/{id}/inventory")
	public String inventory(@PathVariable long id, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		Stores s = storeService.getStoresbyId(id);
		List<Products> products= service.allProducts(s); 
		model.addAttribute("store", s);
		model.addAttribute("products", products);
		return "inventory";
	}
	
	@GetMapping("/store/{id}/inventory/add")
	public String addProductPage(@PathVariable long id, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		Stores s = storeService.getStoresbyId(id);
		model.addAttribute("store", s);
		model.addAttribute("product", new Products());
		return "addproduct";
	}
	
	@PostMapping("/store/{id}/inventory/add")
	public String addProduct(@PathVariable long id, @ModelAttribute("product") Products p, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		Stores s = storeService.getStoresbyId(id);
		if(s == null) {
			model.addAttribute("error", "Something went wrong! Store value is null!!");
			return "addproduct";
		}
		p.setId(null);
		p.setStore(s);
		System.out.print("Product id: "+p.getId());
		boolean status = service.addProduct(p);
		if(status) {
			return "redirect:/store/{id}/inventory";
		}
		else {
			model.addAttribute("store",s);
			model.addAttribute("error", "Something went wrong! Product could not be added!!");
			return "addproduct";
		}
	}
	
	@GetMapping("/store/{storeId}/inventory/delete/{productId}")
	public String delete(Model model, HttpServletRequest req, @PathVariable Long productId, @PathVariable long storeId) {
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		boolean status = service.deleteProduct(productId);
		if(status) {
			return "redirect:/store/"+storeId+"/inventory";
		}
		else {
			model.addAttribute("error", "Something went wrong! Product could not be deleted!!");
			return "redirect:/store/"+storeId+"/inventory";
		}
	}
	
	@GetMapping("/store/{storeId}/inventory/edit/{productId}")
	public String editpage(Model model, HttpServletRequest req, @PathVariable Long productId, @PathVariable long storeId) {
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		Stores s = storeService.getStoresbyId(storeId);
		Products p = service.getProducts(productId);
		model.addAttribute("store", s);
		model.addAttribute("product", p);
		
		return "editproduct";
	}
	
	@PostMapping("/store/{storeId}/inventory/edit/{productId}")
	public String edit(Model model, HttpServletRequest req, @PathVariable Long productId, @PathVariable long storeId, @ModelAttribute("product") Products p) {
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		Products existingProduct = service.getProducts(productId);
		if (existingProduct == null) {
		    return "redirect:/store/" + storeId + "/inventory";
		}
		existingProduct.setQuantity(p.getQuantity());
		existingProduct.setCostPrice(p.getCostPrice());
		existingProduct.setSellPrice(p.getSellPrice());
		
		boolean status = service.updateProduct(existingProduct);
		if(status) {
			return "redirect:/store/"+storeId+"/inventory";
		}
		else {
			model.addAttribute("error", "Something went wrong! Product could not be updated!!");
			return "redirect:/store/"+storeId+"/inventory/edit/"+productId;
		} 
	}
	
	
}
