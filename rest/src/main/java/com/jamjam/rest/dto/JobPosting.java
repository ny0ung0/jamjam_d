package com.jamjam.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {

	private String new_name;
	private String company_name;
	private Integer posting_id;
	private Integer company_id;
	private String title;
	private String industry;
	private String job_role;
	private String job_description;
	private String experience_required;
	private String employment_type;
	private Integer number_of_positions;
	private String salary_condition;
	private String salary_type;
	private String education_requirement;
	private String required_skills;
	private String location;
	private String working_hours;
	private String benefits;
	private String keywords;
	private String contact_person_name;
	private String contact_person_email;
	private String contact_person_phone;
	private Date application_deadline;
	private Integer view_count;
	private Date created_at;
	private Date updated_at;
	private int deadline_status;



}
