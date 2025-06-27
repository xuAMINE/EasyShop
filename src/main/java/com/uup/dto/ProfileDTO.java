package com.uup.dto;

import com.uup.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;

    public static ProfileDTO fromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhone(),
                profile.getEmail(),
                profile.getAddress(),
                profile.getCity(),
                profile.getState(),
                profile.getZip()
        );
    }

    public void updateEntity(Profile profile) {
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhone(phone);
        profile.setEmail(email);
        profile.setAddress(address);
        profile.setCity(city);
        profile.setState(state);
        profile.setZip(zip);
    }
}
