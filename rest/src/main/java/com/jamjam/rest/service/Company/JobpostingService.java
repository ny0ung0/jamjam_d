package com.jamjam.rest.service.Company;

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
}
