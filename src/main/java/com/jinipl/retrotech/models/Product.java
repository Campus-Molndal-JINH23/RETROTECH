package com.jinipl.retrotech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @MongoId
    private String id;
    private String name;
    private double price;
    private String category;
    private String description;
    private String imageUrl;

}
