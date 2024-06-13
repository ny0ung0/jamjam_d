package com.jamjam.view.dto;

import java.io.File;
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
	private File logo_image;
	private String description;
	private String industry;
	private Integer number_of_employees;
	private String benefits;
	private String ideal_candidate;
	private String main_contact;
	private String main_email;
	private Date created_at;
	private Date updated_at;
}
