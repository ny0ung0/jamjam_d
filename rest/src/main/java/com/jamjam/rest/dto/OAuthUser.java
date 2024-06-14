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
public class OAuthUser {
	
	private Long id;
    private Integer userId;
    private String provider;
    private String providerUserId;
    
    public OAuthUser(Integer integer, String provider2, String providerUserId2) {
		this.userId = integer;
		this.provider = provider2;
		this.providerUserId = providerUserId2;
	}
}
