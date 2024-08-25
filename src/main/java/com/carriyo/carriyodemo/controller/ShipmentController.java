package com.carriyo.carriyodemo.controller;

import com.carriyo.carriyodemo.controller.model.request.ShipmentRequest;
import com.carriyo.carriyodemo.controller.model.response.ResponseBody;
import com.carriyo.carriyodemo.controller.model.response.ShipmentResponse;
import com.carriyo.carriyodemo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/shipment")
public class ShipmentController {
    @Autowired private ShipmentService shipmentService;

    @GetMapping("/{shipmentId}")
    public ResponseBody getShipment(@PathVariable String shipmentId) {
        System.out.println("shipment id ---> " + shipmentId);
        var shipmentResponse = shipmentService.getShipment(shipmentId);
        return new ResponseBody(shipmentResponse);
    }
    @PostMapping()
    public ResponseBody addShipment(@RequestBody ShipmentRequest addShipmentRequest){
        System.out.println("request -> " + addShipmentRequest);
        var shipmentResponse = shipmentService.addShipment(addShipmentRequest);
        return new ResponseBody(shipmentResponse);
    }
    @PutMapping
    public ResponseBody updateShipment(@RequestBody ShipmentRequest updateShipmentRequest){
        System.out.println("request -> " + updateShipmentRequest);
        var shipmentResponse = shipmentService.updateShipment(updateShipmentRequest);
        return new ResponseBody(shipmentResponse);
    }
    @DeleteMapping("/{shipmentId}")
    public String deleteShipment(@PathVariable String shipmentId) {
        shipmentService.deleteShipment(shipmentId);
        return "Shipment Deleted Successfully";
    }
}
