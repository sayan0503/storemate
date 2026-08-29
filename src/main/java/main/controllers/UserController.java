package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.entity.User;
import main.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/GoRegister")
	public String registerForm(Model model) {
		model.addAttribute("User",new User());
		return "register";
	}
	
	@PostMapping("/registerForm")
	public String registerComp(@ModelAttribute("User") User u, @RequestParam("confirmPassword") String cpass, Model model) {
		
		if(!u.getPassword().equals(cpass)) {
			model.addAttribute("error", "Password do not match!!");
			return "register";
		}
		else {
			boolean status = service.register(u);
			try {
				if(status) {
					return "redirect:/goLogin";
				}
				else {
					model.addAttribute("error", "Registration Unsuccessfull!!!");
					return "register";
				}
			}catch(Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "Something went wrong!!");
				return "register";
			}
		}
		
	}
	
	@GetMapping("/goLogin")
	public String loginForm(Model model) {
		model.addAttribute("User",new User());
		return "login";
	}
	
	@PostMapping("/loginUser")
	public String loginComp(@ModelAttribute("User") User u, Model model, HttpServletRequest req) {
		User user = service.login(u.getEmail(), u.getPassword());
		if(user!=null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			return "redirect:/dashboard";
		}
		else {
			model.addAttribute("error", "login Unsuccess");
			return "login";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/goLogin";
	}
	
	
	@GetMapping("/user/{id}")
	public String reports(Model model, HttpServletRequest req, @PathVariable Long id ) {
		HttpSession session = req.getSession(false);
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		
		model.addAttribute("user", u);
		return "accountDashboard";
	}
	
	
	@PostMapping("/user/{id}/update")
	public String profile(Model model, HttpServletRequest req, @PathVariable Long id, @ModelAttribute("user") User user) {
		HttpSession session = req.getSession(false);
		User u = (User)session.getAttribute("user");
		if(u==null) {
			return "redirect:/goLogin";
		}
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		boolean status = service.update(u);
		if(status) {
			return "accountDashboard";
		}
		else {
			model.addAttribute("error", "Updatation Failed!");
			return "accountDashboard";
		}
	}
}
