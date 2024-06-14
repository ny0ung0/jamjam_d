package com.jamjam.view.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("resume")
	public String resumeForm() {
		return "/user/resumeForm";
	}
	
	
}
