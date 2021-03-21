package com.spirng.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spirng.demo.domain.Member;
import com.spirng.demo.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/*회원등록 start */
	@GetMapping("/members/new")
	public String creatForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
//	member/new 에 form name 이랑 <-> MemberForm 에 name이랑 매칭
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		
		memberService.join(member);
		return "redirect:/";
	}
	/*회원등록 End */
	
	/*회원조회 start */
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMember();
		model.addAttribute("members", members);
		return "members/memberList";

	}
	
}
