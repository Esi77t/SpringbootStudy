package com.korea.member.service;

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
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	
	// 특정회원 조회
	public MemberDTO getMemberByEmail(String email) {
		Optional<MemberEntity> entity = repository.findByEmail(email);
		
		return entity.map(MemberDTO::new).orElse(null);
	}
	
	public List<MemberDTO> addMember(MemberDTO dto) {
		MemberEntity entity = dto.toEntity(dto);
		repository.save(entity);
		
		return getAllMembers();
	}
	
	public List<MemberDTO> updatePasswordByEmail(String email, String newPassword) {
		Optional<MemberEntity> entity = repository.findByEmail(email);
		
		entity.ifPresent(member -> {
			member.setPassword(newPassword);
			repository.save(member);
		});
		
		return getAllMembers();
	}
	
	public List<MemberDTO> deleteMember(int id) {
		repository.deleteById(id);
		return getAllMembers();
	}
}
