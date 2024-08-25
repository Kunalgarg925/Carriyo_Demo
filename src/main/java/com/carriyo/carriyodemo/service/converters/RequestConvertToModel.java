package com.carriyo.carriyodemo.service.converters;

import com.carriyo.carriyodemo.controller.exception.handler.ValidationException;
import com.carriyo.carriyodemo.controller.model.request.ShipmentRequest;
import com.carriyo.carriyodemo.database.model.*;

import java.util.List;
import java.util.UUID;

public class RequestConvertToModel {
    public static Shipment validateAndTranslateToShipment(ShipmentRequest shipmentRequest){
        // 1. validate
        validateRequest(shipmentRequest);

        // 2. convert to model
        var shipmentId = shipmentRequest.getShipmentId();
        int totalAmount = shipmentRequest.getShipmentDetails().stream()
                .mapToInt(shipmentDetail -> Integer.parseInt(shipmentDetail.getAmount()))
                .sum();
        if(shipmentId == null){
            shipmentId = UUID.randomUUID().toString();
        }

        return new Shipment(shipmentId,shipmentRequest.getOrderId(), ShipmentStatus.valueOf(shipmentRequest.getStatus()),shipmentRequest.getAddress(),shipmentRequest.getShipmentDetails(),shipmentRequest.getTrackingDetails(),totalAmount,shipmentRequest.getShipmentDate());
    }

    private static void validateRequest(ShipmentRequest shipmentRequest) {

        if(shipmentRequest.getOrderId() == null || shipmentRequest.getOrderId().trim().isEmpty()){
            throw new ValidationException("Order is cannot be null or empty");
        }
        if(shipmentRequest.getStatus() == null || shipmentRequest.getStatus().trim().isEmpty()){
            throw new ValidationException("Shipment status is cannot be null or empty");
        }

        Address destination = shipmentRequest.getAddress();
        if (destination == null) {
            throw new ValidationException("Destination address cannot be null");
        }
        if (destination.getPostalCode() == null || !destination.getPostalCode().matches("\\d{6}")) {
            throw new ValidationException("Postal code must be a 6-digit number");
        }
        if (destination.getCity() == null || destination.getCity().trim().isEmpty()) {
            throw new ValidationException("City cannot be null or empty");
        }
        if (destination.getState() == null || destination.getState().trim().isEmpty()) {
            throw new ValidationException("State cannot be null or empty");
        }
        if (destination.getCountry() == null || destination.getCountry().trim().isEmpty()) {
            throw new ValidationException("Country cannot be null or empty");
        }
        if (destination.getStreet() == null || destination.getStreet().trim().length() < 4) {
            throw new ValidationException("street must have a minimum length of 8");
        }

        List<ShipmentDetails> shipmentDetails = shipmentRequest.getShipmentDetails();
        if (shipmentDetails == null || shipmentDetails.isEmpty()) {
            throw new ValidationException("Shipment details cannot be empty");
        }
        for (ShipmentDetails detail : shipmentDetails) {
            if (detail.getItemId() == null || detail.getItemId().isEmpty()) {
                throw new ValidationException("Item ID cannot be null or empty");
            }
            if (detail.getAmount() == null || detail.getAmount().isEmpty()) {
                throw new ValidationException("Amount cannot be null or empty");
            }
        }

        TrackingDetails trackingDetails = shipmentRequest.getTrackingDetails();
        if (trackingDetails == null) {
            throw new ValidationException("Tracking details cannot be null");
        }
        if (trackingDetails.getTrackingNumber() == null || trackingDetails.getTrackingNumber().isEmpty()) {
            throw new ValidationException("Tracking number cannot be null or empty");
        }
        if (trackingDetails.getCourierName() == null || trackingDetails.getCourierName().isEmpty()) {
            throw new ValidationException("Courier name cannot be null or empty");
        }
        if(trackingDetails.getEstimatedDeliveryDate() == null){
            throw new ValidationException("Estimated delivery date is mandatory");
        }
    }
}
