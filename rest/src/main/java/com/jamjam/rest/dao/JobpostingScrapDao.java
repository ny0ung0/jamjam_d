package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.JobPostingScrap;

@Mapper
public interface JobpostingScrapDao {

	@Insert("INSERT INTO jobpostingscrap (user_id, posting_id) VALUES (#{user_id}, #{posting_id})")
	void scrapJob (JobPostingScrap jobpostingscrap);

	@Select("SELECT COUNT(*) > 0 FROM jobpostingscrap WHERE user_id = #{user_id} AND posting_id = #{posting_id}")
    boolean hasScrapped(@Param("user_id") Integer user_id, @Param("posting_id") Integer posting_id);

	
	@Delete("DELETE FROM jobpostingscrap WHERE user_id = #{user_id} AND posting_id = #{posting_id}")
	void cancelScrapJob(@Param("user_id") Integer user_id, @Param("posting_id") Integer posting_id);
	
}
