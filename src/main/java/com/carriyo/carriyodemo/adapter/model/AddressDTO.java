package com.carriyo.carriyodemo.adapter.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class AddressDTO {
    @DynamoDBAttribute(attributeName = "addressLine1")
    private String addressLine1;
    @DynamoDBAttribute(attributeName = "addressLine2")
    private String addressLine2;
    @DynamoDBAttribute(attributeName = "addressLine3")
    private String addressLine3;
    @DynamoDBAttribute(attributeName = "landmark")
    private String landmark;
    @DynamoDBAttribute(attributeName = "postalCode")
    private String postalCode;
    @DynamoDBAttribute(attributeName = "city")
    private String city;
    @DynamoDBAttribute(attributeName = "state")
    private String state;
    @DynamoDBAttribute(attributeName = "country")
    private String country;
    @DynamoDBAttribute(attributeName = "addressTags")
    private List<AddressTagDTO> addressTag;
}
