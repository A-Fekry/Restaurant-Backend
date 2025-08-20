package com.spring.resturant.service.impl;


import com.spring.resturant.code.Generator;
import com.spring.resturant.dto.RequestOrderDto;
import com.spring.resturant.dto.RequestResponseDto;
import com.spring.resturant.mapper.ProductMapper;
import com.spring.resturant.mapper.RequestOrderMapper;
import com.spring.resturant.mapper.RequestResponseMapper;
import com.spring.resturant.models.Product;
import com.spring.resturant.models.RequestOrder;
import com.spring.resturant.models.sec.Client;
import com.spring.resturant.repo.RequestOrderRepo;
import com.spring.resturant.service.ProductService;
import com.spring.resturant.service.RequestOrderService;
import com.spring.resturant.service.auth.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestOrderServiceImpl implements RequestOrderService {
    @Autowired
    private RequestOrderRepo requestOrderRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Override
    public String saveOrder(RequestOrderDto order) {
        List<Product> products = ProductMapper.INSTANCE.mapProductDtoListToProductList(productService.getProductsByIds(order.getProductIds()));

        double totalPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            totalPrice += products.get(i).getPrice() * order.getProductsQuantity().get(i);
        }
        order.setTotalPrice(totalPrice);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = (Client) clientService.getClientByName((String) auth.getPrincipal());

        RequestOrder newOrder = RequestOrderMapper.INSTANCE.RequestOrderDtoToRequestOrder(order);
        newOrder.setClient(client);
        newOrder.setProducts(products);

        String code = Generator.generate();
        checkCode(code);
        newOrder.setCode(code);
        requestOrderRepo.save(newOrder);
        return code;
    }

    @Override
    public List<RequestResponseDto> getOrdersByCustomerId(Integer customerId) {
        return RequestResponseMapper.INSTANCE.requestResponseDtoListToRequestResponseDtoList(requestOrderRepo.getRequestOrdersByClientId(customerId));
    }

    @Override
    public List<RequestOrderDto> getOrdersCode(String code) {
        return RequestOrderMapper.INSTANCE.RequestOrderListToRequestOrderDtoList(requestOrderRepo.getRequestOrdersByCode(code));
    }

    @Override
    public void checkCode(String code) {
        List<RequestOrderDto> ors = getOrdersCode(code);
        if (ors.size() > 0) {
            code = Generator.generate();
            checkCode(code);
        }
    }
}
