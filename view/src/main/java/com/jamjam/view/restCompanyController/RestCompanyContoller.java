package com.jamjam.view.restCompanyController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.view.dto.Company;


@RestController
@RequestMapping("/api")
public class RestCompanyContoller {

	@PostMapping("/company")
	public String register(@RequestBody Company company) {
		System.out.println("restServer 들어옴" +company);
		return "aa";
	}
	/*
	 * @GetMapping("/company/{company_id}") public String
	 */
}
