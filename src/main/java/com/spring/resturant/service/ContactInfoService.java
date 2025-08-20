package com.spring.resturant.service;


import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.dto.ContactRequest;

import java.util.List;

public interface ContactInfoService {

    void saveContactInfo(ContactRequest contact,String token);
    List<ContactInfoDto> getAllContactInfo();
}
