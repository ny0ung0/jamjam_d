package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.jamjam.rest.dto.JobPostingScrap;

@Mapper
public interface JobpostingScrapDao {

	@Insert("INSERT INTO jobpostingscrap (user_id, posting_id) VALUES (#{user_id}, #{posting_id})")
	void scrapJob (JobPostingScrap jobpostingscrap);

}
