package com.carriyo.carriyodemo.database.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Address {

    @DynamoDBAttribute(attributeName = "street")
    private String street;

    @DynamoDBAttribute(attributeName = "city")
    private String city;

    @DynamoDBAttribute(attributeName = "postalCode")
    private String postalCode;

    @DynamoDBAttribute(attributeName = "state")
    private String state;

    @DynamoDBAttribute(attributeName = "country")
    private String country;
}
