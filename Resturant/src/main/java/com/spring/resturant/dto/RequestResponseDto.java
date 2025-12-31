package com.spring.resturant.dto;

import com.spring.resturant.models.Product;
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

    private List<Product> products;
    private Integer id;
    private String code;
    private Double totalPrice;
    private Integer quantity;
}
