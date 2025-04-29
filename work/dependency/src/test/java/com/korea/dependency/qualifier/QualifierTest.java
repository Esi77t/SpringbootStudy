package com.korea.dependency.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class QualifierTest {
	
	// 컴퓨터 타입의 객체를 주입받으려고 한다
	// Laptop도 있고 Desktop도 있다
	// 스프링 입장에서는 개발자가 어떤걸 원하는지 알 수 없다
	@Autowired
	@Qualifier("laptop")
	Computer laptop;
	
	@Autowired
	@Qualifier("desktop")
	Computer desktop;
	
	// 호출할 때마다 @Qualifier 붙이기 싫을 때
	@Autowired
	Computer computer;
	
	@Test
	public void computerTest() {
		log.info("모니터 너비 : {}", laptop.getScreenWidth());
		log.info("모니터 너비 : {}", desktop.getScreenWidth());
		log.info("모니터 너비 : {}", computer.getScreenWidth());
	}
}
