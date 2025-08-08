package com.spring.resturant.models.sec;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "ROLE_SEQ", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    private List<Client> clients;
}
