package com.carriyo.carriyodemo.controller.model.request.shipment_model;

import com.carriyo.carriyodemo.adapter.model.AddressDTO;
import com.carriyo.carriyodemo.adapter.model.ShipmentDetailsDTO;
import com.carriyo.carriyodemo.adapter.model.TrackingDetails;
import lombok.Data;

import java.util.List;

@Data
public class Shipment {
    private String shipmentId;
    private AddressDTO destination;
    private List<ShipmentDetailsDTO> shipmentDetails;
    private TrackingDetails trackingDetails;
    private Integer totalAmount;
}
