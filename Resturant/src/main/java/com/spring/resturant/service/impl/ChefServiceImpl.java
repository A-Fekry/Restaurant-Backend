package com.spring.resturant.service.impl;

import com.spring.resturant.dto.ChefDto;
import com.spring.resturant.mapper.ChefMapper;
import com.spring.resturant.models.Chef;
import com.spring.resturant.repo.ChefRepo;
import com.spring.resturant.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChefServiceImpl implements ChefService {
    @Autowired
    private ChefRepo chefRepo;

    @Override
    public ChefDto saveChef(ChefDto chefDto) throws RuntimeException {
        if(Objects.nonNull(chefDto.getId()))
        {
            throw new RuntimeException("invalid id");
        }
        Chef chef = chefRepo.save(ChefMapper.INSTANCE.chefDtoToChef(chefDto));
        return ChefMapper.INSTANCE.chefChefToChefDto(chef);
    }

    @Override
    public List<ChefDto> findAllChef() {
        return ChefMapper.INSTANCE.listChefToChefDtoList(chefRepo.findAll());
    }

    @Override
    public ChefDto findChefById(Integer id) {
        return null;
    }

    @Override
    public ChefDto updateChef(ChefDto chefDto) {
        return null;
    }

    @Override
    public void deleteChef(Integer id) {

    }
}
