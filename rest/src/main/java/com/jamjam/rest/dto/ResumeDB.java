package com.jamjam.rest.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDB {

	private Integer resume_id;
	private Integer user_id;
	private String profile_photo;
	private String photo_newName;
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
	private String portfolio;
	private Integer view_count;
	private Date created_at;
	private Date updated_at;
	
	//이력서 상세 보기에 유저 정보확인 및 출력용
	private String email;
	private String name;
	private String contact;
	private String address;
	private Date birth_date;
	private String gender;
	private String role;
	
}
