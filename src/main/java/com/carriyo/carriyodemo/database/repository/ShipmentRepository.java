package com.carriyo.carriyodemo.database.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.kms.model.NotFoundException;
import com.carriyo.carriyodemo.database.interfaces.ShipmentRepositoryInterface;
import com.carriyo.carriyodemo.database.model.Shipment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;


@Repository
public class ShipmentRepository implements ShipmentRepositoryInterface {
    private final DynamoDBMapper dynamoDBMapper;
    private final RestHighLevelClient elasticSearchClient;
    private static final String INDEX_NAME = "shipment-index";
    @Autowired
    public ShipmentRepository(DynamoDBMapper dynamoDBMapper, RestHighLevelClient restHighLevelClient) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.elasticSearchClient= restHighLevelClient;
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
            throw new NotFoundException(exception.getMessage());
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
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        }catch (Exception exception){
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public Shipment elasticSearchShipmentByShipmentId(String shipmentId) {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("shipmentId", shipmentId));
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = elasticSearchClient.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                Shipment shipment = mapJsonToShipment(searchResponse.getHits().getAt(0).getSourceAsString());
                return shipment;
            } else {
                throw new NotFoundException("No shipment is found in the database");
            }
        } catch (NotFoundException exception) {
            throw new NotFoundException(exception.getMessage());
        }catch (Exception exception){
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
    private Shipment mapJsonToShipment(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Shipment.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
