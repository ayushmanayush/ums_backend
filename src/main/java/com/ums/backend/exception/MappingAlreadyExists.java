package com.ums.backend.exception;

public class MappingAlreadyExists extends RuntimeException{
    public MappingAlreadyExists(String message){
        super(message);
    }
}
