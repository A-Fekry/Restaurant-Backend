package com.spring.resturant.service;

import com.spring.resturant.dto.ChefDto;

import java.util.List;

public interface ChefService {

    ChefDto saveChef(ChefDto chefDto) throws Exception;
    List<ChefDto> findAllChef();
    ChefDto findChefById(Integer id);
    ChefDto updateChef(ChefDto chefDto);
    void deleteChef(Integer id);
}
