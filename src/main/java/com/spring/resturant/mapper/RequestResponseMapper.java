package com.spring.resturant.mapper;

import com.spring.resturant.dto.RequestResponseDto;
import com.spring.resturant.models.RequestOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RequestResponseMapper {

    RequestResponseMapper INSTANCE = Mappers.getMapper(RequestResponseMapper.class);

    List<RequestResponseDto> requestResponseDtoListToRequestResponseDtoList(List<RequestOrder> requestResponse);
}
