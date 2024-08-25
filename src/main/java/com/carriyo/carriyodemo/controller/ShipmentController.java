package com.carriyo.carriyodemo.controller;

import com.carriyo.carriyodemo.controller.model.request.Request;
import com.carriyo.carriyodemo.controller.model.response.Response;
import com.carriyo.carriyodemo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/shipment")
public class ShipmentController {
    @Autowired private ShipmentService shipmentService;

    @GetMapping("/{shipmentId}")
    public Response getShipment(@PathVariable String shipmentId) {
        System.out.println("shipment id ---> " + shipmentId);
        var shipmentResponse = shipmentService.getShipment(shipmentId);
        return new Response(shipmentResponse);
    }
    @PostMapping()
    public Response addShipment(@RequestBody Request addShipmentRequest){
        System.out.println("request -> " + addShipmentRequest);
        var shipmentResponse = shipmentService.addShipment(addShipmentRequest.getData());
        return new Response(shipmentResponse);
    }
    @PutMapping
    public Response updateShipment(@RequestBody Request updateShipmentRequest){
        System.out.println("request -> " + updateShipmentRequest);
        var shipmentResponse = shipmentService.updateShipment(updateShipmentRequest.getData());
        return new Response(shipmentResponse);
    }
    @DeleteMapping("/{shipmentId}")
    public String deleteShipment(@PathVariable String shipmentId) {
        shipmentService.deleteShipment(shipmentId);
        return "Shipment Deleted Successfully";
    }
}
