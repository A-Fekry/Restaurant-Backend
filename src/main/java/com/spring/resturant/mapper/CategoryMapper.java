package com.spring.resturant.mapper;

import com.spring.resturant.config.DtoConfig;
import com.spring.resturant.dto.CategoryDto;
import com.spring.resturant.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", config = DtoConfig.class)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category mapCategoryDtoToCategory(CategoryDto categoryDto);
    CategoryDto mapCategoryToCategoryDto(Category category);
    List<CategoryDto> mapCategoryListToCategoryDtoList(List<Category> categoryList);
    List<Category> mapCategoryDtoListToCategoryList(List<CategoryDto> categoryDtoList);
}
