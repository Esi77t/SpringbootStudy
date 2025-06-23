package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.mybatis.domain.User;

@Mapper		// Mapper 어노테이션을 붙이면 자동을 Bean으로 등록
public interface UserMapper {
	
	@Select("SELECT * FROM 'user'")
	List<User> findAll();	// 전체 유저 조회
	
	@Select("SELECT * FROM 'user' WHERE id=#{id}")
	User findById(Long id);	// id를 통한 유저 한건 조회
	
	@Insert("INSERT INTO ")
	void insert(User user);
	
	void update(User user);
	void delete(Long id);
	
}
