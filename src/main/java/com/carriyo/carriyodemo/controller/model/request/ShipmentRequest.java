package com.carriyo.carriyodemo.controller.model.request;

import com.carriyo.carriyodemo.database.model.Address;
import com.carriyo.carriyodemo.database.model.ShipmentDetails;
import com.carriyo.carriyodemo.database.model.TrackingDetails;
import lombok.Data;

import java.util.List;

@Data
public class ShipmentRequest {
    private String shipmentId;
    private String orderId;
    private String status;
    private Address address;
    private List<ShipmentDetails> shipmentDetails;
    private TrackingDetails trackingDetails;
    private Integer totalAmount;
    private String shipmentDate;
}
