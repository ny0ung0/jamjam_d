package com.jamjam.rest.userController;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.Resume;
import com.jamjam.rest.dto.ResumeDB;
import com.jamjam.rest.dto.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Autowired
	ResumeDao resumeMapper;
	
	@Autowired
	UserDao userMapper;
	
	@Autowired
    HttpSession httpSession;
	
	@PostMapping("/resume")
	public String regResume(@RequestParam("email")String email_, Resume resume, @RequestPart("profile_photo") MultipartFile profilePhoto) {
		System.out.println(resume);
		ResumeDB resumedb = new ResumeDB();
		resumedb.setTitle(resume.getTitle());
		resumedb.setDesired_job(resume.getDesired_job());
		resumedb.setSkills(resume.getSkills());
		resumedb.setLicense(resume.getLicense());
		resumedb.setEducation(resume.getEducation());
		resumedb.setExperience(resume.getExperience());
		resumedb.setPreferences(resume.getPreferences());
		resumedb.setCover_letter_title(resume.getCover_letter_title());
		resumedb.setCover_letter_content(resume.getCover_letter_content());
		resumedb.setDesired_conditions(resume.getDesired_conditions());
		resumedb.setPortfolio(resume.getPortfolio());
		String email = email_;
		User user = userMapper.findByEmail(email);
		resumedb.setUser_id(user.getUser_id());
		String originalName = resume.getFileName();
		resumedb.setProfile_photo(originalName);
		String newName = UUID.randomUUID().toString() + originalName;
		resumedb.setPhoto_newName(newName);
		File file = new File(newName);
		try {
			resume.getProfile_photo().transferTo(file);
			System.out.println("파일업로드 성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		File ufile = new File(uploadPath + newName);
		
		
		System.out.println(email);
		System.out.println(user);
		System.out.println(resumedb);
		int resu = resumeMapper.insertResume(resumedb);
		if(resu > 0) {
			return "성공";
		}else {
			return "실패";
		}
		
	}
}
