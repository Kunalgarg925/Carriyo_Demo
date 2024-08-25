package com.carriyo.carriyodemo.service.interfaces;

import com.carriyo.carriyodemo.controller.model.request.ShipmentRequest;
import com.carriyo.carriyodemo.controller.model.response.ShipmentResponse;

public interface ShipmentServiceInterface {
    ShipmentResponse getShipment(String shipmentId);
    ShipmentResponse addShipment(ShipmentRequest shipmentRequest);
    ShipmentResponse updateShipment(ShipmentRequest shipmentRequest);
    void deleteShipment(String shipmentId);
}
