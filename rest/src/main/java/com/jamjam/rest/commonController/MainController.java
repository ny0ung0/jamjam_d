package com.jamjam.rest.commonController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.User;

@Controller
public class MainController {

	@Autowired
	UserDao userMapper;
	
	
	@GetMapping("/")
    public String root(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userMapper.findByEmail(email);
            String name = user.getName();
            model.addAttribute("email", email);
            model.addAttribute("name", name);
        }
        return "index";
    }
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupForm(@AuthenticationPrincipal OAuth2User principal, Model model) {
	    if (principal != null) {
	        String email = principal.getAttribute("email");
	        String name = principal.getAttribute("name");

	        model.addAttribute("email", email);
	        model.addAttribute("name", name);
	    }
	    return "signup";
	}

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> processSignup(@RequestBody User user) {
        // 사용자 추가 정보를 저장하는 로직 구현
        userMapper.updateUser(user);
        return ResponseEntity.ok().body("Signup successful");
    }
    
}
