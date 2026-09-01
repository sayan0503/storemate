package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.entity.Stores;
import main.entity.User;
import main.services.StoreService;

@Controller
public class StoreController {

	@Autowired
	StoreService service;
	
	@GetMapping("/store/add")
	public String addstorepage(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		model.addAttribute("store", new Stores());
		return "addStore";
	}
	
	@PostMapping("/store/add")
	public String addstore(@ModelAttribute("store") Stores s, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		s.setUser(u);
		boolean status = service.addStore(s);
		if(status) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute("error", "An error occured! Store could not be created!");
			return "/store/add";
		}
	}
	
	@GetMapping("/store/{id}")
	public String storeDashboard(@PathVariable long id, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null) {
            return "redirect:/goLogin";
        }
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		Stores s = service.getStoresbyId(id);
		model.addAttribute("store", s);
		return "storeDashboard";
	}
	
	@GetMapping("/store/{id}/deletestore")
	public String deleteStore(@PathVariable long id, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		if(session==null) {
			return "redirect:/goLogin";
		}
		
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		Stores s = service.getStoresbyId(id);
		boolean status = service.deleteStore(s);
		if(status) {
			return "redirect:/dashboard";
		}
		return "storeDashboard";
	}
}
