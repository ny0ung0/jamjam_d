package com.jamjam.rest.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.JobPosting;
import com.jamjam.rest.dto.Resume;
import com.jamjam.rest.dto.User;

@RequestMapping("/user")
@RestController
public class UserPositionMatchController {

	@Autowired
	UserDao userMapper;
	
	@Autowired
	ResumeDao resumeMapper;
	@Autowired
	JobpostingDao jobpostingMapper;
	
	@GetMapping("/matchingPostings")
    public ResponseEntity<?> getMatchingPostings(@RequestParam("email") String email) {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Resume latestResume = resumeMapper.getLatestResume(user.getUser_id());
        System.out.println(latestResume);
        if (latestResume == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resume found");
        }

        String desiredLocation = latestResume.getDesired_conditions();
        String desiredJob = latestResume.getDesired_job();

        List<JobPosting> matchingPostings = jobpostingMapper.getMatchingJobPostings(desiredLocation, desiredJob);

        return ResponseEntity.ok(matchingPostings);
    }
	
}
