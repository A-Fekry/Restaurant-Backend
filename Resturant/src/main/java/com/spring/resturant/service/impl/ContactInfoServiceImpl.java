package com.spring.resturant.service.impl;

import com.spring.resturant.config.JwtTokenHandler;
import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.dto.ContactRequest;
import com.spring.resturant.mapper.ContactInfoMapper;
import com.spring.resturant.models.ContactInfo;
import com.spring.resturant.models.sec.Client;
import com.spring.resturant.repo.ContactInfoRepo;
import com.spring.resturant.service.ContactInfoService;
import com.spring.resturant.service.auth.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    @Autowired
    private ContactInfoRepo contactInfoRepo;

    @Autowired
    private ClientService clientService;

    @Override
    public void saveContactInfo(ContactRequest contact,String token) {
        Client client = clientService.getClientByName(JwtTokenHandler.getUsername(token));
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setClient(client);
        contactInfo.setEmail(client.getEmail());
        contactInfo.setName(client.getName());
        contactInfo.setSubject(contact.getSubject());
        if (contact.getMessage().length() > 1000){
            throw new RuntimeException("your.message.is.very.long");
        }
        contactInfo.setMessage(contact.getMessage());

        contactInfoRepo.save(contactInfo);
    }

    @Override
    public List<ContactInfoDto> getAllContactInfo() {
        return ContactInfoMapper.INSTANCE.toContactInfoDtoList(contactInfoRepo.findAll());
    }
}
