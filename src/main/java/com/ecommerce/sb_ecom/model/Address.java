package com.ecommerce.sb_ecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be atleast 5 character")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be atleast 5 character")
    private String buildingName;

    @NotBlank
    @Size(min = 4, message = "Street name must be atleast 4 character")
    private String city;

    @NotBlank
    @Size(min = 4, message = "State name must be atleast 4 character")
    private String State;

    @NotBlank
    @Size(min = 2, message = "country name must be atleast 2 character")
    private String country;

    @NotBlank
    @Size(min = 6, message = "pincode name must be atleast 2 character")
    private String pincode;


//    @ToString.Exclude
//    @ManyToMany(mappedBy = "addresses")
//    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String street, String buildingName, String city, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }


}
