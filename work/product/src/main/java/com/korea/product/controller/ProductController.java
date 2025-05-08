package com.korea.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.dto.ProductDTO;
import com.korea.product.service.ProductService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	// 상품 추가
	// 사용자로부터 데이터를 받을 때는 DTO를 쓰자
	// JSON으로 넘어오는걸 자바 객체로 받을 땐 @RequestBody
	// 사용자가 인터페이스(리액트)에서 데이터가 넘어온다 -> 매개변수로 받는다
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto) {
		List<ProductDTO> response = productService.addProduct(dto);
		return ResponseEntity.ok(response);
	}
	
	// 상품 조회
	@GetMapping
	public ResponseEntity<?> getAllProducts(
			@RequestParam(required=false) Double minPrice) {
		List<ProductDTO> products = productService.getFilterProducts(minPrice);
		return ResponseEntity.ok(products);
	}
	
	// 상품 수정
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDTO dto) {
		List<ProductDTO> updateProduct = productService.updateProduct(id, dto);
		return ResponseEntity.ok(updateProduct);
	}
	
	// 상품 삭제
	// @PathVariable을 이용하여 삭제하기
	// 경로 : /{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		boolean isDeleted = productService.deleteProduct(id);
		// 삭제가 정상 처리되면 본문 없이 204 No Content 응답
		if(isDeleted) {
			return ResponseEntity.noContent().build();
		}
		// 삭제 실패시 404 NotFound 응답
		return ResponseEntity.notFound().build();
	}
}
