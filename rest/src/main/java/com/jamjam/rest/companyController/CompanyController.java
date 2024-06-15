package com.jamjam.rest.companyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dto.Company;
import com.jamjam.rest.service.Company.CompanyService;



@RestController
public class CompanyController {

	@Autowired
	CompanyService companyService;
	@GetMapping("/company/{company_id}")
	public Company getCompany(@PathVariable("company_id") Integer company_id) {
		Company company = companyService.getCompany(company_id);
		return company;
	}
	
	
	
	

	
}
