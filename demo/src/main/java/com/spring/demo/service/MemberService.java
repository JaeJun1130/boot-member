package com.spring.demo.service;

import java.util.List;
import java.util.Optional;

import com.spring.demo.domain.Member;
import com.spring.demo.repository.MemberRepository;
import com.spring.demo.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();

	// 회원가입
	public Long join(Member member) {
		// 같은 이름 중복회원 금지
		vaildateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	//중복체크
	private void vaildateDuplicateMember(Member member) {
		// ifPresent => Null이 아닌 값이 있으면
		memberRepository.findName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	//전체 회원조회
	private List<Member> findMember() {
		return memberRepository.findAllI();
	}
	
	private Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
