package com.jamjam.view.companyController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamjam.view.dto.Company;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@RequestMapping("/log")
	public String log(HttpSession session) {
		Company com = new Company(
					1,"aaa@aaa.com","123-12-1234","호텔·여행·항공","김대표","세종특별자치시","010-1234-1234",null,null
				);
		session.setAttribute("loginCompany", com);
		
		return "/leindex";
	}
	@RequestMapping("/job_posting_create")
	public void job_posting_create()  {
		
	}
	@RequestMapping("/company_introduction_create")
	public void company_introduction_create() {
		
	}
	@RequestMapping("/company_edit_profile")
	public void company_edit_profile(){
		
	}

	
}
