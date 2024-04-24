package com.jinipl.retrotech.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    private List<Product> products;

    private double totalPrice;

    private String name;

    private String address;

    private String postCode;

    private String city;

    private String email;

    private LocalDateTime orderTime;

}
