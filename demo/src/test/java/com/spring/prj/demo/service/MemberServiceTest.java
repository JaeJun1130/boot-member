package com.spring.prj.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring.demo.domain.Member;
import com.spring.demo.repository.MemoryMemberRepository;
import com.spring.demo.service.MemberService;

public class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beForeach() {
		memberService = new MemberService(memberRepository);
		memberRepository = new MemoryMemberRepository();
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given(조건)
		Member member = new Member();
		member.setName("hello");
		
		//when(검증)
		Long saveId = memberService.join(member);
		
		//then (결과)
		Member findMember = memberService.findOne(saveId).get();
		
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("Spring");
		
		Member member2 = new Member();
		member2.setName("Spring");
		
		//when
		memberService.join(member1);
		//이 예외가 무조권 터져야 성공
		IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		
//		memberService.join(member1);ㅅ
//		try {
//			memberService.join(member2);
//			fail();
//		} catch (IllegalStateException e) {
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		

	}
	
	@Test
	void 회원조회() {
		Member member = new Member();
		member.setName("jaejun");
		Member member1 = new Member();
		member1.setName("aa");
		
		List<Member> findAll = memberService.findMember();
		assertThat(findAll);
	}
	
	@Test
	void findOne() {
	}
}
