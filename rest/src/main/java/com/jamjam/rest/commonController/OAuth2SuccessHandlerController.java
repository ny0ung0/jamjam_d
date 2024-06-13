package com.jamjam.rest.commonController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class OAuth2SuccessHandlerController {

	@Autowired
	UserDao userMapper;
	@Autowired
    private HttpSession httpSession;
	
	@GetMapping("/oauth2/success")
	public String handleOAuth2Success(@AuthenticationPrincipal OAuth2User principal, Model model) {
		httpSession.setAttribute("additionalInfoRequired", true);
		String email = principal.getAttribute("email");
		User user = userMapper.findByEmail(email);
		if(user.getGender() != null) {
			return "redirect:/";
		}else {
	    
	    return "redirect:/signup";
		}
	}
}
