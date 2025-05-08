package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.product.dto.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	@Autowired
	private final ProductRepository repository;
	
	// 상품 추가
	public List<ProductDTO> addProduct(ProductDTO productDTO) {
		ProductEntity entity = ProductDTO.toEntity(productDTO);
		repository.save(entity);
		
		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	// 상품 조회
	public List<ProductDTO> getFilterProducts(Double minPrice) {
		List<ProductEntity> products = repository.findAll();
		
		// 가격 필터링
		if(minPrice != null) {
			products = products.stream()
					.filter(product -> product.getPrice() >= minPrice)
					.collect(Collectors.toList());
		}
		
		return products.stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	// 상품수정(id를 갖고 수정)
	public List<ProductDTO> updateProduct(int id, ProductDTO dto) {
		// id를 통해서 데이터가 실제로 존재하는지 확인
		Optional<ProductEntity> optionalEntity = repository.findById(id);
		
		// 데이터가 존재한다면
		if(optionalEntity.isPresent()) {
			// Optional에 들어있는 데이터를 꺼낸다
			ProductEntity entity = optionalEntity.get();
			// 수정하려고 하는 데이터로 다시 세팅
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			// 다시 세팅했으면 데이터베이스에 다시 저장
			repository.save(entity);
		}
		
		return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	// 상품 삭제
	public boolean deleteProduct(int id) {
		// id를 통해서 데이터가 실제로 존재하는지 확인
		Optional<ProductEntity> optionalEntity = repository.findById(id);
		
		// 만약존재한다면 데이터베이스에서 삭제
		if(optionalEntity.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
}
