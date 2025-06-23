package com.uup.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    private Integer userId;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}

