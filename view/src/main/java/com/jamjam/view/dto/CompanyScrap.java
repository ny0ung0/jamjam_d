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
public class CompanyScrap {

	private Integer scrap_id;
	private Integer company_id;
	private Integer resume_id;
	private Date created_at;
	private Date updated_at;
}
