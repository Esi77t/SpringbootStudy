package com.korea.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.user.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	// 추가적으로 사용자 검색 기능이 필요하다면 메서드를 정의할 수 있다
	UserEntity findByEmail(String email);
}
