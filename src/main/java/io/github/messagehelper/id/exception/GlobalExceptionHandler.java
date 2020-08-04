package io.github.messagehelper.id.exception;

import io.github.messagehelper.id.dto.GetExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public GetExceptionResponseDto handleRuntimeException(RuntimeException e) {
    e.printStackTrace();
    return new GetExceptionResponseDto(e.toString());
  }
}
