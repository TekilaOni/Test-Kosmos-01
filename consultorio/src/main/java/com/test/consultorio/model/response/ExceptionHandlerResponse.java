package com.test.consultorio.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ExceptionHandlerResponse {

  private String mensaje;

  private HttpStatus codigo;
}
