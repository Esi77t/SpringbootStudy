package com.example.demo.di3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

// 컴포넌트 스캐닝
// 클래스 앞에 @Component 어노테이션을 붙이고
// 패키지에서 컴포넌트 어노테이션이 붙어있는 클래스를 찾아서
// 객체로 만들어서 Map에 저장하는 기법

@Component class Car{};
@Component class SportCar extends Car{};
@Component class Truck extends Car{};
@Component class Engine{};

class AppContext{
	Map map;
	
	public AppContext() {
		map = new HashMap();
		doComponentScan();
	}
	
	// 패키지에 클래스를 모두 순회하면서 @Component어노테이션이 붙은 클래스를
	// Map에 객체로 등록을 한다
	private void doComponentScan() {
		try {
			// 1. 패키지 내의 클래스 목록을 가져온다
			// 2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어있는지 확인
			// 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
			
			// ClassLoader
			// JVM 내부에서 클래스와 리소스(설정파일, 이미지 등)를 로딩하는 역할을 하는 객체
			
			// AppContext.class.getClassLoader();
			// AppContext클래스를 로딩한 ClassLoader객체를 반환
			ClassLoader classLoader = AppContext.class.getClassLoader();
			
			// ClassPath는 구아바 라이브러리에서 제공하는 클래스로,
			// 클래스 경로 상의 모든 클래스를 탐색하고 사용할 수 있게 도와준다
			ClassPath classPath = ClassPath.from(classLoader);
			
			// 지정한 패키지 내의 최상위 클래스를 가져온다
			Set<ClassPath.ClassInfo> set =
					classPath.getTopLevelClasses("com.example.demo.di3");
			
			// 해당 클래스에 @Component 어노테이션이 있는지 확인한다
			// @Component는 스프링에서 자주 사용되는 어노테이션으로
			// 빈으로 등록하려는 클래스에 부여한다
			for(ClassPath.ClassInfo classInfo : set) {
				// ClassInfo 객체를 
				Class clazz = classInfo.load();
				
				Component component = (Component)clazz.getAnnotation(Component.class);
				
				// @Component 어노테이션이 null이 아니면
				// 즉, 해당 클래스가 어노테이션으로 지정되어 있다면
				// 아래 로직을 실행
				if(component != null) {
					// 클래스의 이름의 첫글자를 소문자로 변환하여 id로 사용할 것이다
					// 변환하는 이유는 스프링에서 생성할 때, 기본적으로 클래스 이름의 첫 글자를 소문자로 사용하기 때문이다
					// getSimpleName() : 패키지 없이 클래스 이름만 반환
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					
					// newInstance() : 기본 생성자를 호출하여 객체를 생성한다
					map.put(id,  clazz.newInstance());
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// 클래스 이름으로 찾기
	Object getBean(String key) {
		return map.get(key);
	}
	
	// 클래스 타입으로 찾기
	// 클래스의 정보 자체를 매개변수로 받는다
	Object getBean(Class clazz) {
		// map.values() : map의 value들을 컬렉션으로 저장
		for(Object obj : map.values()) {
			// 객체 obj가 clazz에 속하는지 확인 (obj instanaceof clazz)
			if(clazz.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
	
}


// 스프링이 직접 관리하도록 시킨 클래스는 메모리에 무조건 한번만 올라간다
// 사용할 때는 메모리에 올라가 있는 객체를 받아서 사용한다
public class Main {
	public static void main(String[] args) {
		AppContext ac = new AppContext();
		
		Car car = (Car)ac.getBean("car");
		System.out.println("car = " + car);
		
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("engine = " + engine);
		
		// 타입을 통해서 map에 저장되어 있는 객체 찾기
		Car car2 = (Car)ac.getBean(Car.class);
		System.out.println("car2 = " + car2);
		
		
		// 실제로는 @ComponentScan어노테이션으로 모든 과정을 생략한다
		// 내부에서 위와 같은 원리로 돌아가고 있다
	}
}
