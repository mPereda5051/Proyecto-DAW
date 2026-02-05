package com.jinbu.backend_jinbu.exception;

public class PhotoNotFoundException extends RuntimeException {

    public PhotoNotFoundException(Long id) {
        super("The photo id " + id + " does not exist");
    }
}
