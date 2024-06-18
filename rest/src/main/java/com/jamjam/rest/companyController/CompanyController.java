package com.jamjam.rest.companyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dto.Company;
import com.jamjam.rest.service.MailService;
import com.jamjam.rest.service.Company.CompanyService;



@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;
	@Autowired
	MailService mailService;
	@GetMapping("/company/{user_id}")
	public Company getCompany(@PathVariable("user_id") Integer user_id) {
		
		System.out.println("회사회원정보 접근");
		Company company = companyService.getCompany(user_id);
		return company;
	}
	@PutMapping("/company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company){
		mailService.sendSimpleEmail("wnsgud0202@naver.com", "jamjam에서 알려드립니다", "회원정보가 수정되었습니다.");
		int result=companyService.updateCompany(company);
		if(result==1) {
			return ResponseEntity.ok().body("업데이트 성공");
		}else {
			return ResponseEntity.status(500).body("업데이트 실패");
		}
	}
	@DeleteMapping("/company/{user_id}")
	public ResponseEntity<?> deleteCompany(@PathVariable("user_id") Integer user_id){
		int result=companyService.deleteCompany(user_id);
		if(result==1) {
			return ResponseEntity.ok().body("회원탈퇴 성공");
		}else {
			return ResponseEntity.status(500).body("회원탈퇴 실패");
		}
	}
	
	
	
	

	
}
