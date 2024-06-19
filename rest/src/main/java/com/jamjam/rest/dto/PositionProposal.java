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
public class PositionProposal {

	private Integer proposal_id;
	private Integer company_id;
	private Integer user_id;
	private String status;
	private Date created_at;
	private Date updated_at;
	private String company_name;
	private String proposal_title;
	private String proposal_content;
	
	private String user_name;
}
