package com.spring.resturant.controller.vm;

import com.spring.resturant.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseVm {
    List<ProductDto> products;
    long total;
}
