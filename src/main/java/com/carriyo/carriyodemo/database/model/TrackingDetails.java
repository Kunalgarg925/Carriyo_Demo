package com.carriyo.carriyodemo.database.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

import java.util.List;

@Data
@DynamoDBDocument
public class TrackingDetails {
    @DynamoDBAttribute(attributeName = "trackingNumber")
    private String trackingNumber;
    @DynamoDBAttribute(attributeName = "courierName")
    private String courierName;
    @DynamoDBAttribute(attributeName = "estimatedDeliveryDate")
    private String estimatedDeliveryDate;
    @DynamoDBAttribute(attributeName = "deliveredDate")
    private String deliveredDate;
    @DynamoDBAttribute(attributeName = "trackingEvents")
    private List<TrackingHistory> trackingHistory;
}
