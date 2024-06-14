package com.jamjam.rest.commonController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.CompanyDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.Company;
import com.jamjam.rest.dto.User;

@RestController
public class MainController {

	@Autowired
	UserDao userMapper;
	@Autowired
	CompanyDao companyMapper;
	
	
	@GetMapping("/userInfo")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userMapper.findByEmail(email);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> processSignup(@RequestBody User user) {
        // 사용자 추가 정보를 저장하는 로직 구현
        userMapper.updateUser(user);
        return ResponseEntity.ok().body("Signup successful");
    }
    
    @PostMapping("/signup/company")
    public ResponseEntity<?> processSignup(@RequestBody Company company){
    	System.out.println("기업회원가입 컨틀롤러 들어옴...");
    	companyMapper.insertCompany(company);
    	return ResponseEntity.ok().body("Signup successful");
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
    
}
