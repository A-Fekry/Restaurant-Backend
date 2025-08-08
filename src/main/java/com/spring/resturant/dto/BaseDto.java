package com.spring.resturant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {

    private Integer id;
    private String name;
    private String logoPath;

    public Integer getId(){
        return id;
    }
}
