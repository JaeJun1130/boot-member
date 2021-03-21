package com.spring.prj.demo.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.spirng.demo.domain.Member;
import com.spirng.demo.repository.MemoryMemberRepository;


public class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach 
	//메소드가 끝나면 동작을 실행
	//테스트는 의존관계(순서)와 상관없이 설계되야된다. 그러므로 클리어 동작실행
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		repository.save(member);
		
//		 Optional<Member> byId = repository.findById(member.getId());
		 Member result = repository.findById(member.getId()).get(); //옵셔널타입은 .get()으로 꺼낼수있다.
		 assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByname() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAllI();
		assertThat(result.size()).isEqualTo(2);
		
	}
}

