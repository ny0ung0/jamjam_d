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
public class JobScrapList {

	private Integer scrap_id;
	private Integer user_id;
	private Integer posting_id;
	private Integer company_id;
	private String title;
	private String keywords;
	private Date application_deadline;
	private String company_name;
	
}
