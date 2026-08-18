package main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.entity.Stores;
import main.entity.User;
import main.services.StoreService;

@Controller
public class DashboardController {

	@Autowired
	StoreService storeService;
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		List<Stores> stores = storeService.allStores(u);
		model.addAttribute("stores", stores);
		return "dashboard";
	}
	
}
