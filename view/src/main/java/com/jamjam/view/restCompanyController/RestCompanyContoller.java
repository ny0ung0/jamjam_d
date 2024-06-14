package com.jamjam.view.restCompanyController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamjam.view.dto.Company;


@RestController
@RequestMapping("/api")
public class RestCompanyContoller {

	@PostMapping("/company")
	public String register(@RequestBody Company company) {
		System.out.println("restServer 들어옴" +company);
		return "aa";
	}
	

	/*
	 * @GetMapping("/company/{company_id}") public String
	 */
	
	@GetMapping("/address2/{address1}")
	public List<String> address2(@PathVariable("address1") String address1) throws StreamReadException, DatabindException, IOException {
		System.out.println("address1에 접근 성공");
		ObjectMapper objectMapper = new ObjectMapper();
		 List<String> address2= null;
		 List<Map<String, Object>> regionData = objectMapper.readValue(
		            new ClassPathResource("static/json/korea-administrative-district.json").getFile(),
		            new TypeReference<List<Map<String, Object>>>() {
		            });
		      Map<String, List<String>> regions = regionData.stream()
		            .collect(Collectors.toMap(map -> map.keySet().iterator().next(), // key 값 (서울특별시, 부산광역시 등)
		                  map -> (List<String>) map.values().iterator().next() // value 값 (구, 군 리스트)
		            ));
		      for(Map<String, Object> map : regionData) {
		    	  if(map.containsKey(address1)) {
		    	  address2 =(List<String>)map.get(address1);
		    	  }
		      }
		      System.out.println("address2 :" + address2);
		return address2;
	}
}
