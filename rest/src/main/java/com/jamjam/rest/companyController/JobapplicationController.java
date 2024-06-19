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
import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.JobApplication;
import com.jamjam.rest.dto.JobPosting;
import com.jamjam.rest.dto.User;
import com.jamjam.rest.service.MailService;

@RestController
@RequestMapping("/company")
public class JobapplicationController {

	@Autowired
	JobapplicationDao jobapplicationMapper;
	@Autowired
	UserDao userMapper;	
	@Autowired
	ResumeDao resumeMapper;
	@Autowired
	MailService mailService;
	@Autowired
	JobpostingDao jobpostingMapper;
	

	
	
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
		JobApplication application= jobapplicationMapper.getJobApplicationByApplicationId(application_id);
		Integer user_id=application.getUser_id();
		User user =userMapper.findByUserId(user_id);
		String mail = user.getEmail();
		Integer posting_id = application.getPosting_id();
		JobPosting jobposting = jobpostingMapper.getJobPosting(posting_id);
		String postingTitle = jobposting.getTitle();
		String title = "<새로운 알림> 채용공고:["+postingTitle + "], 채용결과가 발표되었습니다.";
		String content ="<!DOCTYPE html>"
	            + "<html>"
	            + "<head>"
	            + "<style>"
	            + "  .header { background-color: #f2f2f2; padding: 10px; text-align: center; }"
	            + "  .content { font-family: Arial, sans-serif; margin: 20px; }"
	            + "  .button { background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; margin: 10px 0; border-radius: 4px; }"
	            + "  .footer { background-color: #f2f2f2; padding: 10px; text-align: center; font-size: 12px; }"
	            + "</style>"
	            + "</head>"
	            + "<body>"
	            + "  <div class=\"header\">"
	            + "    <h1>새로운 알림이 도착했습니다.</h1>"
	            + "  </div>"
	            + "  <div class=\"content\">"
	            + "    <p>채용공고 : ["+postingTitle+"]에</p>"
	            + "    <p>서류 합격하셨습니다. 축하드립니다.</p>"
	            + "    <a href=\"http://localhost:9999/nindex\" class=\"button\">Get Started</a>"
	            + "  </div>"
	            + "  <div class=\"footer\">"
	            + "    <p>&copy; 2024 Our Service, Inc. All rights reserved.</p>"
	            + "  </div>"
	            + "</body>"
	            + "</html>";
		mailService.sendHTMLEmail(mail, title, content);
		jobapplicationMapper.updatePass(application_id);
	}
	@PutMapping("/jobapplication/nonPass/{application_id}")
	public void updateNonPass(@PathVariable("application_id")Integer application_id) {
		//System.out.println("nonpass들어옴");
		JobApplication application= jobapplicationMapper.getJobApplicationByApplicationId(application_id);
		Integer user_id=application.getUser_id();
		User user =userMapper.findByUserId(user_id);
		String mail = user.getEmail();
		Integer posting_id = application.getPosting_id();
		JobPosting jobposting = jobpostingMapper.getJobPosting(posting_id);
		String postingTitle = jobposting.getTitle();
		String title = "<새로운 알림> 채용공고:["+postingTitle + "], 채용결과가 발표되었습니다.";
		String content ="<!DOCTYPE html>"
	            + "<html>"
	            + "<head>"
	            + "<style>"
	            + "  .header { background-color: #f2f2f2; padding: 10px; text-align: center; }"
	            + "  .content { font-family: Arial, sans-serif; margin: 20px; }"
	            + "  .button { background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; margin: 10px 0; border-radius: 4px; }"
	            + "  .footer { background-color: #f2f2f2; padding: 10px; text-align: center; font-size: 12px; }"
	            + "</style>"
	            + "</head>"
	            + "<body>"
	            + "  <div class=\"header\">"
	            + "    <h1>새로운 알림이 도착했습니다.</h1>"
	            + "  </div>"
	            + "  <div class=\"content\">"
	            + "    <p>채용공고 : ["+postingTitle+"]에</p>"
	            + "    <p>서류 불합격입니다.더 좋은 기회가 있을 거에요</p>"
	            + "    <a href=\"http://localhost:9999/nindex\" class=\"button\">Get Started</a>"
	            + "  </div>"
	            + "  <div class=\"footer\">"
	            + "    <p>&copy; 2024 Our Service, Inc. All rights reserved.</p>"
	            + "  </div>"
	            + "</body>"
	            + "</html>";
		mailService.sendHTMLEmail(mail, title, content);
		jobapplicationMapper.updateNonPass(application_id);
	}
}
