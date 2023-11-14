package com.example.backhotdoggourmet.exceptions;

public class ResourceBadRequestException extends RuntimeException {
    
    public ResourceBadRequestException(String mensagem){
        super(mensagem);
    }
}
