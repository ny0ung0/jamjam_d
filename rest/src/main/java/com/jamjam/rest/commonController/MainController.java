package com.jamjam.rest.commonController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jamjam.rest.config.RedirectOAuth2User;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.User;

@Controller
public class MainController {

	@Autowired
	UserDao userMapper;
	
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        return "index";
    }
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
    public String showSignupForm(@AuthenticationPrincipal OAuth2User principal, Model model) {
        User user = new User();
        user.setEmail(principal.getAttribute("email"));
        user.setName(principal.getAttribute("name"));
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, Model model) {
        // 사용자 추가 정보를 저장하는 로직 구현
        userMapper.updateUser(user);
        return "redirect:/home";
    }
    
    @GetMapping("/oauth2/success")
    public String handleOAuth2Success(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal instanceof RedirectOAuth2User) {
            return "redirect:" + ((RedirectOAuth2User) principal).getRedirectUrl();
        }
        return "redirect:/";
    }
    
}
