package com.edu.cs.go.bet.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Address {

    private String id;
    private String country;
    private String city;
    private String zipCode;
    private String streetName;
    private int buildingNumber;
}

