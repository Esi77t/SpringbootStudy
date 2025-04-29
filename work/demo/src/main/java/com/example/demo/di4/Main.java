package com.example.demo.di4;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//ctrl + shift + o
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

@Component class Car{
	Engine engine;
	Wheel wheel;
	@Override
	public String toString() {
		return "Car [engine=" + engine + ", wheel = " + wheel + "]";
	}
};
@Component class SportCar extends Car{};
@Component class Truck extends Car{};
@Component class Engine{};
@Component class Wheel{};

class AppContext{
	Map map;
	
	public AppContext() {
		map = new HashMap();
		doComponentScan();
		doAutowired();
	}
	
	// map에 저장된 객체의 객체변수중 @Autowired가 붙어 있으면
	// 타입에 맞는 객체를 찾아서 연결한다
	// @Autowired가 아래의 함수 역할을 한다
	private void doAutowired() {
		try {
			// map에 들어있는 객체를 하나씩 꺼내서
			for(Object obj : map.values()) {
				// getClass()로 클래스 정보를 얻어오고
				// getDeclaredFields()로 해당 클래스에 있는 필드의 정보들을 배열로 반환
				for(Field fld : obj.getClass().getDeclaredFields()) {
					// 필드에 Autowired 어노테이션이 붙어있는 지 확인하고
					if(fld.getAnnotation(Autowired.class) != null) {
						// 그 필드에 맞는 객체가 있으면 셋팅을 해라
						fld.set(obj, getBean(fld.getType()));
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 패키지에 클래스를 모두 순회하면서 @Component어노테이션이 붙은 클래스를
	// Map에 객체로 등록을 한다
	// @ComponentScan이 아래의 함수 역할을 한다
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
					classPath.getTopLevelClasses("com.example.demo.di4");
			
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


public class Main {
	public static void main(String[] args) {
		AppContext ac = new AppContext();
		
		// car객체에 필드로 engine과 wheel을 갖는다.
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		Wheel wheel = (Wheel)ac.getBean("wheel");
		
		// 원래 자바에서는 필드에 직접 객체를 넣어줘야 한다(의존성 주입)
		// car.engine = engine;
		// car.wheel = wheel;
		
		System.out.println(car);
	}
}

