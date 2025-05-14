package com.korea.product2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.dto.ProductDTO;
import com.korea.product2.dto.ResponseDTO;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
	private final ProductService service;
	
	@GetMapping
	public ResponseEntity<?> productList() {
		List<ProductEntity> entity = service.findAll();
		List<ProductDTO> dtos = entity.stream().map(ProductDTO::new).collect(Collectors.toList());
		ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO dto) {
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			List<ProductEntity> entities = service.create(entity);
			List<ProductDTO> response = entities.stream().map(ProductDTO::new).collect(Collectors.toList());
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	
}
