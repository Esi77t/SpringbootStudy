package com.korea.member.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository repository;
	
	// 전체 회원 조회
	public List<MemberDTO> getAllMembers() {
		return repository.findAll().stream().map(MemberDTO::fromEntity).collect(Collectors.toList());
	}
	
	// 특정회원 조회
	public List<MemberDTO> getMemberByEmail(String email) {
		Optional<MemberEntity> option = repository.findByEmail(email);
		MemberDTO dto = null;
		if(option.isPresent()) {
			MemberEntity entity = option.get();
			dto = MemberDTO.fromEntity(entity);
		}
		return Arrays.asList(dto);
	}
	
	public List<MemberDTO> addMember(MemberDTO dto) {
		MemberEntity entity = MemberEntity.builder()
								.name(dto.getName())
								.email(dto.getEmail())
								.password(dto.getPassword())
								.build();
		repository.save(entity);
		
		return getAllMembers();
	}
	
	public List<MemberDTO> updatePasswordByEmail(String email, String newPassword) {
		Optional<MemberEntity> option = repository.findByEmail(email);
		
		if(option.isPresent()) {
			MemberEntity entity = option.get();
			entity.setPassword(newPassword);
			repository.save(entity);
		}
		
		return getAllMembers();
	}
	
	public List<MemberDTO> deleteMember(int id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new RuntimeException("조회된 회원이 없습니다");
		}
		
		return null;
	}
}
