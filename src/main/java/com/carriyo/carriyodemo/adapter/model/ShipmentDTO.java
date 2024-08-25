package com.carriyo.carriyodemo.adapter.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@DynamoDBTable(tableName = "shipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO {
    @DynamoDBHashKey(attributeName = "shipmentId")
    private String shipmentId;

    @DynamoDBAttribute(attributeName = "destination")
    private AddressDTO destination;

    @DynamoDBAttribute(attributeName = "shipmentDetails")
    private List<ShipmentDetailsDTO> shipmentDetails;

    @DynamoDBAttribute(attributeName = "trackingDetails")
    private TrackingDetails trackingDetails;

    @DynamoDBAttribute(attributeName = "totalAmount")
    private Integer totalAmount;

}
