package com.spring.resturant.service;

import com.spring.resturant.controller.vm.ProductResponseVm;
import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.models.Product;
import jakarta.transaction.SystemException;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductService {
    ProductDto saveProduct(ProductDto productDto , String catName) throws SystemException;
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Integer id);
    List<ProductDto> SaveListOfProductDto(List<ProductDto> productDtoList);
    List<ProductDto> getProductsByIds(List<Integer> products);
    ProductResponseVm getProducts(Integer pageNo, Integer pageSize);
    List<ProductDto> getProductByName(String productName);
    List<ProductDto> getProductByPrice(double productPrice);
    ProductResponseVm getProductByCategoryID(Integer id,Integer pageNo,Integer pageSize);
    ProductResponseVm getProductByLetters(String letters,Integer pageNo,Integer pageSize) throws SystemException;

}
