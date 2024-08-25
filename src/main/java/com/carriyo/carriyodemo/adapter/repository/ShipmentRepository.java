package com.carriyo.carriyodemo.adapter.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.kms.model.NotFoundException;
import com.carriyo.carriyodemo.adapter.interfaces.ShipmentRepositoryInterface;
import com.carriyo.carriyodemo.adapter.model.ShipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ShipmentRepository implements ShipmentRepositoryInterface {
    private final DynamoDBMapper dynamoDBMapper;
    @Autowired
    public ShipmentRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public ShipmentDTO getShipmentDetail(String shipmentId) {
        try{
            return dynamoDBMapper.load(ShipmentDTO.class, shipmentId);
        }catch (Exception exception){
            throw new NotFoundException("No shipment is found in the database");
        }
    }

    @Override
    public ShipmentDTO addShipmentDetail(ShipmentDTO newShipment){
        try{
            dynamoDBMapper.save(newShipment);
            return newShipment;
        }catch (Exception exception){
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    @Override
    public ShipmentDTO updateShipmentDetail(ShipmentDTO updateShipment) {
        try{
            ShipmentDTO shipment = dynamoDBMapper.load(ShipmentDTO.class,updateShipment.getShipmentId());
            if(shipment != null){
                dynamoDBMapper.save(updateShipment);
                return updateShipment;
            }else{
                throw new NotFoundException("No shipment is found in the database");
            }
        }catch (NotFoundException exception){
            throw new RuntimeException(exception);
        }catch (Exception exception){
            throw new InternalServerErrorException("Internal Server Error");
        }
    }


    @Override
    public void deleteShipmentDetail(String shipmentId) {
        try{
            ShipmentDTO shipment = dynamoDBMapper.load(ShipmentDTO.class,shipmentId);
            if(shipment != null){
                dynamoDBMapper.delete(shipment);
            }else{
                throw new NotFoundException("No shipment is found in the database");
            }
        } catch (NotFoundException expection) {
            throw new RuntimeException(expection);
        }catch (Exception exception){
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}
