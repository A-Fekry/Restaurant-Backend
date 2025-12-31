package com.spring.resturant.controller;

import com.spring.resturant.dto.ClientDto;
import com.spring.resturant.dto.NewDto;
import com.spring.resturant.dto.TokenDto;
import com.spring.resturant.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody ClientDto clientDto) throws RuntimeException {
        return ResponseEntity.ok(authService.login(clientDto));
    }

    @PostMapping("/create-account")
    ResponseEntity<TokenDto> createAccount(@RequestBody NewDto newDto) throws RuntimeException {
        return ResponseEntity.ok(authService.createAccount(newDto));
    }
}
