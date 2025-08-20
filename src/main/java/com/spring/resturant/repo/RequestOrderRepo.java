package com.spring.resturant.repo;

import com.spring.resturant.models.RequestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestOrderRepo extends JpaRepository<RequestOrder, Integer> {
    List<RequestOrder> getRequestOrdersByCode(String code);

    List<RequestOrder> getRequestOrdersByClientId(Integer clientId);

}
