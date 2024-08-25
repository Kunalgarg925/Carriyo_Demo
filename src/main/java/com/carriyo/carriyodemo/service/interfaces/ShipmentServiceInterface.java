package com.carriyo.carriyodemo.service.interfaces;

import com.carriyo.carriyodemo.controller.model.response.shipment_model.Shipment;

public interface ShipmentServiceInterface {
    Shipment getShipment(String shipmentId);
    Shipment addShipment(com.carriyo.carriyodemo.controller.model.request.shipment_model.Shipment newShipment);
    Shipment updateShipment(com.carriyo.carriyodemo.controller.model.request.shipment_model.Shipment updateShipment);
    void deleteShipment(String shipmentId);
}
