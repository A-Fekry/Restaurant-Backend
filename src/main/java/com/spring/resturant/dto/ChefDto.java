package com.spring.resturant.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefDto extends BaseDto{

    private String spec;

    private String tweLink;

    private String faceLink;

    private String instaLink;
}
