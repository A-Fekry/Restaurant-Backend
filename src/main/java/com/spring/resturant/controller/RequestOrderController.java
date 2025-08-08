package com.spring.resturant.controller;

import com.spring.resturant.controller.vm.CodeVm;
import com.spring.resturant.dto.RequestOrderDto;
import com.spring.resturant.service.RequestOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
public class RequestOrderController {

    @Autowired
    private RequestOrderService requestOrderService;


    @PostMapping("/save")
    ResponseEntity<CodeVm> saveOrder(@RequestBody RequestOrderDto order) {
        return ResponseEntity.ok(new CodeVm(requestOrderService.saveOrder(order)));
    }
}
