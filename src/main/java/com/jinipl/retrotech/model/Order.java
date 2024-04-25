package com.jinipl.retrotech.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;


@Data
@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @MongoId
    private String id;
    private List<Product> products;
    private double totalPrice;
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String email;
    //private String discount;
    //private LocalDateTime orderDate;
}
