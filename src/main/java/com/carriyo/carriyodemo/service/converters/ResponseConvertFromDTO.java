package com.carriyo.carriyodemo.service.converters;

import com.carriyo.carriyodemo.adapter.model.ShipmentDTO;
import com.carriyo.carriyodemo.controller.model.response.shipment_model.Shipment;

public class ResponseConvertFromDTO {
    public static Shipment convertShipmentDTOToShipment(ShipmentDTO shipmentDTO){
        return new Shipment(shipmentDTO.getShipmentId(),shipmentDTO.getDestination(),shipmentDTO.getShipmentDetails(),shipmentDTO.getTrackingDetails(), shipmentDTO.getTotalAmount());
    }
}
