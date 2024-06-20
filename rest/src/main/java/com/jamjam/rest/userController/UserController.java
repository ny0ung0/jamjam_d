package com.jamjam.rest.userController;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamjam.rest.dao.JobapplicationDao;
import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dao.JobpostingScrapDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.JobPosting;
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
    private ResumeDao resumeMapper;

    @Autowired
    private UserDao userMapper;
    
    @Autowired
    private JobpostingDao jobpostMapper;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private JobpostingScrapDao jobScrapMapper;
    
    @Autowired
    private JobapplicationDao jobApplicationMapper;
    
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
                            @RequestParam(value = "major[]", required = false) List<String> majors, @RequestParam(value = "gpa[]", required = false) List<String> gpas) {

        System.out.println(resume);
        ResumeDB resumedb = new ResumeDB();
        resumedb.setTitle(resume.getTitle());
        resumedb.setDesired_job(resume.getDesired_job());
        resumedb.setSkills(resume.getSkills());

        // Convert licenses to a single string
        StringBuilder licenses = new StringBuilder();
        for (int i = 0; i < licenseNames.size(); i++) {
            licenses.append(licenseNames.get(i)).append(", ").append(licenseIssuers.get(i)).append(", ").append(licenseDates.get(i));
            if (i < licenseNames.size() - 1) licenses.append(" | ");
        }
        resumedb.setLicense(licenses.toString());

        // Convert education to a single string
        StringBuilder educations = new StringBuilder();
        for (int i = 0; i < educationLevels.size(); i++) {
            educations.append(educationLevels.get(i)).append(", ").append(schoolNames.get(i)).append(", ").append(entranceDates.get(i)).append(", ")
                    .append(graduationDates.get(i)).append(", ").append(graduationStatuses.get(i));
            if (educationLevels.get(i).equals("대학졸업(2,3년)") || educationLevels.get(i).equals("대학교졸업(4년)") || educationLevels.get(i).equals("석사졸업") || educationLevels.get(i).equals("박사졸업")) {
                educations.append(", ").append(majors.get(i)).append(", ").append(gpas.get(i));
            }
            if (i < educationLevels.size() - 1) educations.append(" | ");
        }
        resumedb.setEducation(educations.toString());

        // Convert experiences to a single string
        StringBuilder experiences = new StringBuilder();
        for (int i = 0; i < companyNames.size(); i++) {
            experiences.append(companyNames.get(i)).append(", ").append(departmentNames.get(i)).append(", ").append(startDates.get(i)).append(", ")
                    .append(endDates.get(i)).append(", ").append(positions.get(i)).append(", ").append(responsibilities.get(i));
            if (i < companyNames.size() - 1) experiences.append(" | ");
        }
        resumedb.setExperience(experiences.toString());

        // Convert cover letters to a single string
        StringBuilder coverLetterTitlesStr = new StringBuilder();
        StringBuilder coverLetterContentsStr = new StringBuilder();
        for (int i = 0; i < coverLetterTitles.size(); i++) {
            coverLetterTitlesStr.append(coverLetterTitles.get(i));
            coverLetterContentsStr.append(coverLetterContents.get(i));
            if (i < coverLetterTitles.size() - 1) {
                coverLetterTitlesStr.append(", ");
                coverLetterContentsStr.append(", ");
            }
        }
        resumedb.setCover_letter_title(coverLetterTitlesStr.toString());
        resumedb.setCover_letter_content(coverLetterContentsStr.toString());

        resumedb.setPreferences(resume.getPreferences());
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

    @PutMapping("/updateResume")
    public String updateResume(@RequestParam("resume_id") int resumeId, @RequestParam("email") String email_, Resume resume, @RequestPart("profile_photo") MultipartFile profilePhoto,
                               @RequestParam("license_name[]") List<String> licenseNames, @RequestParam("license_issuer[]") List<String> licenseIssuers,
                               @RequestParam("license_date[]") List<String> licenseDates, @RequestParam("company_name[]") List<String> companyNames,
                               @RequestParam("department_name[]") List<String> departmentNames, @RequestParam("start_date[]") List<String> startDates,
                               @RequestParam("end_date[]") List<String> endDates, @RequestParam("position[]") List<String> positions,
                               @RequestParam("responsibility[]") List<String> responsibilities, @RequestParam("cover_letter_title[]") List<String> coverLetterTitles,
                               @RequestParam("cover_letter_content[]") List<String> coverLetterContents, @RequestParam("education_level[]") List<String> educationLevels,
                               @RequestParam("school_name[]") List<String> schoolNames, @RequestParam("entrance_date[]") List<String> entranceDates,
                               @RequestParam("graduation_date[]") List<String> graduationDates, @RequestParam("graduation_status[]") List<String> graduationStatuses,
                               @RequestParam(value = "major[]", required = false) List<String> majors, @RequestParam(value = "gpa[]", required = false) List<String> gpas, @RequestParam("photo")String photo) {

        ResumeDB resumedb = resumeMapper.findById(resumeId);
        resumedb.setTitle(resume.getTitle());
        resumedb.setDesired_job(resume.getDesired_job());
        resumedb.setSkills(resume.getSkills());

        // Convert licenses to a single string
        StringBuilder licenses = new StringBuilder();
        for (int i = 0; i < licenseNames.size(); i++) {
            licenses.append(licenseNames.get(i)).append(", ").append(licenseIssuers.get(i)).append(", ").append(licenseDates.get(i));
            if (i < licenseNames.size() - 1) licenses.append(" | ");
        }
        resumedb.setLicense(licenses.toString());

        // Convert education to a single string
        StringBuilder educations = new StringBuilder();
        for (int i = 0; i < educationLevels.size(); i++) {
            educations.append(educationLevels.get(i)).append(", ").append(schoolNames.get(i)).append(", ").append(entranceDates.get(i)).append(", ")
                    .append(graduationDates.get(i)).append(", ").append(graduationStatuses.get(i));
            if (educationLevels.get(i).equals("대학졸업(2,3년)") || educationLevels.get(i).equals("대학교졸업(4년)") || educationLevels.get(i).equals("석사졸업") || educationLevels.get(i).equals("박사졸업")) {
                educations.append(", ").append(majors.get(i)).append(", ").append(gpas.get(i));
            }
            if (i < educationLevels.size() - 1) educations.append(" | ");
        }
        resumedb.setEducation(educations.toString());

        // Convert experiences to a single string
        StringBuilder experiences = new StringBuilder();
        for (int i = 0; i < companyNames.size(); i++) {
            experiences.append(companyNames.get(i)).append(", ").append(departmentNames.get(i)).append(", ").append(startDates.get(i)).append(", ")
                    .append(endDates.get(i)).append(", ").append(positions.get(i)).append(", ").append(responsibilities.get(i));
            if (i < companyNames.size() - 1) experiences.append(" | ");
        }
        resumedb.setExperience(experiences.toString());

     // Convert cover letters to a single string
        StringBuilder coverLetterTitlesStr = new StringBuilder();
        StringBuilder coverLetterContentsStr = new StringBuilder();
        for (int i = 0; i < coverLetterTitles.size(); i++) {
            coverLetterTitlesStr.append(coverLetterTitles.get(i));
            coverLetterContentsStr.append(coverLetterContents.get(i));
            if (i < coverLetterTitles.size() - 1) {
                coverLetterTitlesStr.append(", ");
                coverLetterContentsStr.append(", ");
            }
        }
        resumedb.setCover_letter_title(coverLetterTitlesStr.toString());
        resumedb.setCover_letter_content(coverLetterContentsStr.toString());

        resumedb.setPreferences(resume.getPreferences());
        resumedb.setDesired_conditions(resume.getDesired_conditions());
        resumedb.setPortfolio(resume.getPortfolio());
        String email = email_;
        User user = userMapper.findByEmail(email);
        resumedb.setUser_id(user.getUser_id());
        if(profilePhoto.isEmpty()) {
        	resumedb.setPhoto_newName(photo);
        }else {
        	String originalName = resume.getFileName();
            resumedb.setProfile_photo(originalName);
            String newName = UUID.randomUUID().toString() + originalName;
            resumedb.setPhoto_newName(newName);
            File file = new File(newName);
            try {
                resume.getProfile_photo().transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            File ufile = new File(uploadPath + newName);
        }
        

        int resu = resumeMapper.updateResume(resumedb);
        if (resu > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }
    
    
    @GetMapping("/resumes")
    public List<ResumeDB> listResumes(@RequestParam("email") String email) {
        List<ResumeDB> resumedb = resumeMapper.findByEmail(email);
        return resumedb;
    }

    @GetMapping("/resume")
    public ResumeDB viewResume(@RequestParam("resume_id") int resumeId) {
        ResumeDB resume = resumeMapper.findByResumeId(resumeId);
        return resume;
    }
    
    @GetMapping("/popularResumeList")
    public List<JobPosting> popularPost() {
    	List<JobPosting> popularPost = jobpostMapper.getpopoularPostAll();
    	return popularPost;
    }
    
}
