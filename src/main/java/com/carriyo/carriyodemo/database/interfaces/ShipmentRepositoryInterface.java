package com.carriyo.carriyodemo.database.interfaces;

import com.carriyo.carriyodemo.database.model.Shipment;

public interface ShipmentRepositoryInterface {
    Shipment getShipmentDetail(String shipmentId);
    Shipment addShipmentDetail(Shipment newShipment);
    Shipment updateShipmentDetail(Shipment updateShipment);
    void deleteShipmentDetail(String shipmentId);
}
