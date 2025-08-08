package com.spring.resturant.service.auth;

import com.spring.resturant.controller.vm.CodeVm;
import com.spring.resturant.models.sec.Client;

public interface ClientService {

    Client getClientByName(String clientName);
    Client getClientByEmail(String email);
    Client saveClient(Client client);
    CodeVm makeAdmin(String email) throws RuntimeException;
}
