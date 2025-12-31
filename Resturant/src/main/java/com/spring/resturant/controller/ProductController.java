package com.spring.resturant.controller;

import com.spring.resturant.controller.vm.ProductResponseVm;
import com.spring.resturant.dto.ProductDto;
import com.spring.resturant.dto.SaveProductRequest;
import com.spring.resturant.models.Product;
import com.spring.resturant.service.ProductService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    ResponseEntity<ProductDto> saveProduct(@RequestBody SaveProductRequest data) throws SystemException {
        return ResponseEntity.ok(productService.saveProduct(data.getProductDto(),data.getCatName()));
    }

    @GetMapping("/get-product-by-category-id/{categoryId}/pageNo/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ProductResponseVm> getProductByCategoryId(@PathVariable("categoryId") Integer id, @PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(productService.getProductByCategoryID(id,pageNo,pageSize));
    }

    @GetMapping("/pageNo/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ProductResponseVm> getProducts(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return ResponseEntity.ok(productService.getProducts(pageNo,pageSize));
    }

    @GetMapping("/search-by-letters/{letters}/pageNo/{pageNo}/pageSize/{pageSize}")
    ResponseEntity<ProductResponseVm> getProductByLetters(@PathVariable("letters") String letters,@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) throws SystemException {
        return ResponseEntity.ok(productService.getProductByLetters(letters,pageNo,pageSize));
    }

    @DeleteMapping("/remove")
    void removeProduct(@RequestBody ProductDto productDto) throws SystemException {
        productService.deleteProduct(productDto.getId());
    }

    @PostMapping("/update")
    ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws SystemException {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }
}
