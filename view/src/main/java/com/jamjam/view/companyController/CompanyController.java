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
	@RequestMapping("/all")
	public String all() {
		return "/company/all";
	}
	
	@RequestMapping("/company_job_postings_list")
	public void company_job_postings_list() {
		
	}
	@RequestMapping("/company_introduction_detail")
	public void company_introduction_detail(@RequestParam("info_id")Integer info_id,Model model) {
		model.addAttribute("info_id",info_id);
	}
	@RequestMapping("/company_introduction_edit")
	public void company_introduction_edit(@RequestParam("info_id")Integer info_id,Model model) {
		model.addAttribute("info_id",info_id);
	}
}
