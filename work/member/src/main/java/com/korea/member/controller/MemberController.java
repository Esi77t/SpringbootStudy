package com.korea.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.member.dto.MemberDTO;
import com.korea.member.dto.ResponseDTO;
import com.korea.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("members")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService service;
	
	@GetMapping
	public ResponseEntity<?> getAllMembers() {
		List<MemberDTO> member = service.getAllMembers();
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(member).build();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		List<MemberDTO> member = service.getMemberByEmail(email);
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(member).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
		List<MemberDTO> member = service.addMember(dto);
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(member).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("{email}/password")
	public ResponseEntity<?> updatePasswordByEmail(@PathVariable String email, @RequestBody MemberDTO dto) {
		String newPassword = dto.getPassword();
		List<MemberDTO> list = service.updatePasswordByEmail(email, newPassword);
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable int id) {
		List<MemberDTO> member = service.deleteMember(id);
		ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(member).build();
		
		return ResponseEntity.ok().body(response);
	}
}
