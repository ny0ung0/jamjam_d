package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.Resume;
import com.jamjam.rest.dto.ResumeDB;

@Mapper
public interface ResumeDao {

	@Insert("INSERT INTO resume (user_id, profile_photo, photo_newName, title, desired_job, skills, license, education, experience, preferences, cover_letter_title, cover_letter_content, desired_conditions, portfolio) VALUES (#{user_id}, #{profile_photo}, #{photo_newName}, #{title}, #{desired_job}, #{skills}, #{license}, #{education}, #{experience}, #{preferences}, #{cover_letter_title}, #{cover_letter_content}, #{desired_conditions}, #{portfolio})")
	@Options(useGeneratedKeys = true, keyProperty = "resume_id")
	int insertResume(ResumeDB resume);
	
	@Select("SELECT * FROM resume WHERE user_id = #{user_id}")
	List<Resume> findByUserId(Long user_id);
	
	@Select("SELECT * FROM resume WHERE resume_id = #{resume_id")
	Resume findByResumeId(Long resumeId);
}
