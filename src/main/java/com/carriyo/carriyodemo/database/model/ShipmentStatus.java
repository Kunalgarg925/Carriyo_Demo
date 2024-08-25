package com.carriyo.carriyodemo.database.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

@DynamoDBTypeConvertedEnum
public enum ShipmentStatus {
    Preparing, Pending, Completed, Failed
}
