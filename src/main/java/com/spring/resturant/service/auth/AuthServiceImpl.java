package com.spring.resturant.service.auth;


import com.spring.resturant.config.JwtTokenHandler;
import com.spring.resturant.dto.ClientDto;
import com.spring.resturant.dto.NewDto;
import com.spring.resturant.dto.TokenDto;
import com.spring.resturant.models.sec.Client;
import com.spring.resturant.models.sec.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private JwtTokenHandler jwtTokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public TokenDto login(ClientDto clientDto) throws RuntimeException {
        Client client = clientService.getClientByName(clientDto.getUserName());
        if (client == null) {
            throw new RuntimeException("username.invalid");
        }

        if(!passwordEncoder.matches(clientDto.getPassword(), client.getPassword())) {
            throw new RuntimeException("password.wrong");
        }
        List<Role> roles = client.getRoles();
        List<String> codes = new ArrayList<>();
        for (Role role : roles) {
            codes.add(role.getCode());
        }
        return new TokenDto(jwtTokenHandler.createToken(client),codes);
    }

    @Override
    public TokenDto createAccount(NewDto newDto) throws RuntimeException {
        Client client1 = clientService.getClientByName(newDto.getUserName());
        if (client1 != null) {
            throw new RuntimeException("used.username");
        }
        Client client2 = clientService.getClientByEmail(newDto.getEmail());
        if(client2 != null) {
            throw new RuntimeException("email.used");
        }
        String encodedPassword = passwordEncoder.encode(newDto.getPassword());
        Client client = new Client();
        client.setName(newDto.getUserName());
        client.setPassword(encodedPassword);
        client.setEmail(newDto.getEmail());
        client.setPhoneNumber(newDto.getPhoneNumber());
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setCode("ROLE_USER");
        roles.add(role1);
        client.setRoles(roles);
        clientService.saveClient(client);
        List<String> code = new ArrayList<>();
        code.add(role1.getCode());
        return new TokenDto(jwtTokenHandler.createToken(client),code);
    }


}
