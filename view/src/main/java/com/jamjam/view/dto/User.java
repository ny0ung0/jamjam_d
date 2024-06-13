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
public class User {
	
	private Long userId;
	private String email;
	private String name;
	private String contact;
	private String address;
	private Date birth_date;
	private String gender;
	private Date created_at;
	private Date updated_at;
	
	public boolean hasAdditionalInfo() {
        return contact != null && !contact.isEmpty() &&
               address != null && !address.isEmpty() &&
               birth_date != null &&
               gender != null && !gender.isEmpty();
    }
	
}
