package com.spring.resturant.service.auth;

import com.spring.resturant.dto.ClientDto;
import com.spring.resturant.dto.NewDto;
import com.spring.resturant.dto.TokenDto;

public interface AuthService {

    TokenDto login(ClientDto clientDto) throws RuntimeException;

    TokenDto createAccount(NewDto newDto) throws RuntimeException;
}
