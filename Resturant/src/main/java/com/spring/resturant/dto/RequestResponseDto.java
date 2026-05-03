package com.spring.resturant.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponseDto {

    private List<ProductDto> products;
    private Integer id;
    private String code;
    private Double totalPrice;
    private Integer quantity;
}
