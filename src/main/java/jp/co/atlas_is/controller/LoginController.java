package jp.co.atlas_is.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
    @RequestMapping("/")
    public String index() {
        return "login";
    }
}