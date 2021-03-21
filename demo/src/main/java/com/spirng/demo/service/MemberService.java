package com.spirng.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spirng.demo.domain.Member;
import com.spirng.demo.repository.MemberRepository;
import com.spirng.demo.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;
	
	//생성자 주입
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

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
	public List<Member> findMember() {
		return memberRepository.findAllI();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
