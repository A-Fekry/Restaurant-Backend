package com.spring.resturant.service;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.models.Category;
import jakarta.transaction.SystemException;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto) throws SystemException;
    Category updateCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    void deleteCategory(CategoryDto categoryDto);
    CategoryDto getCategoryByName(String name);
}
