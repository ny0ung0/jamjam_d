package com.jamjam.view.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	private Integer user_id;
	private String company_name;
	private String email;
	private String registration_number;
	private String company_type;
	private String representative_name;
	private String address;
	private String tel;
	private Date created_at;
	private Date updated_at;
	
}
