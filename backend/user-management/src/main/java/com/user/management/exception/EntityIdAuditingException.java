package com.user.management.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class EntityIdAuditingException extends FieldException {
    public EntityIdAuditingException(String code, String field) {
        super(String.format("Field[%s] not allowed to pass when create  a new entity",
                        field), code, field);
    }
}
