package com.example.ticketsTZ.ticketsREST.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TicketsExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionDTO> handleNoSuchPatientException(NoSuchPatientException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage());

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

}
