package com.jamjam.view.companyController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamjam.view.dto.Company;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@RequestMapping("/log")
	public String log(HttpSession session) {
		Company com = new Company(
					1,"aaa@aaa.com","123-12-1234","호텔·여행·항공","김대표","세종특별자치시","010-1234-1234",null,null
				);
		session.setAttribute("loginCompany", com);
		
		return "/leindex";
	}

	@RequestMapping("/company_edit_profile")
	public void company_edit_profile(Model model) throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		 List<Map<String, Object>> sectorCategories = objectMapper.readValue(
			        new ClassPathResource("static/json/sector_category.json").getFile(),
			        new TypeReference<List<Map<String, Object>>>() {
			        });
		 List<Map<String, Object>> allSectors = objectMapper.readValue(
			        new ClassPathResource("static/json/sector.json").getFile(),
			        new TypeReference<List<Map<String, Object>>>() {
			        });
		 model.addAttribute("sectorCategories", sectorCategories);
		 System.out.println(sectorCategories);
		 model.addAttribute("allSectors", allSectors);
		 
		   List<Map<String, Object>> regionData = objectMapper.readValue(
		            new ClassPathResource("static/json/korea-administrative-district.json").getFile(),
		            new TypeReference<List<Map<String, Object>>>() {
		            });
		      Map<String, List<String>> regions = regionData.stream()
		            .collect(Collectors.toMap(map -> map.keySet().iterator().next(), // key 값 (서울특별시, 부산광역시 등)
		                  map -> (List<String>) map.values().iterator().next() // value 값 (구, 군 리스트)
		            ));
		      model.addAttribute("regions", regions);
	}
}
