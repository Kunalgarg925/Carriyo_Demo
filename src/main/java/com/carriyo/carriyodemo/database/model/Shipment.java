package com.carriyo.carriyodemo.database.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@DynamoDBTable(tableName = "Shipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @DynamoDBHashKey(attributeName = "shipmentId")
    private String shipmentId;

    @DynamoDBAttribute(attributeName = "orderId")
    private String orderId;

    @DynamoDBAttribute(attributeName = "status")
    private ShipmentStatus status;

    @DynamoDBAttribute(attributeName = "destination")
    private Address destination;

    @DynamoDBAttribute(attributeName = "shipmentDetails")
    private List<ShipmentDetails> shipmentDetails;

    @DynamoDBAttribute(attributeName = "trackingDetails")
    private TrackingDetails trackingDetails;

    @DynamoDBAttribute(attributeName = "totalAmount")
    private Integer totalAmount;

    @DynamoDBAttribute(attributeName = "shipmentDate")
    private String shipmentDate;
}
