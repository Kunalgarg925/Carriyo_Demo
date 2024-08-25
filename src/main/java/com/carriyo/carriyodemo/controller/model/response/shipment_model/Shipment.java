package com.carriyo.carriyodemo.controller.model.response.shipment_model;

import com.carriyo.carriyodemo.adapter.model.AddressDTO;
import com.carriyo.carriyodemo.adapter.model.ShipmentDetailsDTO;
import com.carriyo.carriyodemo.adapter.model.TrackingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    private String shipmentId;
    private AddressDTO destination;
    private List<ShipmentDetailsDTO> shipmentDetails;
    private TrackingDetails trackingDetails;
    private Integer totalAmount;
}
