package com.jamjam.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private Long role_id;
	private Integer user_id;
	private Integer company_id;
	private String role;
}
