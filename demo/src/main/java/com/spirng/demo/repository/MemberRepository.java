package com.spirng.demo.repository;

import java.util.List;
import java.util.Optional;

import com.spirng.demo.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findName(String name);
	List<Member> findAllI();
}
