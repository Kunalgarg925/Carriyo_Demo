package com.carriyo.carriyodemo.service.converters;

import com.carriyo.carriyodemo.adapter.model.*;
import com.carriyo.carriyodemo.controller.exception_handler.ValidationException;
import com.carriyo.carriyodemo.controller.model.request.shipment_model.Shipment;

import java.util.List;
import java.util.UUID;

public class RequestConvertToDTO {
    public static ShipmentDTO validateAndTranslateToShipmentDTO(Shipment shipment){
        // 1. validate
        validateRequest(shipment);

        // 2. convert to dto
        var shipmentId = shipment.getShipmentId();
        int totalAmount = shipment.getShipmentDetails().stream()
                .mapToInt(shipmentDetail -> Integer.parseInt(shipmentDetail.getAmount()))
                .sum();

        if(shipmentId == null){
            shipmentId = UUID.randomUUID().toString();
        }
        return new ShipmentDTO(shipmentId,shipment.getDestination(),shipment.getShipmentDetails(),shipment.getTrackingDetails(),totalAmount);
    }

    private static void validateRequest(Shipment shipment) {
        System.out.println();
        AddressDTO destination = shipment.getDestination();
        if (destination == null) {
            throw new ValidationException("Destination address cannot be null");
        }
        if (destination.getPostalCode() == null || !destination.getPostalCode().matches("\\d{6}")) {
            throw new ValidationException("Postal code must be a 6-digit number");
        }
        if (destination.getCity() == null || destination.getCity().isEmpty()) {
            throw new ValidationException("City cannot be null or empty");
        }
        if (destination.getState() == null || destination.getState().isEmpty()) {
            throw new ValidationException("State cannot be null or empty");
        }
        if (destination.getCountry() == null || destination.getCountry().isEmpty()) {
            throw new ValidationException("Country cannot be null or empty");
        }
        if (destination.getAddressLine1() == null || destination.getAddressLine1().length() < 4) {
            throw new ValidationException("Address line 1 must have a minimum length of 4");
        }
        if (destination.getAddressLine2() == null || destination.getAddressLine2().length() < 4) {
            throw new ValidationException("Address line 2 must have a minimum length of 4");
        }
        if (destination.getLandmark() == null || destination.getLandmark().length() < 4) {
            throw new ValidationException("Landmark must have a minimum length of 4");
        }

        List<ShipmentDetailsDTO> shipmentDetails = shipment.getShipmentDetails();
        if (shipmentDetails == null || shipmentDetails.isEmpty()) {
            throw new ValidationException("Shipment details cannot be empty");
        }
        for (ShipmentDetailsDTO detail : shipmentDetails) {
            if (detail.getItemId() == null || detail.getItemId().isEmpty()) {
                throw new ValidationException("Item ID cannot be null or empty");
            }
            if (detail.getAmount() == null || detail.getAmount().isEmpty()) {
                throw new ValidationException("Amount cannot be null or empty");
            }
        }

        TrackingDetails trackingDetails = shipment.getTrackingDetails();
        if (trackingDetails == null) {
            throw new ValidationException("Tracking details cannot be null");
        }
        if (trackingDetails.getTrackingNumber() == null || trackingDetails.getTrackingNumber().isEmpty()) {
            throw new ValidationException("Tracking number cannot be null or empty");
        }
        if (trackingDetails.getCourierName() == null || trackingDetails.getCourierName().isEmpty()) {
            throw new ValidationException("Courier name cannot be null or empty");
        }
    }
}
