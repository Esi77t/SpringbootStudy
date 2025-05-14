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
import com.korea.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
	private final MemberService service;
	
	@GetMapping
	public ResponseEntity<?> getAllMembers() {
		List<MemberDTO> member = service.getAllMembers();
		return ResponseEntity.ok().body(member);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getFindMemberByEmail(@PathVariable String email) {
		MemberDTO member = service.getMemberByEmail(email);
		
		return ResponseEntity.ok().body(member);
	}
	
	@PostMapping
	public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
		List<MemberDTO> member = service.addMember(dto);
		
		return ResponseEntity.ok().body(member);
	}
	
	@PutMapping("/{email}/password")
	public ResponseEntity<?> updatePasswordByEmail(@PathVariable String email, @RequestBody String newPassword) {
		List<MemberDTO> member = service.updatePasswordByEmail(email, newPassword);
		
		return ResponseEntity.ok().body(member);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable int id) {
		List<MemberDTO> member = service.deleteMember(id);
		
		return ResponseEntity.ok().body(member);
	}
}
