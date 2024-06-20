package com.jamjam.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jamjam.rest.dto.JobPosting;

@Mapper
public interface JobpostingDao {

	@Insert("INSERT INTO jobposting(company_id,title,industry,job_role,job_description,experience_required,employment_type,number_of_positions,salary_condition,salary_type,education_requirement,required_skills,location,working_hours,benefits,keywords,contact_person_name,contact_person_email,contact_person_phone,application_deadline,deadline_status)  "
	 		+ "VALUES (#{company_id},#{title},#{industry},#{job_role},#{job_description},#{experience_required},#{employment_type},#{number_of_positions},#{salary_condition},#{salary_type},#{education_requirement},#{required_skills},#{location},#{working_hours},#{benefits},#{keywords},#{contact_person_name},#{contact_person_email},#{contact_person_phone},#{application_deadline},0)")
	public int insertJobposting(JobPosting jobposting);
	
	@Select("select * from jobposting where posting_id=#{posting_id}")
	public JobPosting getJobPosting(Integer posting_id);
	
	@Update("UPDATE jobposting "
			+ "SET "
			+ "    company_id = #{company_id},"
			+ "title = #{title},"
			+ "industry = #{industry},"
			+ "job_role = #{job_role},"
			+ "job_description = #{job_description},"
			+ "experience_required = #{experience_required},"
			+ "employment_type = #{employment_type},"
			+ "number_of_positions = #{number_of_positions},"
			+ "salary_condition = #{salary_condition},"
			+ "salary_type = #{salary_type},"
			+ "education_requirement = #{education_requirement},"
			+ "required_skills = #{required_skills},"
			+ "location = #{location},"
			+ "working_hours = #{working_hours},"
			+ "benefits = #{benefits},"
			+ "keywords = #{keywords},"
			+ "contact_person_name = #{contact_person_name},"
			+ "contact_person_email = #{contact_person_email},"
			+ "contact_person_phone = #{contact_person_phone},"
			+ "application_deadline = #{application_deadline} "
			+ " WHERE posting_id = #{posting_id}")
	public int updateJobposting(JobPosting jobposting);
	
	 @Delete("delete from jobposting where posting_id = #{posting_id}")
	 public int deletePosting(Integer posting_id);
	 
	 @Select("Select * from jobposting")
	 public List<JobPosting> getPostingAll();
	 
	 @Select("Select * from jobposting where company_id = #{company_id}")
	 public List<JobPosting> getPostingListByCompanyId(Integer company_id);
	
	 @Update("update jobposting set deadline_status = 1 where posting_id=#{posting_id}")
	 public void updateDeadlineStatus1(Integer posting_id);
	 @Update("update jobposting set deadline_status = 0 where posting_id=#{posting_id}")
	 public void updateDeadlineStatus0(Integer posting_id);
	 
	 @Update("UPDATE jobposting SET view_count = #{view_count} WHERE posting_id = #{posting_id}")
	 public void updatePostView(@Param("view_count") Integer view_count, @Param("posting_id") Integer posting_id);

	 @Select("SELECT * FROM jobposting RIGHT JOIN user ON jobposting.company_id = user.user_id right JOIN companyinfo on companyinfo.company_id = user.user_id WHERE SUBSTRING_INDEX(location, '//', 1) = #{desired_conditions} AND SUBSTRING_INDEX(required_skills, '//', 1) = #{desired_job}")
	 public List<JobPosting> getMatchingJobPostings(@Param("desired_conditions") String desired_conditions, @Param("desired_job") String desiredJob);
	 
	 @Select("SELECT * FROM jobposting LEFT JOIN user ON jobposting.company_id = user.user_id LEFT JOIN companyinfo ON jobposting.company_id = companyinfo.company_id ORDER BY view_count DESC")
	 public List<JobPosting> getpopoularPostAll();	 
	 
}
