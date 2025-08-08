package com.spring.resturant.service.auth;

import com.spring.resturant.controller.vm.CodeVm;
import com.spring.resturant.models.sec.Client;
import com.spring.resturant.models.sec.Role;
import com.spring.resturant.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public Client getClientByName(String clientName) {
        return clientRepo.findByName(clientName);
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepo.findByEmail(email);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public CodeVm makeAdmin(String email) throws RuntimeException {
        Client client = getClientByEmail(email);
        if (client == null) {
            throw new RuntimeException("Client with email " + email + " not found");
        }
        Role role = new Role();
        role.setCode("ROLE_ADMIN");
        if (client.getRoles().size() > 1) {
            throw new RuntimeException("Client with email " + email + " already admin");
        }
        client.getRoles().add(role);
        clientRepo.save(client);
        CodeVm codeVm = new CodeVm();
        codeVm.setCode("Done successfully");
        return codeVm;
    }
}
