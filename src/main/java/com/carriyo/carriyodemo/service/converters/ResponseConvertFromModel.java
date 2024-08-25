package com.carriyo.carriyodemo.service.converters;

import com.carriyo.carriyodemo.controller.model.response.ShipmentResponse;
import com.carriyo.carriyodemo.database.model.Shipment;

public class ResponseConvertFromModel {
    public static ShipmentResponse convertShipmentToShipmentResponse(Shipment shipment){
        return new ShipmentResponse(shipment.getShipmentId(), shipment.getOrderId(), shipment.getStatus().toString(), shipment.getDestination(), shipment.getShipmentDetails(), shipment.getTrackingDetails(), shipment.getTotalAmount(), shipment.getShipmentDate());
    }
}
