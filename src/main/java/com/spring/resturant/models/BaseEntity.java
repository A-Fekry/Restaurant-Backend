package com.spring.resturant.models;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Column(nullable = false, unique = true)
    private String name;

    private String logoPath;


}
