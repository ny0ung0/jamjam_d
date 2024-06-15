package com.jamjam.rest.userController;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String regResume(@RequestParam("email") String email_, Resume resume, @RequestPart("profile_photo") MultipartFile profilePhoto,
                            @RequestParam("license_name[]") List<String> licenseNames, @RequestParam("license_issuer[]") List<String> licenseIssuers,
                            @RequestParam("license_date[]") List<String> licenseDates, @RequestParam("company_name[]") List<String> companyNames,
                            @RequestParam("department_name[]") List<String> departmentNames, @RequestParam("start_date[]") List<String> startDates,
                            @RequestParam("end_date[]") List<String> endDates, @RequestParam("position[]") List<String> positions,
                            @RequestParam("responsibility[]") List<String> responsibilities, @RequestParam("cover_letter_title[]") List<String> coverLetterTitles,
                            @RequestParam("cover_letter_content[]") List<String> coverLetterContents, @RequestParam("education_level[]") List<String> educationLevels,
                            @RequestParam("school_name[]") List<String> schoolNames, @RequestParam("entrance_date[]") List<String> entranceDates,
                            @RequestParam("graduation_date[]") List<String> graduationDates, @RequestParam("graduation_status[]") List<String> graduationStatuses,
                            @RequestParam("major[]") List<String> majors, @RequestParam("gpa[]") List<String> gpas) {

        System.out.println(resume);
        ResumeDB resumedb = new ResumeDB();
        resumedb.setTitle(resume.getTitle());
        resumedb.setDesired_job(resume.getDesired_job());
        resumedb.setSkills(resume.getSkills());
        resumedb.setLicense(licenseNames.toString() + ", " + licenseIssuers.toString() + ", " + licenseDates.toString());
        resumedb.setEducation(educationLevels.toString() + ", " + schoolNames.toString() + ", " + entranceDates.toString() + ", " +
                graduationDates.toString() + ", " + graduationStatuses.toString() + ", " + majors.toString() + ", " + gpas.toString());
        resumedb.setExperience(companyNames.toString() + ", " + departmentNames.toString() + ", " + startDates.toString() + ", " +
                endDates.toString() + ", " + positions.toString() + ", " + responsibilities.toString());
        resumedb.setPreferences(resume.getPreferences());
        resumedb.setCover_letter_title(coverLetterTitles.toString());
        resumedb.setCover_letter_content(coverLetterContents.toString());
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        File ufile = new File(uploadPath + newName);

        System.out.println(email);
        System.out.println(user);
        System.out.println(resumedb);
        int resu = resumeMapper.insertResume(resumedb);
        if (resu > 0) {
            return "성공";
        } else {
            return "실패";
        }

    }
    
    

    @GetMapping("/resumes")
    public List<ResumeDB> listResumes(@RequestParam("email") String email) {
    	 System.out.println(email);
    	 List<ResumeDB> resumedb = resumeMapper.findByEmail(email);
    	 System.out.println(resumedb.toString());
    	 
        return resumeMapper.findByEmail(email);
    }

    @GetMapping("/resume")
    public ResumeDB viewResume(@RequestParam("resume_id") int resumeId) {
        return resumeMapper.findById(resumeId);
    }
    

}
