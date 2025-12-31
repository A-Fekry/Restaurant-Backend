package com.spring.resturant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestOrderDto {
    private Double totalPrice;
    private Integer quantity;
    private List<Integer> productIds;
    private List<Integer> productsQuantity;
}
