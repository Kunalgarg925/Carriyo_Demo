package com.carriyo.carriyodemo.controller.model.response;

import com.carriyo.carriyodemo.database.model.Address;
import com.carriyo.carriyodemo.database.model.ShipmentDetails;
import com.carriyo.carriyodemo.database.model.TrackingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {
    private String shipmentId;
    private String orderId;
    private String status;
    private Address address;
    private List<ShipmentDetails> shipmentDetails;
    private TrackingDetails trackingDetails;
    private Integer totalAmount;
    private String shipmentDate;
}
