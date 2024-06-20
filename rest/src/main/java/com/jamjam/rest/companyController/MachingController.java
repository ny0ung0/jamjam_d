package com.jamjam.rest.companyController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobapplicationDao;
import com.jamjam.rest.dao.PositionProposalDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.PositionProposal;
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
	@Autowired
	PositionProposalDao proposlaMapper;
	
	@GetMapping("/machingList")
	public List<User> machingList(@RequestParam("keywords") String keywords,@RequestParam("company_id") Integer company_id) {
	 String[] keywords_ =keywords.split("//");
	//List<User> users = userMapper.userFindAll();
	List<ResumeDB> resumes = resumeMapper.findAll();
		List<User> results = new ArrayList<>();
	for(String keyword : keywords_) {
		for(ResumeDB resume : resumes){
			if(resume.getDesired_job().contains(keyword) || resume.getSkills().contains(keyword) ) {
				Integer userId=resume.getUser_id();
				User user =userMapper.findByUserId(userId);
				user.setResume_id(resume.getResume_id());
				
				results.add(user);
			}
		}
	}
	List<PositionProposal> proposals = proposlaMapper.findProposalsByCompanyId(company_id);
	for(User user : results ) {
		user.setProposalStatus("0");
		for(PositionProposal proposal : proposals) {
			if(proposal.getUser_id() ==user.getUser_id()) {
				user.setProposalStatus("1");
				break;
			}
		}
	}
	
			
	System.out.println(results);
	return results;
		
	}
	
	@GetMapping("/positionProposal/{company_id}")
	public List<PositionProposal> findAllProposal(@PathVariable("company_id")Integer company_id) {
		System.out.println("rest proposal 들어옴");
		List<PositionProposal> list =proposlaMapper.findProposalsAndNameByCompanyId(company_id);
		System.out.println(list);
		return list;
	}
	
	@PostMapping("/positionProposal")
	public void insertPositionProposal(@RequestBody PositionProposal positionProposal) {
		//System.out.println("제안등록 들어옴");
		//System.out.println(positionProposal);
		proposlaMapper.insertProposal(positionProposal);
		
	}
}
