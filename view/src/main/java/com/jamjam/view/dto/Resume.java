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
public class Resume {

	private Integer resume_id;
	private Integer user_id;
	private String title;
	private String desired_job;
	private String skills;
	private String license;
	private String education;
	private String experience;
	private String preferences;
	//자기소개서 제목
	private String cover_letter_title;
	//자기소개서 내용
	private String cover_letter_content;
	private String desired_conditions;
	//포트폴리오 링크
	private File portfolio;
	private Integer view_count;
	private Date created_at;
	private Date updated_at;
	
}
