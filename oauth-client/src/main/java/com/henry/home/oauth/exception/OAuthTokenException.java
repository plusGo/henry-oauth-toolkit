package com.henry.home.oauth.exception;

public class OAuthTokenException extends RuntimeException{
    public OAuthTokenException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
