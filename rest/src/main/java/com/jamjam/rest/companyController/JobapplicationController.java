package com.jamjam.rest.companyController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobapplicationDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.JobApplication;
import com.jamjam.rest.dto.User;

@RestController
@RequestMapping("/company")
public class JobapplicationController {

	@Autowired
	JobapplicationDao jobapplicationMapper;
	@Autowired
	UserDao userMapper;	
	@Autowired
	ResumeDao resumeMapper;
	
	@GetMapping("/jobapplication/posting_id/{posting_id}")
	public List<User> getJobapplicationByCompany_id(@PathVariable("posting_id")Integer posting_id) {
		//System.out.println("잡어플리케이션 get 들어옴");
		List<JobApplication> jobApplications =jobapplicationMapper.getJobApplicationByPosting_id(posting_id);
		List<User> users = new ArrayList<>();
		for(JobApplication jobApplication :jobApplications) {
			User user= userMapper.findByUserId(jobApplication.getUser_id());
			user.setResume_id(jobApplication.getResume_id());
			//ResumeDB resume =resumeMapper.findById(jobApplication.getResume_id());
			user.setResumeStatus(jobApplication.getResume_viewed());
			user.setApplication_id(jobApplication.getApplication_id());
			users.add(user);
			System.out.println(user);
		}
		
		return users;
	}
	
	@GetMapping("/jobapplication/{application_id}")
	public JobApplication  getJobapplication(@PathVariable("application_id")Integer application_id) {
		//System.out.println("어플리케이션 겟 들어옴");
		System.out.println(jobapplicationMapper.getJobApplicationByApplicationId(application_id)); 
		
		return jobapplicationMapper.getJobApplicationByApplicationId(application_id);
	}
	
	@PutMapping("/jobapplication/pass/{application_id}")
	public void updatePass(@PathVariable("application_id")Integer application_id) {
		//System.out.println("pass들어옴");
		jobapplicationMapper.updatePass(application_id);
	}
	@PutMapping("/jobapplication/nonPass/{application_id}")
	public void updateNonPass(@PathVariable("application_id")Integer application_id) {
		//System.out.println("pass들어옴");
		jobapplicationMapper.updateNonPass(application_id);
	}
}
