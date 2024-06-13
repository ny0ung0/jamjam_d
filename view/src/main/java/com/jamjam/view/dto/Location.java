package com.jamjam.view.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
  private Long location_id;
  private String region;
  private String sub_region;
  
  public Location(String region, String sub_region) {
		this.region = region;
		this.sub_region = sub_region;
		
	}
}
