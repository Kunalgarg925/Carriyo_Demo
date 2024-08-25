package com.carriyo.carriyodemo.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {
    private String errorDescription;
    private String errorCode;
}
