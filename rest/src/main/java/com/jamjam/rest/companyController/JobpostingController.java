package com.jamjam.rest.companyController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		jobposting.setJob_description(jobposting.getJob_description().replace("\n", "<br>"));
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
	@PutMapping("/jobposting")
	public ResponseEntity<?> updateJobposting(@RequestBody JobPosting jobposting) {
		jobposting.setJob_description(jobposting.getJob_description().replace("\n", "<br>"));
		int result = jobpostingService.updateJobposting(jobposting);
		if(result ==1) {
			return ResponseEntity.ok().body("등록성공");
		}else {
			return ResponseEntity.status(500).body("등록실패");
		}
	}
	
	@DeleteMapping("/jobposting/{posting_id}")
	public ResponseEntity<?> deleteCompany(@PathVariable("posting_id") Integer posting_id){
		int result=jobpostingService.deletePosting(posting_id);
		if(result==1) {
			return ResponseEntity.ok().body("공고삭제 성공");
		}else {
			return ResponseEntity.status(500).body("공고삭제 실패");
		}
	}
	@GetMapping("/jobposting")
	public List<JobPosting> getPostingAll(@RequestParam(value="company_id", required = false) Integer company_id){
		
		if(company_id ==null) {
			return jobpostingService.getPostingAll();
			
		}else {
			return jobpostingService.getPostingListByCompanyId(company_id);
		}
		
	}
	@PutMapping("/jobposting/status/{posting_id}")
	public void deadlineStatus(@PathVariable("posting_id")Integer posting_id) {
		//System.out.println("deadlineStatus 들어옴");
		jobpostingService.updateDeadlineStatus1(posting_id);
	}
	
	
	
}
