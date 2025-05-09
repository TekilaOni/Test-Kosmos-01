package com.test.consultorio.exception;

import lombok.Getter;

/**
 * Generic {@link RuntimeException} used as base implementation for errors generated in the
 * application.
 */
@Getter
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = 234325634533451L;

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
