package com.ums.backend.exception;

public class SubjectNotFound extends RuntimeException{
    public SubjectNotFound(String message){
        super(message);
    }
}
