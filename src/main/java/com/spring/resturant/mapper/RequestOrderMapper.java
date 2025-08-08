package com.spring.resturant.mapper;

import com.spring.resturant.dto.RequestOrderDto;
import com.spring.resturant.models.RequestOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = com.spring.resturant.config.DtoConfig.class)
public interface RequestOrderMapper {

    RequestOrderMapper INSTANCE = Mappers.getMapper(RequestOrderMapper.class);

    RequestOrder RequestOrderDtoToRequestOrder(RequestOrderDto requestOrderDto);
    List<RequestOrderDto> RequestOrderListToRequestOrderDtoList(List<RequestOrder> requestOrderList);
}
