package com.spring.resturant.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.resturant.models.sec.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class RequestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String code;

    private Double totalPrice;

    private Integer quantity;

    @ManyToMany
    private List<Product> products;

    @JsonIgnore
    @ManyToOne
    private Client client;
}
