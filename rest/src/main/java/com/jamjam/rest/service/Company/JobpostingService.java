package com.jamjam.rest.service.Company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dto.JobPosting;

@Service
public class JobpostingService {

	@Autowired
	JobpostingDao jobpostingMapper;
	
	public String insertJobposting(JobPosting jobposting) {
		int result = jobpostingMapper.insertJobposting(jobposting);
		
		if(result == 1) {
			return "등록성공";
		}else {
			return "등록실패";
		}
	}
	
	public JobPosting getJobPosting(Integer posting_id) {
		return jobpostingMapper.getJobPosting(posting_id);
	}
	
	public int updateJobposting(JobPosting jobposting) {
		int result = jobpostingMapper.updateJobposting(jobposting);
		return result;
	}
	
	public int deletePosting(Integer posting_id) {
		return jobpostingMapper.deletePosting(posting_id);
	}
	
	public List<JobPosting> getPostingAll() {
		return jobpostingMapper.getPostingAll();
	}
	
	public List<JobPosting> getPostingListByCompanyId(Integer company_id) {
		return jobpostingMapper.getPostingListByCompanyId(company_id);
	}
	
	public void updateDeadlineStatus1(Integer posting_id) {
		jobpostingMapper.updateDeadlineStatus1(posting_id);
	}
	public void updateDeadlineStatus0(Integer posting_id) {
		jobpostingMapper.updateDeadlineStatus0(posting_id);
	}
	
}
