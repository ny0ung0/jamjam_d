package com.jamjam.rest.userController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamjam.rest.dao.JobpostingDao;
import com.jamjam.rest.dao.PositionProposalDao;
import com.jamjam.rest.dao.ResumeDao;
import com.jamjam.rest.dao.UserDao;
import com.jamjam.rest.dto.JobPosting;
import com.jamjam.rest.dto.PositionProposal;
import com.jamjam.rest.dto.Resume;
import com.jamjam.rest.dto.ResumeDB;
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
	
	@Autowired
	PositionProposalDao positionProposalMapper;
	
	@GetMapping("/matchingPostings")
    public ResponseEntity<?> getMatchingPostings(@RequestParam("email") String email) {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        ResumeDB latestResume = resumeMapper.getLatestResume(user.getUser_id());
        System.out.println(latestResume);
        if (latestResume == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resume found");
        }

        String desiredLocation = latestResume.getDesired_conditions();
        String desiredJob = latestResume.getDesired_job();

        List<JobPosting> matchingPostings = jobpostingMapper.getMatchingJobPostings(desiredLocation, desiredJob);

        return ResponseEntity.ok(matchingPostings);
    }
	
	@GetMapping("/currentResume")
    public ResponseEntity<?> getCurrentResume(@RequestParam("email") String email) {
        User user = userMapper.findByEmail(email);

        ResumeDB latestResume = resumeMapper.getLatestResume(user.getUser_id());
        System.out.println(latestResume);


        return ResponseEntity.ok(latestResume);
    }
	
	@GetMapping("/positionProposal")
	public ResponseEntity<?> getProposalPosition(@RequestParam("email") String email) {
		
		User user = userMapper.findByEmail(email);
		
		List<PositionProposal> proposalList = positionProposalMapper.findProposalsByUserId(user.getUser_id());
		
		return ResponseEntity.ok(proposalList);
		
	}
	
	@PutMapping("/positionProposal")
	public ResponseEntity<?> updateProposalStatue(@RequestBody Map<String, Object> payload) {
	    String status = (String) payload.get("status");
	    Integer proposal_id = (Integer) payload.get("proposal_id");
	    positionProposalMapper.updatePositionproposalStatue(status, proposal_id);
	    return ResponseEntity.ok("업데이트 완료");
	}
}
