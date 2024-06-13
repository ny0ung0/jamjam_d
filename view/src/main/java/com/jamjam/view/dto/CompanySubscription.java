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
public class CompanySubscription {

	private Integer subscription_id;
	private Integer user_id;
	private Integer company_id;
	private Date created_at;
	private Date updated_at;
}
