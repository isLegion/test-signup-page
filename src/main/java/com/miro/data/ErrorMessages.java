package com.miro.data;

public enum ErrorMessages {
    EMPTY_NAME("Please enter your name."),
    EMPTY_EMAIL("Please enter your email address."),
    EMPTY_PASSWORD("Please enter your password."),
    NO_TERMS("Please agree with the Terms to sign up."),
    INVALID_EMAIL("This doesn’t look like an email address. Please check it for typos and try again."),
    REGISTERED_EMAIL("Sorry, this email is already registered"),
    SECURE_PASSWORD("Please use 8+ characters for secure password");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
