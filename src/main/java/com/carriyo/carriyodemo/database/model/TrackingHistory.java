package com.carriyo.carriyodemo.database.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingHistory {
    @DynamoDBAttribute(attributeName = "historyDate")
    private String historyDate;

    @DynamoDBAttribute(attributeName = "location")
    private String location;

    @DynamoDBAttribute(attributeName = "eventDescription")
    private String eventDescription;
}
