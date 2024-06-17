package com.jamjam.view.companyController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamjam.view.dto.Company;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@RequestMapping("/log")
	public String log(HttpSession session) {
		Company com = new Company(
					1,"00호텔","aaa@aaa.com","123-12-1234","호텔·여행·항공","김대표","세종특별자치시","010-1234-1234",null,null
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
	@RequestMapping("/job_posting_edit")
	public void job_posting_edit(@RequestParam("posting_id") Integer posting_id,Model model) {
		model.addAttribute("posting_id", posting_id);
	}

	@RequestMapping("/job_posting_detail_company")
	public void job_posting_detail_company(@RequestParam("posting_id") Integer posting_id,Model model) {
		model.addAttribute("posting_id", posting_id);
	}
}
