package com.jamjam.rest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private String role;
	
	//공고 지원자 리스트에서 필요
	private Integer resume_id;
	private Integer resumeStatus;
	private Integer application_id;
	
	//포지션 매칭에 필요
	private Integer posting_id;
	private String title;
	private String proposalStatus;
	
	public void setBirth_date(String birth_date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.birth_date = format.parse(birth_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	
	
	public boolean hasAdditionalInfo() {
        return contact != null && !contact.isEmpty() &&
               address != null && !address.isEmpty() &&
               birth_date != null &&
               gender != null && !gender.isEmpty();
    }
	
}
