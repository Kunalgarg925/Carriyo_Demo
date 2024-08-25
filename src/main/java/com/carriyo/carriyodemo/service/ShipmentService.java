package com.carriyo.carriyodemo.service;

import com.carriyo.carriyodemo.adapter.model.ShipmentDTO;
import com.carriyo.carriyodemo.adapter.repository.ShipmentRepository;
import com.carriyo.carriyodemo.controller.model.response.shipment_model.Shipment;
import com.carriyo.carriyodemo.service.interfaces.ShipmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.carriyo.carriyodemo.service.converters.RequestConvertToDTO.validateAndTranslateToShipmentDTO;
import static com.carriyo.carriyodemo.service.converters.ResponseConvertFromDTO.convertShipmentDTOToShipment;


@Service
public class ShipmentService implements ShipmentServiceInterface {

    @Autowired
    private ShipmentRepository shipmentRepository;
    @Override
    public Shipment getShipment(String shipmentId) {
        System.out.println("shipmentId ---> " + shipmentId);
        // 1. validate
        if(shipmentId == null) throw new NullPointerException("shipment id cannot be a null or empty");

        // 2. get the shipment detail
        ShipmentDTO shipmentDTO = shipmentRepository.getShipmentDetail(shipmentId);
        return convertShipmentDTOToShipment(shipmentDTO);
    }

    @Override
    public Shipment addShipment(com.carriyo.carriyodemo.controller.model.request.shipment_model.Shipment newShipment) {
        // 1. Validate and Transform Data
        var userDto = validateAndTranslateToShipmentDTO(newShipment);

        // 2.  Save in Dynamo DB
        ShipmentDTO shipmentAddedInDatabase = shipmentRepository.addShipmentDetail(userDto);

        return convertShipmentDTOToShipment(shipmentAddedInDatabase);
    }

    @Override
    public Shipment updateShipment(com.carriyo.carriyodemo.controller.model.request.shipment_model.Shipment updateShipment) {
        // 1. Validate and Transform Data
        var userDto = validateAndTranslateToShipmentDTO(updateShipment);

        // 2.  Update in Dynamo DB
        ShipmentDTO shipmentUpdateInDatabase = shipmentRepository.updateShipmentDetail(userDto);

        return convertShipmentDTOToShipment(shipmentUpdateInDatabase);
    }
    @Override
    public void deleteShipment(String shipmentId) {
        // 1. validate
        if(shipmentId == null) throw new NullPointerException("shipment id cannot be a null or empty");

        // 2. Delete from dynamo db database
        shipmentRepository.deleteShipmentDetail(shipmentId);
    }
}
