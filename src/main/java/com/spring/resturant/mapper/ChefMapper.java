package com.spring.resturant.mapper;


import com.spring.resturant.dto.ChefDto;
import com.spring.resturant.models.Chef;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = com.spring.resturant.config.DtoConfig.class)
public interface ChefMapper {
    ChefMapper INSTANCE = Mappers.getMapper(ChefMapper.class);

    Chef chefDtoToChef(ChefDto chefDto);
    ChefDto chefChefToChefDto(Chef chef);
    List<Chef> listChefDtoToChefList(List<ChefDto> chefDtoList);
    List<ChefDto> listChefToChefDtoList(List<Chef> chefList);
}
