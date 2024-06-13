package com.jamjam.view.commonController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RegCompanyController {

	@RequestMapping("/reg_company_form")
	public void reg_company_form(Model model) throws StreamReadException, DatabindException, IOException {
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
	}
}
