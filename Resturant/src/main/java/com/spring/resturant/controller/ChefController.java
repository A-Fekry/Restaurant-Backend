package com.spring.resturant.controller;

import com.spring.resturant.dto.ChefDto;
import com.spring.resturant.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chef")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @PostMapping("/save")
    ResponseEntity<ChefDto> saveChef(ChefDto chefDto) throws Exception {
        return ResponseEntity.ok(chefService.saveChef(chefDto));
    }

    @GetMapping("")
    ResponseEntity<List<ChefDto>> getAllChefs() throws Exception {
        return ResponseEntity.ok(chefService.findAllChef());
    }
}
