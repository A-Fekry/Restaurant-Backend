package com.spring.resturant.repo;

import com.spring.resturant.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Page<Product> getProductByCategoryId(Integer categoryId, org.springframework.data.domain.Pageable pageable);

    @Query(
            value = "SELECT * FROM PRODUCT WHERE LOWER(name) LIKE '%' || LOWER(:val) || '%' OR LOWER(description) LIKE '%' || LOWER(:val) || '%'",
            countQuery = "SELECT COUNT(*) FROM PRODUCT WHERE LOWER(name) LIKE '%' || LOWER(:val) || '%' OR LOWER(description) LIKE '%' || LOWER(:val) || '%'",
            nativeQuery = true
    )
    Page<Product> getProductByLetters(@Param("val") String val, Pageable pageable);

    Product findByName(String name);

    void removeProductById(Integer id);
}
