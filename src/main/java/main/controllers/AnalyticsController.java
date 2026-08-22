package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.entity.AnalyticsData;
import main.entity.Stores;
import main.entity.User;
import main.services.AnalyticsService;
import main.services.StoreService;

@Controller
public class AnalyticsController {

	@Autowired
	private AnalyticsService service;
	
	@Autowired
	private StoreService storeService;
	
	@GetMapping("/store/{storeId}/analytics")
	public String analytics(@PathVariable Long storeId,  Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);

        if (session == null) {
            return "redirect:/goLogin";
        }

        User u = (User) session.getAttribute("user");

        if (u == null) {
            return "redirect:/goLogin";
        }

        Stores store = storeService.getStoresbyId(storeId);

        if (store == null) {
            return "redirect:/dashboard";
        }
        
        AnalyticsData data = service.getAnalytics(storeId);
        model.addAttribute("store", store);
        model.addAttribute("analytics", data);
        
        return "analytics";
	}
}
