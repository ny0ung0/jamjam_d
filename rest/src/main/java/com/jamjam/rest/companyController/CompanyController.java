package com.jamjam.rest.companyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dto.CompanyInfo;
import com.jamjam.rest.dto.CompanyInfoDto;
import com.jamjam.rest.dto.JobPosting;



@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	JobpostingDao jobpostionMapper;
	
	@PostMapping("/jobposting")
	public String insertJobposting(@RequestBody JobPosting jobposting) {
		System.out.println("채용공고" + jobposting);
		jobpostionMapper.insertJobposting(jobposting);
		return "채용공고 등록";
	}
	
	@PostMapping("/companyinfo")
	public String insertCompanyinfo(CompanyInfoDto companyInfoDto) {
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompany_id(companyInfoDto.getCompany_id());
		companyInfo.setDescription(companyInfoDto.getDescription());
		companyInfo.setNumber_of_employees(companyInfoDto.getNumber_of_employees());
		companyInfo.setWorking_environment(companyInfoDto.getWorking_environment());
		companyInfo.setBenefits(companyInfoDto.getBenefits());
		companyInfo.setIdeal_candidate(companyInfoDto.getIdeal_candidate());
		companyInfo.setMain_contact(companyInfoDto.getMain_contact());
		companyInfo.setMain_email(companyInfoDto.getMain_email());
		return "";
	}
	

	
}
