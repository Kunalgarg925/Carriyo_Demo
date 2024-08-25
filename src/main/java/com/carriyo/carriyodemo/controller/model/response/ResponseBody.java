package com.carriyo.carriyodemo.controller.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody {
    private ErrorResponse errorResponse;
    private ShipmentResponse data;

    public ResponseBody(Object responseObject) {
        if (responseObject instanceof ErrorResponse) {
            this.errorResponse = (ErrorResponse) responseObject;
        } else if (responseObject instanceof ShipmentResponse) {
            this.data = (ShipmentResponse) responseObject;
        }
    }
}
