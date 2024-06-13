package com.jamjam.rest.companyController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dto.Company;



@RestController
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyController {

	@PostMapping("/register")
	public String register(Company company) {
		System.out.println("restServer 들어옴" +company);
		return "aa";
	}
}
