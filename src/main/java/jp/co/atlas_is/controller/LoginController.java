package jp.co.atlas_is.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String rootForm(Model model) {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("login", "error", error);
	}
	
	@GetMapping("/top")
	public String topForm(Principal principal, Model model) {
		Authentication authentication = (Authentication)principal;
		String username = authentication.getName();
		model.addAttribute("username", username);
		return "top";
	}
}