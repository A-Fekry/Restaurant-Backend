package com.spring.resturant.controller;


import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.dto.ContactRequest;
import com.spring.resturant.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;


    @GetMapping("/get-all")
    public ResponseEntity<List<ContactInfoDto>> getAllContactInfo(){
        return ResponseEntity.ok(contactInfoService.getAllContactInfo());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveContactInfo(@RequestBody ContactInfoDto contactInfoDto,@RequestHeader("Authorization") String token){
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setMessage(contactInfoDto.getMessage());
        contactRequest.setSubject(contactInfoDto.getSubject());
        contactInfoService.saveContactInfo(contactRequest, token);
        return null;
    }
}
