package com.carriyo.carriyodemo.database.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.kms.model.NotFoundException;
import com.carriyo.carriyodemo.database.interfaces.ShipmentRepositoryInterface;
import com.carriyo.carriyodemo.database.model.Shipment;
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
    public Shipment getShipmentDetail(String shipmentId) {
        try{
            return dynamoDBMapper.load(Shipment.class, shipmentId);
        }catch (Exception exception){
            throw new NotFoundException("No shipment is found in the database");
        }
    }

    @Override
    public Shipment addShipmentDetail(Shipment newShipment) {
        try{
            dynamoDBMapper.save(newShipment);
            return newShipment;
        }catch (Exception exception){
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    @Override
    public Shipment updateShipmentDetail(Shipment updateShipment) {
        try{
            Shipment shipment = dynamoDBMapper.load(Shipment.class,updateShipment.getShipmentId());
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
            Shipment shipment = dynamoDBMapper.load(Shipment.class,shipmentId);
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
