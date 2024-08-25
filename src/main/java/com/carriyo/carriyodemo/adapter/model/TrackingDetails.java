package com.carriyo.carriyodemo.adapter.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

@Data
@DynamoDBDocument
public class TrackingDetails {
    @DynamoDBAttribute(attributeName = "trackingNumber")
    private String trackingNumber;
    @DynamoDBAttribute(attributeName = "courierName")
    private String courierName;
}
