package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.jamjam.rest.dto.JobApplication;

@Mapper
public interface JobapplicationDao {
	
	@Insert("INSERT INTO jobapplication (user_id, posting_id) VALUES (#{user_id}, #{posting_id})")
	void applyJob(JobApplication jobApplication);

}
