package com.jamjam.rest.service.Company;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jamjam.rest.dao.CompanyInfoDao;
import com.jamjam.rest.dto.CompanyInfo;
import com.jamjam.rest.dto.CompanyInfoDto;

@Service
public class CompanyInfoService {

	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	@Autowired
	CompanyInfoDao companyInfoMapper;
	
	public String insertCompanyInfo(CompanyInfoDto companyInfoDto) {
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
				companyInfo.setOrigin_name(originName);
				String newName = UUID.randomUUID().toString() + originName;//중복되지 않는 새이름
				companyInfo.setNew_name(newName);
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
			System.out.println(companyInfo);
			companyInfoMapper.insertCompanyInfo(companyInfo);
			return "등록성공";
		}catch(Exception e){
			return "등록실패" + e.getMessage();
		}
		
	}
	
	public CompanyInfo getCompanyInfo(Integer compnay_id) {
		return companyInfoMapper.getCompanyInfo(compnay_id);
	}
	
}
