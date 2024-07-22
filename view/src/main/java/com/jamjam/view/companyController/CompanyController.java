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
	public void job_posting_detail_company(@RequestParam(value ="posting_id",required =false) Integer posting_id,Model model) {
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
	public void company_introduction_detail(@RequestParam(value ="info_id",required =false)Integer info_id,Model model) {
		model.addAttribute("info_id",info_id);
	}
	@RequestMapping("/company_introduction_edit")
	public void company_introduction_edit(@RequestParam(value ="info_id",required =false)Integer info_id,Model model) {
		model.addAttribute("info_id",info_id);
	}
	
	@RequestMapping("/applicants_list")
	public void applicants_list(@RequestParam("posting_id") Integer posting_id,Model model) {
		model.addAttribute("posting_id",posting_id);
	}
	
	@RequestMapping("position_matching_list_company")
	public void position_matching_list_company() {
		
	}
	@RequestMapping("position_suggestion")
	public void position_suggestion(@RequestParam("company_id")Integer company_id,@RequestParam("user_id")Integer user_id,Model model) {
		model.addAttribute("company_id", company_id);
		model.addAttribute("user_id", user_id);
	}
	
	@RequestMapping("company_homepage")
	public void company_homepage(HttpSession session,@RequestParam("company_id")Integer company_id) {
		session.setAttribute("company_id", company_id);
		System.out.println(company_id);
	}
	
	@RequestMapping("position_suggestion_list")
	public void position_suggestion_list() {
		
	}
}
