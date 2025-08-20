package com.spring.resturant.controller;

import com.spring.resturant.config.JwtTokenHandler;
import com.spring.resturant.controller.vm.CodeVm;
import com.spring.resturant.dto.ClientDto;
import com.spring.resturant.dto.RequestOrderDto;
import com.spring.resturant.dto.RequestResponseDto;
import com.spring.resturant.models.sec.Client;
import com.spring.resturant.service.RequestOrderService;
import com.spring.resturant.service.auth.ClientService;
import com.spring.resturant.sitting.JwtAuthenticationFilter;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
public class RequestOrderController {

    @Autowired
    private RequestOrderService requestOrderService;


    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    ResponseEntity<CodeVm> saveOrder(@RequestBody RequestOrderDto order) {
        return ResponseEntity.ok(new CodeVm(requestOrderService.saveOrder(order)));
    }

    @GetMapping("/get-by-id")
    ResponseEntity<List<RequestResponseDto>> getOrderById(@RequestHeader("Authorization") String token) {
        Client client = clientService.getClientByName(JwtTokenHandler.getUsername(token));
        return ResponseEntity.ok(requestOrderService.getOrdersByCustomerId(client.getId()));
    }
}
