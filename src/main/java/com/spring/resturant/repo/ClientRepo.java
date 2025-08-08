package com.spring.resturant.repo;

import com.spring.resturant.models.sec.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

    Client findByName(String name);
    Client findByEmail(String email);
}
