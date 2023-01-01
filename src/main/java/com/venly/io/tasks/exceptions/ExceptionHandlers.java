package com.venly.io.tasks.exceptions;

import com.venly.io.tasks.controller.v1.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<com.venly.io.tasks.controller.v1.models.ErrorResponse> handleInvalidInput(InvalidInputException ex) {
        ErrorResponse error = new ErrorResponse("201", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
