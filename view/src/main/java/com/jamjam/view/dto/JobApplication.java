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
public class JobApplication {
	
	private Integer application_id;
	private Integer user_id;
	private Integer posting_id;
	private Integer resume_viewed;
	private Date created_at;
	private Date updated_at;
}
