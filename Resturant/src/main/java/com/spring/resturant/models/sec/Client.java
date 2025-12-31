package com.spring.resturant.models.sec;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.resturant.models.ContactInfo;
import com.spring.resturant.models.RequestOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @SequenceGenerator(name = "client_seq", sequenceName = "CLIENT_SEQ", allocationSize = 1)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;


    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany
    Set<RequestOrder> requestOrders = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany
    @JsonIgnore
    private List<ContactInfo> contactInfos;
}
