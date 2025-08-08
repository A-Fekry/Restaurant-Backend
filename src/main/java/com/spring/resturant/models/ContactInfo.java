package com.spring.resturant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.resturant.models.sec.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContactInfo {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;


    private String email;

    private String subject;

    @Size(max = 1000)
    private String message;

    @OneToOne
    @JoinColumn(name = "CLIENT_ID")
    @JsonIgnore
    private Client client;
}
