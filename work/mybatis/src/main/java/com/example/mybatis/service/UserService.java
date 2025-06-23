package com.example.mybatis.service;

import java.util.List;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	
	public List<User> getAllUsers() {
		return userMapper.findAll();
	}
	
	public void createUser(User user) {
		userMapper.insert(user);
	}
}
