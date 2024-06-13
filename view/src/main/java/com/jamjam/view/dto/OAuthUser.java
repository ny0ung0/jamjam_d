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
public class OAuthUser {
	
	private Long id;
    private Long userId;
    private String provider;
    private String providerUserId;
    
    public OAuthUser(Long user_id, String provider2, String providerUserId2) {
		this.userId = user_id;
		this.provider = provider2;
		this.providerUserId = providerUserId2;
	}
}
