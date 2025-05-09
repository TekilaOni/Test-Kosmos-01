package com.test.consultorio.exception;

import com.test.consultorio.model.response.ExceptionHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionHandlerResponse> handleNotFoundException(NotFoundException ex) {
    ExceptionHandlerResponse response =
        ExceptionHandlerResponse.builder()
            .mensaje(ex.getMessage())
            .codigo(HttpStatus.NOT_FOUND)
            .build();
    return new ResponseEntity<>(response, response.getCodigo());
  }

}
