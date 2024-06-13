package com.jamjam.rest.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	USER("ROLE_USER", "구직자"),
	COMPANY("ROLE_COMPANY", "기업");
	
	private final String key;
	private final String title;
}
