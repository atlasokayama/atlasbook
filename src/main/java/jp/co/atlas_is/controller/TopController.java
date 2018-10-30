package jp.co.atlas_is.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("top")
public class TopController {
	
	@RequestMapping(params = "list", method = RequestMethod.POST)
    ModelAndView list() {
		ModelAndView mav = new ModelAndView("list");
		return mav;
    }
	
	@RequestMapping(params = "edit", method = RequestMethod.POST)
    ModelAndView edit() {
		ModelAndView mav = new ModelAndView("edit");
		return mav;
    }
	
	@RequestMapping(params = "master", method = RequestMethod.POST)
    ModelAndView master() {
		ModelAndView mav = new ModelAndView("master");
		return mav;
    }
	
}