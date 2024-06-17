package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.JobApplication;

@Mapper
public interface JobapplicationDao {
	
	@Insert("INSERT INTO jobapplication (user_id, posting_id) VALUES (#{user_id}, #{posting_id})")
	void applyJob(JobApplication jobApplication);

	
	@Select("SELECT COUNT(*) > 0 FROM jobapplication WHERE user_id = #{user_id} AND posting_id = #{posting_id}")
    boolean hasApplied(@Param("user_id") Integer user_id, @Param("posting_id") Integer posting_id);
}
