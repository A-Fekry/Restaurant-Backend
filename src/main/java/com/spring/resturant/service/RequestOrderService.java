package com.spring.resturant.service;


import com.spring.resturant.dto.RequestOrderDto;
import com.spring.resturant.dto.RequestResponseDto;

import java.util.List;

public interface RequestOrderService {
    String saveOrder(RequestOrderDto order);
    List<RequestResponseDto> getOrdersByCustomerId(Integer customerId);
    List<RequestResponseDto> getOrdersForAdmin();
    List<RequestOrderDto> getOrdersCode(String code);
    void checkCode(String code);

}
