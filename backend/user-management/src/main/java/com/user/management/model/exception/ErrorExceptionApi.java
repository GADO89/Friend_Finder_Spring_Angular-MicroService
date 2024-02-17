package com.user.management.model.exception;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class ErrorExceptionApi {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd hh:mm:ss")

    private LocalDateTime localDateTime;

    private String message;

    public ErrorExceptionApi(HttpStatus status, String message) {
        localDateTime = localDateTime.now();
        this.status = status;
        this.message = message;
    }
}
