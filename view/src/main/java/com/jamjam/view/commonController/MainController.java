package com.jamjam.view.commonController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		return "/nindex";
	}
	@RequestMapping("leindex")
	public void lee(HttpSession session,@RequestParam("company_id")Integer company_id) {
		session.setAttribute("company_id", company_id);
		System.out.println(company_id);
	}
	@RequestMapping("nindex")
	public void n() {
		
	}
	
	@RequestMapping("usermain")
	public void usermain() {
		
	}
	
	
	
}
