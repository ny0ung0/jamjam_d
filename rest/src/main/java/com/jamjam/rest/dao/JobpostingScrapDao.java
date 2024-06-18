package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.JobPostingScrap;
import com.jamjam.rest.dto.JobScrapList;

@Mapper
public interface JobpostingScrapDao {

	@Insert("INSERT INTO jobpostingscrap (user_id, posting_id) VALUES (#{user_id}, #{posting_id})")
	void scrapJob (JobPostingScrap jobpostingscrap);

	@Select("SELECT COUNT(*) > 0 FROM jobpostingscrap WHERE user_id = #{user_id} AND posting_id = #{posting_id}")
    boolean hasScrapped(@Param("user_id") Integer user_id, @Param("posting_id") Integer posting_id);

	
	@Delete("DELETE FROM jobpostingscrap WHERE user_id = #{user_id} AND posting_id = #{posting_id}")
	void cancelScrapJob(@Param("user_id") Integer user_id, @Param("posting_id") Integer posting_id);
	
	
	@Select("SELECT * FROM jobpostingscrap WHERE user_id = #{user_id}")
	List <JobPostingScrap> jobScrapList(@Param("user_id") Integer user_id);
	
	@Select("SELECT company_id, scrap.posting_id AS posting_id, title, experience_required, employment_type, keywords, application_deadline, company_name FROM jobpostingscrap scrap RIGHT JOIN jobposting post ON scrap.posting_id = post.posting_id RIGHT JOIN user ON post.company_id = user.user_id WHERE scrap.user_id = #{user_id}")
    List<JobScrapList> getScrapListDetails(@Param("user_id") Integer user_id);
}
