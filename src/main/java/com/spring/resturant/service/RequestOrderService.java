package com.spring.resturant.service;


import com.spring.resturant.dto.RequestOrderDto;

import java.util.List;

public interface RequestOrderService {
    String saveOrder(RequestOrderDto order);
    List<RequestOrderDto> getOrdersByCustomerId(Integer customerId);
    List<RequestOrderDto> getOrdersCode(String code);
    void checkCode(String code);

}
