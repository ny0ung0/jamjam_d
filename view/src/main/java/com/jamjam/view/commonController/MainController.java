package com.jamjam.view.commonController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		return "index";
	}
	@RequestMapping("leindex")
	public void lee() {
		
	}
	@RequestMapping("nindex")
	public void n() {
		
	}
}