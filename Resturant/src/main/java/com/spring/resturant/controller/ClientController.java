package com.spring.resturant.controller;


import com.spring.resturant.controller.vm.CodeVm;
import com.spring.resturant.service.auth.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/add-admin")
    ResponseEntity<CodeVm> addAdmin(@RequestBody String email) {
        return ResponseEntity.ok(clientService.makeAdmin(email));
    }
}
