package com.jamjam.rest.companyController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobapplicationDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.ResumeDB;
import com.jamjam.rest.dto.User;

@RestController
@RequestMapping("/company")
public class MachingController {

	@Autowired
	JobapplicationDao jobapplicationMapper;
	@Autowired
	UserDao userMapper;	
	@Autowired
	ResumeDao resumeMapper;
	
	public void machingList(@RequestParam("keywords") String keywords) {
	 String[] keywords_ =keywords.split("//");
	//List<User> users = userMapper.userFindAll();
	List<ResumeDB> resumes = resumeMapper.findAll();
		List<User> results = new ArrayList<>();
	for(String keyword : keywords_) {
		for(ResumeDB resume : resumes){
			if(resume.getDesired_job().contains(keyword) || resume.getSkills().contains(keyword) ) {
				
			}
		}
	}
			
			
		
	}
}
