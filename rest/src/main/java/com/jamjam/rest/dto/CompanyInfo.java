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
public class CompanyInfo {

	private Integer info_id;
	private Integer company_id;
	private String description;

	private Integer number_of_employees;
	private String working_environment;
	private String benefits;
	private String ideal_candidate;
	private String main_contact;
	private String main_email;
	private Date created_at;
	private Date updated_at;
	
	private String origin_name;
	private String new_name;
}
