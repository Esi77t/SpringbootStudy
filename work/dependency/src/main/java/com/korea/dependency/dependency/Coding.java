package com.korea.dependency.dependency;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// 코딩클래스는 컴퓨터에 의존성이 필요하다
@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Coding {
	
	
	private final Computer computer;	// 코딩은 컴퓨터가 있어야 할 수 있다
	
	// 객체를 메모리에 올리면서 생성자는 호출이 된다
	// 이 때 필요한 의존성을 매개변수에 스프링이 주입을 해준다
	//	public Coding(Computer computer) {
	//		this.computer = computer;
	//	}
	
	// setter 주입
	// @Autowired
	// public void setComputer(Computer computer) {
	// 		this.computer = computer;
	// }
}
