package com.spring.resturant.mapper;

import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = com.spring.resturant.config.DtoConfig.class)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product mapProductDtoToProduct(ProductDto productDto);
    ProductDto mapProductToProductDto(Product product);
    List<ProductDto> mapProductListToProductDtoList(List<Product> productList);
    List<Product> mapProductDtoListToProductList(List<ProductDto> productDtoList);
}
