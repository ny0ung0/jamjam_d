package com.jamjam.rest.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobapplicationDao;
import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dao.JobpostingScrapDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.JobApplication;
import com.jamjam.rest.dto.JobAppliedList;
import com.jamjam.rest.dto.JobPosting;
import com.jamjam.rest.dto.JobPostingScrap;
import com.jamjam.rest.dto.JobScrapList;

@RestController
@RequestMapping("/user")
public class UserApplicationController {

	@Autowired
    private ResumeDao resumeMapper;

    @Autowired
    private UserDao userMapper;
    
    @Autowired
    private JobpostingDao jobpostMapper;
	
	@Autowired
    private JobpostingScrapDao jobScrapMapper;
    
    @Autowired
    private JobapplicationDao jobApplicationMapper;
	
    
   
    
	@GetMapping("/jobPosts")
    public List<JobPosting> jobPostList(){
    	List<JobPosting> postList = jobpostMapper.getPostingAll();
    	
		return postList;
    	
    }
    
    @PostMapping("/applyJob/{post_id}")
    public ResponseEntity<?> applyJob(@PathVariable("post_id")Integer post_id, @RequestParam ("email") String email, @RequestParam("resume_id") Integer resume_id) {
        Integer user_id = userMapper.findByEmailInteger(email);
        
        JobApplication jobApplication = new JobApplication();
        jobApplication.setUser_id(user_id);
        jobApplication.setPosting_id(post_id);
        jobApplication.setResume_id(resume_id);
        jobApplicationMapper.applyJob(jobApplication);

        return ResponseEntity.ok("Job application successful");
    }

    @PostMapping("/scrapJob/{post_id}")
    public ResponseEntity<?> scrapJob(@PathVariable("post_id")Integer post_id, @RequestParam ("email") String email) {


        Integer user_id = userMapper.findByEmailInteger(email);
        
        JobPostingScrap jobPostingScrap = new JobPostingScrap();
        jobPostingScrap.setUser_id(user_id);
        jobPostingScrap.setPosting_id(post_id);

        jobScrapMapper.scrapJob(jobPostingScrap);

        return ResponseEntity.ok("Job scrap successful");
    }
    
    @GetMapping("/scrapList")
    public ResponseEntity<List<JobScrapList>> scrapList(@RequestParam("email") String email) {
        try {
            Integer user_id = userMapper.findByEmailInteger(email);
            List<JobScrapList> scrapListDetails = jobScrapMapper.getScrapListDetails(user_id);
            return ResponseEntity.ok(scrapListDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/appliedList")
    public ResponseEntity<List<JobAppliedList>> appliedList(@RequestParam("email") String email){
    	try {
    		Integer user_id = userMapper.findByEmailInteger(email);
    		List<JobAppliedList> applicationListDetails = jobApplicationMapper.getJobApplicationListByUserId(user_id);
    		return ResponseEntity.ok(applicationListDetails);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    	}
    }
    
    @GetMapping("/applied/{post_id}")
    public ResponseEntity<?> hasApplied(@PathVariable("post_id") Integer post_id, @RequestParam ("email") String email) {
        
        Integer user_id = userMapper.findByEmailInteger(email);
        
        boolean hasApplied = jobApplicationMapper.hasApplied(user_id, post_id);
        return ResponseEntity.ok(hasApplied);
    }
    
    @GetMapping("/applied")
    public ResponseEntity<?> passCheck (@RequestParam ("application_id") Integer application_id, @RequestParam ("email") String email) {
        
        Integer user_id = userMapper.findByEmailInteger(email);
        
        JobApplication jobApplication = jobApplicationMapper.getJobApplicationByUserIdAndPostingId(user_id, application_id);
        return ResponseEntity.ok(jobApplication);
    }
    

    @GetMapping("/scrapped/{post_id}")
    public ResponseEntity<?> hasScrapped(@PathVariable("post_id") Integer post_id, @RequestParam ("email") String email) {
        
        Integer user_id = userMapper.findByEmailInteger(email);
        
        boolean hasScrapped = jobScrapMapper.hasScrapped(user_id, post_id);
        return ResponseEntity.ok(hasScrapped);
    }
    
    @DeleteMapping("/cancelScrapJob/{post_id}")
    public ResponseEntity<?> cancelScrapJob(@PathVariable("post_id")Integer post_id, @RequestParam ("email") String email) {


        Integer user_id = userMapper.findByEmailInteger(email);
        
        

        jobScrapMapper.cancelScrapJob(user_id, post_id);

        return ResponseEntity.ok("Job scrap cancel successful");
    }
    
    @DeleteMapping("cancelApplied/{application_id}")
    public ResponseEntity<?> cancelApplied(@PathVariable("application_id")Integer application_id, @RequestParam("email")String email){
    	Integer user_id = userMapper.findByEmailInteger(email);
    	
    	jobApplicationMapper.cancelApplied(user_id, application_id);
    	
    	return ResponseEntity.ok("Job applied cancel Successful");
    }
    
    @PutMapping("/postViewUp/{post_id}")
    public ResponseEntity<?> postViewUp(@PathVariable("post_id") Integer post_id) {
        JobPosting view = jobpostMapper.getJobPosting(post_id);
        Integer view_count = view.getView_count();

        view_count++;

        jobpostMapper.updatePostView(view_count, post_id);

        return ResponseEntity.ok("post view up successful");
    }
    
	
}
