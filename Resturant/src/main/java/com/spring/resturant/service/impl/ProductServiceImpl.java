package com.spring.resturant.service.impl;
import com.spring.resturant.controller.vm.ProductResponseVm;
import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.mapper.CategoryMapper;
import com.spring.resturant.mapper.ProductMapper;
import com.spring.resturant.models.Category;
import com.spring.resturant.models.Product;
import com.spring.resturant.repo.ProductRepo;
import com.spring.resturant.service.CategoryService;
import com.spring.resturant.service.ProductService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ProductDto saveProduct(ProductDto productDto , String catName) throws SystemException {
        if(Objects.nonNull(productDto.getId()))
        {
            throw new RuntimeException("invalid.id");
        }
        Product product = productRepo.findByName(productDto.getName());
        if(!Objects.isNull(product)){
            throw new RuntimeException("product.already.exist");
        }
        if (!Objects.nonNull(productDto.getPrice())) {
            throw new RuntimeException("price.is.null");
        }
        Product newProduct = ProductMapper.INSTANCE.mapProductDtoToProduct(productDto);
        Category cat = CategoryMapper.INSTANCE.mapCategoryDtoToCategory(categoryService.getCategoryByName(catName));
        if(cat == null){
            throw new RuntimeException("category.not.found");
        }

        newProduct.setCategory(cat);
        return ProductMapper.INSTANCE.mapProductToProductDto(productRepo.save(newProduct));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepo.findByName(productDto.getName());
        if(Objects.isNull(product)){
            throw new RuntimeException("product.already.not.exist");
        }
        Product newProduct = ProductMapper.INSTANCE.mapProductDtoToProduct(productDto);

        return ProductMapper.INSTANCE.mapProductToProductDto(productRepo.save(newProduct));
    }

    @Override
    public void deleteProduct(Integer id) {
        ProductDto productDto = ProductMapper.INSTANCE.mapProductToProductDto(productRepo.findById(id).get());
        if (productDto == null){
            throw new RuntimeException("product.not.found");
        }
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDto> SaveListOfProductDto(List<ProductDto> productDtoList) {
        return List.of();
    }

    @Override
    public List<ProductDto> getProductsByIds(List<Integer> products) {
        return ProductMapper.INSTANCE.mapProductListToProductDtoList(productRepo.findAllById(products));
    }

    @Override
    public ProductResponseVm getProducts(Integer pageNumber, Integer pageSize) {
        Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepo.findAll((org.springframework.data.domain.Pageable) pageable);
        ProductResponseVm productResponseVm = new ProductResponseVm(ProductMapper.INSTANCE.mapProductListToProductDtoList(productPage.getContent())
        , productPage.getTotalElements());
        return productResponseVm;
    }

    @Override
    public ProductResponseVm getProductByCategoryID(Integer id,Integer pageNo,Integer pageSize) {
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepo.getProductByCategoryId(id,pageable);
        ProductResponseVm productResponseVm = new ProductResponseVm(ProductMapper.INSTANCE.mapProductListToProductDtoList(productPage.getContent())
                , productPage.getTotalElements());
        return productResponseVm;
    }

    @Override
    public ProductResponseVm getProductByLetters(String letters ,Integer pageNo ,Integer pageSize) throws SystemException{
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = (Page<Product>) productRepo.getProductByLetters(letters, pageable);
        if (productPage.getContent().isEmpty()) {
            throw new RuntimeException("error.internal");
        }
        List<ProductDto> productDtos = ProductMapper.INSTANCE.mapProductListToProductDtoList(productPage.getContent());
        ProductResponseVm productResponseVm = new ProductResponseVm(productDtos, productPage.getTotalElements());
        return productResponseVm;
    }
}
