package com.jamjam.rest.companyController;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.CompanyInfoDao;
import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dto.CompanyInfo;
import com.jamjam.rest.dto.CompanyInfoDto;
import com.jamjam.rest.dto.JobPosting;



@RestController
@RequestMapping("/company")
public class CompanyController {

	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Autowired
	JobpostingDao jobpostionMapper;
	@Autowired
	CompanyInfoDao companyInfoMapper;
	
	@PostMapping("/jobposting")
	public String insertJobposting(@RequestBody JobPosting jobposting) {
		System.out.println("채용공고" + jobposting);
		jobpostionMapper.insertJobposting(jobposting);
		return "채용공고 등록";
	}
	
	@PostMapping("/companyInfo")
	public ResponseEntity<?> insertCompanyinfo11(@ModelAttribute CompanyInfoDto companyInfoDto) {
		System.out.println("컨트롤러 들어옴");
		CompanyInfo companyInfo = new CompanyInfo();
		companyInfo.setCompany_id(companyInfoDto.getCompany_id());
		companyInfo.setDescription(companyInfoDto.getDescription());
		companyInfo.setNumber_of_employees(companyInfoDto.getNumber_of_employees());
		companyInfo.setWorking_environment(companyInfoDto.getWorking_environment());
		companyInfo.setBenefits(companyInfoDto.getBenefits());
		companyInfo.setIdeal_candidate(companyInfoDto.getIdeal_candidate());
		companyInfo.setMain_contact(companyInfoDto.getMain_contact());
		companyInfo.setMain_email(companyInfoDto.getMain_email());
		//파일 관련 멤버변수 세팅
				String originName =companyInfoDto.getFileName();
				companyInfo.setOriginName(originName);
				String newName = UUID.randomUUID().toString() + originName;//중복되지 않는 새이름
				companyInfo.setNewName(newName);
				//String thumbNailName = "";
		File file = new File(newName);
		
		try {
			companyInfoDto.getFile().transferTo(file);
			System.out.println("파일 업로드 성공....");
			
			//썸네일 생성
			//String thumbnailSaveName = "s_" + newName;
			//board.setThumbnailName(thumbnailSaveName);
			
			//File thumbfile = new File(uploadPath + thumbnailSaveName);
			File ufile = new File(uploadPath + newName);
			
			//Thumbnails.of(ufile).size(100,100).toFile(thumbfile);
		}catch(Exception e){
			
		}
		companyInfoMapper.insertCompanyInfo(companyInfo);
		return ResponseEntity.ok().body("ddddd");
	}
	

	
}
