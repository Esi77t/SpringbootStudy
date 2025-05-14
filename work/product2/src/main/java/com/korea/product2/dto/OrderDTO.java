package com.korea.product2.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private int orderId;
	private int productId;
	private String productName;
	private int productCount;
	private int productPrice;
	private int totalPrice;
	private String orderDate;
	
	// Object[]에 들어있는 데이터를 OrderDTO로 변환
	public static List<OrderDTO> toListOrderDTO(List<Object[]> list) {
		// Object -> Integer -> int
		return list.stream().map(result -> OrderDTO.builder()
                .orderId((int)result[0])
                .productName((String)result[1])
                .productCount((int)result[2])
                .productPrice((int)result[3])
                .totalPrice((int)result[4])
                .orderDate((String)result[5])
                .build()).collect(Collectors.toList());
	}

}
