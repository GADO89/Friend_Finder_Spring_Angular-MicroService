package com.user.management.exception;

import lombok.Getter;

import org.springframework.security.authentication.BadCredentialsException;

import com.user.management.config.translate.BundleTranslator;

@Getter
public class BaddAuthException extends BadCredentialsException {

    protected final String code;

    public BaddAuthException(String message, String code) {
        super(BundleTranslator.getMessage(message));
        this.code = code;
    }
}
