package com.example.backhotdoggourmet.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
}
