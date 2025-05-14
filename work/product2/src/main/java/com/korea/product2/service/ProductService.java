package com.korea.product2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.product2.model.ProductEntity;
import com.korea.product2.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repository;
	
	public List<ProductEntity> findAll() {
		return repository.findAll();
	}
	
	public List<ProductEntity> create(final ProductEntity entity) {
		validate(entity);
		
		// jpa에 데이터를 전달할 때는 Entity 타입이어야 한다
		repository.save(entity);
		return repository.findAll();
	}
	
	private void validate(final ProductEntity entity) {
		if(entity == null) {
			throw new RuntimeException("빈 값을 넣을 수 없습니다.");
		}
	}
}
