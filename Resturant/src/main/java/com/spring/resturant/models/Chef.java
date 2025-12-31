package com.spring.resturant.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chef extends BaseEntity{

    @Column(name = "SPECIALTY")
    private String spec;

    @Column(name = "TWITTER_LINK")
    private String tweLink;

    @Column(name = "FACEBOOK_LINK")
    private String faceLink;

    @Column(name = "INSTAGRAM_LINK")
    private String instaLink;
}
