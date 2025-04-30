package com.example.todo.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.model.TodoEntity;


@Repository
// 주로 데이터베이스와 상호작용하는 클래스에서 사용되며, CRUD와 같은 데이터베이스 작업을 처리하는데 사용된다
// @Component의 자식 어노테이션이므로 자동으로 bean으로 등록된다
// 다른 계층에서 주입받아 사용할 수 있다
// JpaRepository<T, ID>
// Spring data JPA에서 제공하는 기본인터페이스로 JPA를 사용하여 데이터베이스 작업을
// 쉽게 처리할 수 있도록 도와주는 역할을 한다
// T는 엔티티 클래스
// ID는 엔티티 클래스의 기본 키 타입을 의미
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
	
}
