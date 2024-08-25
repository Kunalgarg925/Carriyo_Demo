package com.carriyo.carriyodemo.database.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

@Data
@DynamoDBDocument
public class ShipmentDetails {
    @DynamoDBAttribute(attributeName = "itemId")
    private String itemId;
    @DynamoDBAttribute(attributeName = "weight")
    private String weight;
    @DynamoDBAttribute(attributeName = "dimension")
    private String dimension;
    @DynamoDBAttribute(attributeName = "amount")
    private String amount;
}
