package com.carriyo.carriyodemo.service;

import com.carriyo.carriyodemo.controller.model.request.ShipmentRequest;
import com.carriyo.carriyodemo.controller.model.response.ShipmentResponse;
import com.carriyo.carriyodemo.database.model.Shipment;
import com.carriyo.carriyodemo.database.repository.ShipmentRepository;
import com.carriyo.carriyodemo.service.interfaces.ShipmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.carriyo.carriyodemo.service.converters.RequestConvertToModel.validateAndTranslateToShipment;
import static com.carriyo.carriyodemo.service.converters.ResponseConvertFromModel.convertShipmentToShipmentResponse;


@Service
public class ShipmentService implements ShipmentServiceInterface {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @Override
    public ShipmentResponse getShipment(String shipmentId) {
        // 1. validate
        if(shipmentId == null) throw new NullPointerException("Shipment id cannot be a null or empty");

        // 2. get the shipment detail
        Shipment shipment = shipmentRepository.getShipmentDetail(shipmentId);

        return convertShipmentToShipmentResponse(shipment);
    }

    @Override
    public ShipmentResponse addShipment(ShipmentRequest shipmentRequest) {
        // 1. Validate and Transform to normal model
        var newShipment = validateAndTranslateToShipment(shipmentRequest);

        // 2.  Save in Dynamo DB
        Shipment shipmentAddedInDatabase = shipmentRepository.addShipmentDetail(newShipment);

        return convertShipmentToShipmentResponse(shipmentAddedInDatabase);
    }

    @Override
    public ShipmentResponse updateShipment(ShipmentRequest shipmentRequest) {
        // 1. Validate and Transform to normal model
        var updateShipment = validateAndTranslateToShipment(shipmentRequest);

        // 2.  Update in Dynamo DB
        Shipment shipmentUpdateInDatabase = shipmentRepository.updateShipmentDetail(updateShipment);

        return convertShipmentToShipmentResponse(shipmentUpdateInDatabase);
    }
    @Override
    public void deleteShipment(String shipmentId) {
        // 1. validate
        if(shipmentId == null) throw new NullPointerException("shipment id cannot be a null or empty");

        // 2. Delete from dynamo db database
        shipmentRepository.deleteShipmentDetail(shipmentId);
    }
}
