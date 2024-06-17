package com.jamjam.rest.commonController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/json")
public class JsonController {

	@RequestMapping("/job_category")
	public List<Map<String, Object>> jobRole1() throws StreamReadException, DatabindException, IOException {
		// System.out.println("jobRole1 접근성공");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> jobCategories = objectMapper.readValue(
				new ClassPathResource("static/json/job_category.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		// System.out.println(jobCategories);
		return jobCategories;
	}

	@RequestMapping("/workType")
	public List<Map<String, Object>> workType() throws StreamReadException, DatabindException, IOException {
		//System.out.println("workType 접근성공");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> workTypes = objectMapper.readValue(
				new ClassPathResource("static/json/workType.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		//System.out.println(workTypes);
		return workTypes;
	}

	@RequestMapping("/eduCode")
	public List<Map<String, Object>> eduCode() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> eduCodes = objectMapper.readValue(
				new ClassPathResource("static/json/eduCode.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		return eduCodes;
	}

	@GetMapping("/address2/{address1}")
	public List<String> address2(@PathVariable("address1") String address1)
			throws StreamReadException, DatabindException, IOException {
		//System.out.println("address1에 접근 성공 +" + address1);
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> address2 = null;
		List<Map<String, Object>> regionData = objectMapper.readValue(
				new ClassPathResource("static/json/korea-administrative-district.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		Map<String, List<String>> regions = regionData.stream()
				.collect(Collectors.toMap(map -> map.keySet().iterator().next(), // key 값 (서울특별시, 부산광역시 등)
						map -> (List<String>) map.values().iterator().next() // value 값 (구, 군 리스트)
				));
		for (Map<String, Object> map : regionData) {
			if (map.containsKey(address1)) {
				address2 = (List<String>) map.get(address1);
			}
		}
		//System.out.println("address2 :" + address2);
		return address2;
	}

	@GetMapping("/job/{job1}")
	public List<String> job2(@PathVariable("job1") Integer job1)
			throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> allJobs = objectMapper.readValue(
				new ClassPathResource("static/json/job.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		List<String> job2 = new ArrayList<>();
		for (Map<String, Object> map : allJobs) {
			if (((Integer) map.get("직무 상위 코드")) == job1) {

				job2.add((String) map.get("직무키워드명"));
			}
		}
		//System.out.println(job2);
		return job2;
	}

	@GetMapping("/sector_category")
	public List<Map<String, Object>> sector_category() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> sectorCategories = objectMapper.readValue(
				new ClassPathResource("static/json/sector_category.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});

		return sectorCategories;
	}

	@GetMapping("/sector/{sector1}")
	public List<String> sector(@PathVariable("sector1") Integer sector1) throws StreamReadException, DatabindException, IOException {
		//System.out.println("섹터 들어옴");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> allSectors = objectMapper.readValue(
				new ClassPathResource("static/json/sector.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		List<String> result =  new ArrayList<>();
		for(Map<String, Object> map : allSectors) {
			if(((Integer)map.get("상위코드")) == sector1) {
				result.add((String)map.get("업종명"));
			}
		}
		//System.out.println(result);
		return result;

	}

	@GetMapping("/address1")
	public Map<String, List<String>> address1() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> regionData = objectMapper.readValue(
				new ClassPathResource("static/json/korea-administrative-district.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		Map<String, List<String>> regions = regionData.stream()
				.collect(Collectors.toMap(map -> map.keySet().iterator().next(), // key 값 (서울특별시, 부산광역시 등)
						map -> (List<String>) map.values().iterator().next() // value 값 (구, 군 리스트)
				));

		return regions;
	}
	
	@GetMapping("/required_skill1")
	public Map<String, List<String>> required_skill1() throws StreamReadException, DatabindException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> skillData = objectMapper.readValue(
				new ClassPathResource("static/json/skillList.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		Map<String, List<String>> skills = skillData.stream()
				.collect(Collectors.toMap(map -> map.keySet().iterator().next(), // key 값 (서울특별시, 부산광역시 등)
						map -> (List<String>) map.values().iterator().next() // value 값 (구, 군 리스트)
				));

		return skills;
	}
	
	@GetMapping("/required_skill2/{required_skill1}")
	public List<String> required_skill2(@PathVariable("required_skill1") String required_skill1)
			throws StreamReadException, DatabindException, IOException {
		//System.out.println("address1에 접근 성공 +" + address1);
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> required_skill2 = null;
		List<Map<String, Object>> skillData = objectMapper.readValue(
				new ClassPathResource("static/json/skillList.json").getFile(),
				new TypeReference<List<Map<String, Object>>>() {
				});
		Map<String, List<String>> skills = skillData.stream()
				.collect(Collectors.toMap(map -> map.keySet().iterator().next(), // key 값 (서울특별시, 부산광역시 등)
						map -> (List<String>) map.values().iterator().next() // value 값 (구, 군 리스트)
				));
		for (Map<String, Object> map : skillData) {
			if (map.containsKey(required_skill1)) {
				required_skill2 = (List<String>) map.get(required_skill1);
			}
		}
		
		return required_skill2;
	}
	
}
