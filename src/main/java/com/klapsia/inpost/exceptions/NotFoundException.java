package com.klapsia.inpost.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Entity not found");
    }
}
