package com.jamjam.rest.companyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		System.out.println("채용공고" + jobposting);
		String result = jobpostingService.insertJobposting(jobposting);
		return ResponseEntity.ok().body(result);
	}
}
