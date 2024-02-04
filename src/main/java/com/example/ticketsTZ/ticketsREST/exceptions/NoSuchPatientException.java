package com.example.ticketsTZ.ticketsREST.exceptions;

public class NoSuchPatientException extends RuntimeException{
    public NoSuchPatientException(String message){
        super(message);
    }
}
