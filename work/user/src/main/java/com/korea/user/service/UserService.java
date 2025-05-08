package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  // final이나 @NonNull이 붙은 필드를 생성자의 매개변수로 포함한다
public class UserService {
	
	private final UserRepository repository;
	
	public List<UserDTO> create(UserEntity entity) {
		repository.save(entity);	// 데이터베이스에 저장
		
		return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	public List<UserDTO> getAllUsers() {
		return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	public UserDTO getUserByEmail(String email) {
		UserEntity entity = repository.findByEmail(email);
		
		return new UserDTO(entity);
	}
	
	public List<UserDTO> updateUser(UserEntity entity) {
		Optional<UserEntity> userEntityOptional = repository.findById(entity.getId());
		
		userEntityOptional.ifPresent(userEntity -> {
			userEntity.setName(entity.getName());
			userEntity.setEmail(entity.getEmail());
			
			repository.save(userEntity);
		});
		
		return getAllUsers();
	}
}
