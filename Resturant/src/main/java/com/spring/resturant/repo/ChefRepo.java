package com.spring.resturant.repo;

import com.spring.resturant.models.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef,Integer> {
}
