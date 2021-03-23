package com.spirng.demo;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spirng.demo.repository.JdbcMemberRepository;
import com.spirng.demo.repository.MemberRepository;
import com.spirng.demo.service.MemberService;
import org.springframework.jdbc.datasource.DataSourceUtils;

@Configuration

public class SpringConfig {
	
	
	private final DataSource dataSource;
	
	@Autowired
	 public SpringConfig(DataSource dataSource) {
	 this.dataSource = dataSource;
	 
	 }
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		return new JdbcMemberRepository(dataSource);  
	}

}
