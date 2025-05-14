package com.korea.product2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.product2.dto.OrderDTO;
import com.korea.product2.dto.ProductDTO;
import com.korea.product2.model.OrderEntity;
import com.korea.product2.model.ProductEntity;
import com.korea.product2.persistence.OrderRepository;
import com.korea.product2.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository o_epository;
	private final ProductRepository p_epository;
	
	// 주문조회
	public List<OrderDTO> getAllOrderTotalPrices() {
		List<Object[]> results = o_epository.findAllOrderTotalPrice();
		
		return OrderDTO.toListOrderDTO(results);
	}
	
	// 주문하기기능 만들기
	// 1. 상품 존재 여부를 확인
	// 2. 재고확인(재고 < 주문개수) 예외 발생
	// 3. 주문하기(주문내역을 저장)
	// 4. 재고 감소시키기
	// 5. 수정된 재고로 다시 데이터베이스에 저장하기
	// 6. 다시 전체 내용을 반환하기
	public List<ProductDTO> save(OrderDTO dto) {
		
		Optional<ProductEntity> option = p_epository.findById(dto.getProductId());
		ProductEntity productEntity = null;
		
		// 상품이 조회가 된다면
		if(option.isPresent()) {
			productEntity = option.get();	// option 객체에서 데이터를 꺼내온다
		} else {
			throw new RuntimeException("상품을 찾을 수 없습니다");
		}
		
		if(productEntity.getProductStock() < dto.getProductCount()) {
			throw new RuntimeException("재고가 부족합니다. 현재 재고 : " + productEntity.getProductStock());
		}
		
		OrderEntity order = OrderEntity.builder()
									.product(productEntity)
									.productCount(dto.getProductCount())
									.build();
		
		o_epository.save(order);
		productEntity.setProductStock(productEntity.getProductStock() - dto.getProductCount());
		p_epository.save(productEntity);
		
		return p_epository.findAll().stream().map(entity -> new ProductDTO(entity)).collect(Collectors.toList());
	}
}
