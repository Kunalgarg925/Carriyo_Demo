package com.carriyo.carriyodemo.adapter.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

@DynamoDBTypeConvertedEnum
public enum AddressTagDTO {
    Home, Business, Work
}
