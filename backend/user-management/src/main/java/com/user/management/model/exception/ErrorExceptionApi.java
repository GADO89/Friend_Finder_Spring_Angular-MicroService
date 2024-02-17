package com.user.management.model.exception;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.management.model.bundle.BundleErrorMessage;

@Data
public class ErrorExceptionApi {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd hh:mm:ss")
    private LocalDateTime localDateTime;
    @JsonProperty("errorMessage")
    private BundleErrorMessage bundleErrorMessage;

    public ErrorExceptionApi(HttpStatus status, BundleErrorMessage bundleErrorMessage) {
        localDateTime = localDateTime.now();
        this.status = status;
        this.bundleErrorMessage = bundleErrorMessage;
    }
}
