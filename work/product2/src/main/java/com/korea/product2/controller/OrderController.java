package com.korea.product2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.dto.OrderDTO;
import com.korea.product2.dto.ProductDTO;
import com.korea.product2.dto.ResponseDTO;
import com.korea.product2.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	// 주문 조회 기능
	@GetMapping
	public ResponseEntity<?> getAllOrderTotal() {
		List<OrderDTO> list = orderService.getAllOrderTotalPrices();
		ResponseDTO<OrderDTO> response = ResponseDTO.<OrderDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	// 주문하기 기능
	@PostMapping
	public ResponseEntity<?> saveOrder(@RequestBody OrderDTO dto) {
		List<ProductDTO> list = orderService.save(dto);
		ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
}
