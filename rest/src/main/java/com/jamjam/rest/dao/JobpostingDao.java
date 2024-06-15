package com.jamjam.rest.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jamjam.rest.dto.JobPosting;

@Mapper
public interface JobpostingDao {

	@Insert("INSERT INTO jobposting(company_id,title,industry,job_role,job_description,experience_required,employment_type,number_of_positions,salary_condition,salary_type,education_requirement,required_skills,location,working_hours,benefits,keywords,contact_person_name,contact_person_email,contact_person_phone,application_deadline)  "
	 		+ "VALUES (#{company_id},#{title},#{industry},#{job_role},#{job_description},#{experience_required},#{employment_type},#{number_of_positions},#{salary_condition},#{salary_type},#{education_requirement},#{required_skills},#{location},#{working_hours},#{benefits},#{keywords},#{contact_person_name},#{contact_person_email},#{contact_person_phone},#{application_deadline})")
	public int insertJobposting(JobPosting jobposting);
	
	@Select("select * from jobposting where posting_id=#{posting_id}")
	public JobPosting getJobPosting(Integer posting_id);
}
