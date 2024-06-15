package com.jamjam.rest.companyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dto.JobPosting;
import com.jamjam.rest.service.Company.JobpostingService;

@RestController
@RequestMapping("/company")
public class JobpostingController {

	@Autowired
	JobpostingService jobpostingService;
	
	@PostMapping("/jobposting")
	public ResponseEntity<?> insertJobposting(@RequestBody JobPosting jobposting) {
		
		String result = jobpostingService.insertJobposting(jobposting);
		return ResponseEntity.ok().body(result);
	}
	@GetMapping("/jobposting/{posting_id}")
	public JobPosting getPosting(@PathVariable("posting_id") Integer posting_id){
		//System.out.println("get포스팅 접근 성공 : "+ posting_id);
		JobPosting jobPosting= jobpostingService.getJobPosting(posting_id);
		System.out.println(jobPosting);
		return jobPosting;
	}
}