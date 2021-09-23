package com.henry.home.oauth.starter.exception;

public class OAuthTokenException extends RuntimeException{
    public OAuthTokenException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
