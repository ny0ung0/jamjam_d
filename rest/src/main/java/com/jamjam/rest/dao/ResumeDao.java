package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.Resume;
import com.jamjam.rest.dto.ResumeDB;

@Mapper
public interface ResumeDao {

	@Insert("INSERT INTO resume (user_id, profile_photo, photo_newName, title, desired_job, skills, license, education, experience, preferences, cover_letter_title, cover_letter_content, desired_conditions, portfolio) VALUES (#{user_id}, #{profile_photo}, #{photo_newName}, #{title}, #{desired_job}, #{skills}, #{license}, #{education}, #{experience}, #{preferences}, #{cover_letter_title}, #{cover_letter_content}, #{desired_conditions}, #{portfolio})")
	@Options(useGeneratedKeys = true, keyProperty = "resume_id")
	int insertResume(ResumeDB resume);
	
	@Select("SELECT * FROM resume WHERE user_id = #{user_id}")
	List<Resume> findByUserId(Long user_id);
	
	@Select("SELECT * FROM resume WHERE resume_id = #{resume_id}")
    ResumeDB findById(int resume_id);
	
	@Select("SELECT resume_id, title, created_at FROM resume WHERE user_id = (SELECT user_id FROM user WHERE email = #{email})")
    List<ResumeDB> findByEmail(String email);
	
	@Update("UPDATE resume SET profile_photo = #{profile_photo}, photo_newName = #{photo_newName}, title = #{title}, desired_job = #{desired_job}, skills = #{skills}, license = #{license}, education = #{education}, experience = #{experience}, preferences = #{preferences}, cover_letter_title = #{cover_letter_title}, cover_letter_content = #{cover_letter_content}, desired_conditions = #{desired_conditions}, portfolio = #{portfolio} WHERE resume_id = #{resume_id}")
    int updateResume(ResumeDB resume);
	
	@Select("select * from resume")
	public List<ResumeDB> findAll();
	
	@Select("SELECT * FROM resume RIGHT JOIN user ON resume.user_id = user.user_id WHERE resume_id = #{resume_id}")
	ResumeDB findByResumeId(int resume_id);
	
	@Select("SELECT * FROM resume WHERE user_id = #{user_id} ORDER BY updated_at DESC LIMIT 1")
    ResumeDB getLatestResume(Integer user_id);
}
