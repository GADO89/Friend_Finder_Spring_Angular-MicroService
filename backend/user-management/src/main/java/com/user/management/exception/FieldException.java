package com.user.management.exception;

import lombok.Getter;

import com.user.management.config.translate.BundleTranslator;

@Getter
public class FieldException extends RuntimeException {

    protected final String code;
    protected final String field;

    public FieldException(String message, String code, String field) {
        super(BundleTranslator.getMessage(message));
        this.code = code;
        this.field = field;
    }
}
