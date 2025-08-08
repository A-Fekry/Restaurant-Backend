package com.spring.resturant.controller;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.models.Category;
import com.spring.resturant.service.CategoryService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    ResponseEntity<CategoryDto> saveCategory(@RequestBody @Validated CategoryDto categoryDto) throws SystemException {
        return ResponseEntity.ok(categoryService.saveCategory(categoryDto));
    }

    @GetMapping("/get-all-categories")
    ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
