package com.jamjam.rest.companyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dto.CompanyInfo;
import com.jamjam.rest.dto.CompanyInfoDto;
import com.jamjam.rest.service.Company.CompanyInfoService;

@RestController
@RequestMapping("/company")
public class CompanyInfoController {

	@Autowired
	CompanyInfoService companyInfoService;
	
	
	@PostMapping("/companyInfo")
	public ResponseEntity<?> insertCompanyinfo11(@ModelAttribute CompanyInfoDto companyInfoDto) {
		System.out.println("컨트롤러 들어옴");
		System.out.println(companyInfoDto);
		companyInfoDto.setDescription(companyInfoDto.getDescription().replace("\n", "<br>"));
		String result = companyInfoService.insertCompanyInfo(companyInfoDto);
		if(result.contains("성공")) {
			return ResponseEntity.ok().body(result);
		}else {
			return  ResponseEntity.status(500).body(result);
		}
		
	}
	
	@PutMapping("/companyInfo")
	public ResponseEntity<?> updateCompanyinfoImage(@ModelAttribute CompanyInfoDto companyInfoDto) {
		System.out.println("컨트롤러 들어옴");
		System.out.println(companyInfoDto);
		companyInfoDto.setDescription(companyInfoDto.getDescription().replace("\n", "<br>"));
		String result = companyInfoService.updateCompanyInfoImage(companyInfoDto);
		if(result.contains("성공")) {
			return ResponseEntity.ok().body(result);
		}else {
			return  ResponseEntity.status(500).body(result);
		}
		
	}
	
	@GetMapping("/companyInfo/{company_id}")
	public CompanyInfo getCompanyInfo(@PathVariable("company_id") Integer company_id) {
		System.out.println("회사소개get 들어옴");
		CompanyInfo result =companyInfoService.getCompanyInfoByCompanyId(company_id);
		System.out.println(result); 
		
		return result;
	}
	
	@DeleteMapping("/companyInfo/{info_id}")
	public void deleteCompanyInfo(@PathVariable("info_id") Integer info_id) {
		System.out.println("회사소개delete 들어옴");
		companyInfoService.deleteCompanyInfo(info_id);
		
	}
	@GetMapping("/companyInfo/info_id/{info_id}")
	public CompanyInfo getCompanyInfoByInfoId(@PathVariable("info_id") Integer info_id) {
		System.out.println("회사소개get 들어옴 infoId");
		CompanyInfo result =companyInfoService.getCompanyInfoByInfo_id(info_id);
		System.out.println(result); 
		
		return result;
	}
	
	
}
