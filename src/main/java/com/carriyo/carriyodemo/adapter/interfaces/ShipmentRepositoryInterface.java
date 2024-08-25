package com.carriyo.carriyodemo.adapter.interfaces;

import com.carriyo.carriyodemo.adapter.model.ShipmentDTO;

public interface ShipmentRepositoryInterface {
    ShipmentDTO getShipmentDetail(String shipmentId);
    ShipmentDTO addShipmentDetail(ShipmentDTO newShipment) throws Exception;
    ShipmentDTO updateShipmentDetail(ShipmentDTO updateShipment);
    void deleteShipmentDetail(String shipmentId);
}
