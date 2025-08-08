package com.spring.resturant.service.impl;

import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.mapper.CategoryMapper;
import com.spring.resturant.models.Category;
import com.spring.resturant.repo.CategoryRepo;
import com.spring.resturant.service.CategoryService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) throws SystemException {
        if(Objects.nonNull(categoryDto.getId())){
            throw new RuntimeException("invalid id");
        }
        Category category = CategoryMapper.INSTANCE.mapCategoryDtoToCategory(categoryDto);
        return CategoryMapper.INSTANCE.mapCategoryToCategoryDto(categoryRepo.save(category));
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return CategoryMapper.INSTANCE.mapCategoryListToCategoryDtoList(categoryRepo.findAll());
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) {

    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return CategoryMapper.INSTANCE.mapCategoryToCategoryDto(categoryRepo.findByName(name));
    }
}
