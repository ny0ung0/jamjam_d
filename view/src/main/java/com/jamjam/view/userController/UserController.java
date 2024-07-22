package com.jamjam.view.userController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("resume")
	public String resumeForm() {
		return "/user/resumeForm";
	}
	
	@GetMapping("resumeDetail")
	public String resumeDetsil() {
		return "/user/resumeDetail";
	}
	
	@GetMapping("resumeList")
	public String resumeList() {
		return "/user/resumeList";
	}
	
	@GetMapping("updateResume")
	public String updateResumePage() {
		return "/user/updateResume";
	}
	
	@GetMapping("postList")
	public String postList() {
		return "/user/postingList";
	}
	
	@GetMapping("updateUserInfo")
	public String updateUserInfo() {
		return "/user/updateUserInfo";
	}
	
	@GetMapping("userMyPage")
	public String userMyPage() {
		return "/user/userMyPage";
	}
	
	@GetMapping("scrapList")
	public String userScrapList() {
		return "/user/scrapList";
	}
	
	@GetMapping("appliedList")
	public String appliedList() {
		return "/user/appliedList";
	}
	
	@GetMapping("positionMatch")
	public String jobSeeker_Match() {
		return "/user/jobSeeker_positionMatch";
	}
	
	@GetMapping("positionProposal")
	public String positionProposalList() {
		return "/user/positionProposal";
	}
	@RequestMapping("/job_posting_detail_company")
	public void job_posting_detail_company(@RequestParam(value ="posting_id",required =false) Integer posting_id,Model model) {
		model.addAttribute("posting_id", posting_id);
	}
}
