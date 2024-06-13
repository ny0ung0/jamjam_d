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
public class User {
	
	private Integer user_id;
	private String email;
	private String name;
	private String contact;
	private String address;
	private Date birth_date;
	private String gender;
	private Date created_at;
	private Date updated_at;
}
