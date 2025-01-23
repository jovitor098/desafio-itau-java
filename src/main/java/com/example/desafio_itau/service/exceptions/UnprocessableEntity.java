package com.example.desafio_itau.service.exceptions;

public class UnprocessableEntity extends RuntimeException{
    public UnprocessableEntity(String msg){
        super(msg);
    }
}
