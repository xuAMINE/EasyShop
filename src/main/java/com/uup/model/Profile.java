package com.uup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

