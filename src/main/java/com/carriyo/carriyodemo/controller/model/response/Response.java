package com.carriyo.carriyodemo.controller.model.response;

import com.carriyo.carriyodemo.controller.model.response.shipment_model.Shipment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private ErrorData errorResponse;
    private Shipment data;

    public Response(Object responseObject) {
        if (responseObject instanceof ErrorData) {
            this.errorResponse = (ErrorData) responseObject;
        } else if (responseObject instanceof Shipment) {
            this.data = (Shipment) responseObject;
        }
    }
}
