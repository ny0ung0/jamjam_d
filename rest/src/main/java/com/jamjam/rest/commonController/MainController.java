package com.jamjam.rest.commonController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamjam.rest.dao.CompanyDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.Company;
import com.jamjam.rest.dto.User;
import com.jamjam.rest.service.MailService;

@RestController
public class MainController {

	@Autowired
	UserDao userMapper;
	@Autowired
	CompanyDao companyMapper;
	@Autowired
	MailService mailService;
	
	String emailContent = "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<style>"
            + "  .header { background-color: #f2f2f2; padding: 10px; text-align: center; }"
            + "  .content { font-family: Arial, sans-serif; margin: 20px; }"
            + "  .button { background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; margin: 10px 0; border-radius: 4px; }"
            + "  .footer { background-color: #f2f2f2; padding: 10px; text-align: center; font-size: 12px; }"
            + "</style>"
            + "</head>"
            + "<body>"
            + "  <div class=\"header\">"
            + "    <h1>Welcome to Our Service</h1>"
            + "  </div>"
            + "  <div class=\"content\">"
            + "    <p>Hello,</p>"
            + "    <p>Thank you for joining our service. We are excited to have you on board.</p>"
            + "    <a href=\"http://localhost:9999/nindex\" class=\"button\">Get Started</a>"
            + "  </div>"
            + "  <div class=\"footer\">"
            + "    <p>&copy; 2024 Our Service, Inc. All rights reserved.</p>"
            + "  </div>"
            + "</body>"
            + "</html>";
	
	 @GetMapping("/userInfo")
	    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
	        if (principal == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
	        }
	        
	        // 예시로, 사용자의 이메일을 통해 추가 정보 여부를 확인하는 로직
	        String email = principal.getAttribute("email");
	        User user = userMapper.findByEmail(email);
	        
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	        
	        boolean hasAdditionalInfo = user.getAddress() != null;
	        Map<String, Object> response = new HashMap<>();
	        response.put("name", user.getName());
	        response.put("email", user.getEmail());
	        response.put("role", user.getRole()); // 역할 정보를 추가합니다.
	        response.put("user_id",user.getUser_id());
	        response.put("contact", user.getContact());
	        response.put("address", user.getAddress());
	        response.put("birth_date", user.getBirth_date());
	        response.put("gender", user.getGender());
	        response.put("hasAdditionalInfo", hasAdditionalInfo);
	        
	        return ResponseEntity.ok(response);
	    }
	 

    @PutMapping("/signup")
    public ResponseEntity<?> processSignup(@RequestBody Map<String, Object> requestData) {
    	
    	try {
            User user = new User();
            user.setEmail((String) requestData.get("email"));
            user.setName((String) requestData.get("name"));
            user.setContact((String) requestData.get("contact"));
            user.setBirth_date( (String) requestData.get("birth_date"));
            user.setGender((String) requestData.get("gender"));

            // Create address JSON
            Map<String, String> addressMap = new HashMap<>();
            addressMap.put("city", (String) requestData.get("city"));
            addressMap.put("district", (String) requestData.get("district"));
            addressMap.put("address", (String) requestData.get("address"));

            ObjectMapper objectMapper = new ObjectMapper();
            String addressJson = objectMapper.writeValueAsString(addressMap);
            user.setAddress(addressJson);

            // Save user
            userMapper.updateUser(user);
            System.out.println(user.getEmail());
            User userid = userMapper.findByEmail(user.getEmail());
            System.out.println(userid);
            mailService.sendHTMLEmail(user.getEmail(), "jamjam에서 알려드립니다", emailContent);
            return ResponseEntity.ok().body("Signup successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Signup failed: " + e.getMessage());
        }
        
    }
    
    @PutMapping("/updateUserInfo")
    public ResponseEntity<?> updateUserInfo(@RequestBody Map<String, Object> requestData) {
    	try {
            User user = new User();
            user.setEmail((String) requestData.get("email"));
            user.setName((String) requestData.get("name"));
            user.setContact((String) requestData.get("contact"));

            // Create address JSON
            Map<String, String> addressMap = new HashMap<>();
            addressMap.put("city", (String) requestData.get("city"));
            addressMap.put("district", (String) requestData.get("district"));
            addressMap.put("address", (String) requestData.get("address"));

            ObjectMapper objectMapper = new ObjectMapper();
            String addressJson = objectMapper.writeValueAsString(addressMap);
            user.setAddress(addressJson);

            // Save user
            userMapper.updateUserAfterInfo(user);
            System.out.println(user.getEmail());
            User userid = userMapper.findByEmail(user.getEmail());
            System.out.println(userid);
            return ResponseEntity.ok().body("Signup successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Signup failed: " + e.getMessage());
        }
        
    }
    
    
    
    
    @PostMapping("/signup/company")
    public ResponseEntity<?> processSignup(@RequestBody Company company){
    	System.out.println("기업회원가입 컨틀롤러 들어옴...");
    	try {
    		companyMapper.insertCompany(company);
    		mailService.sendHTMLEmail(company.getEmail(), "jamjam에서 알려드립니다", emailContent);
        	return ResponseEntity.ok().body("Signup successful");
    	}catch(Exception e) {
    		return  ResponseEntity.status(500).body("Signup failed: " + e.getMessage());
    	}
    	
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
