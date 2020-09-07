package io.github.messagehelper.id.exception;

import io.github.messagehelper.id.dto.GetExceptionResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
  private ResponseEntity<String> errorResponse;

  public GlobalExceptionHandler() {
    HttpHeaders errorHeaders = new HttpHeaders();
    errorHeaders.add("content-type", "application/json;charset=utf-8");
    errorHeaders.add("allow", "GET,HEAD,OPTIONS");
    errorResponse =
        ResponseEntity.status(405)
            .headers(errorHeaders)
            .body("{\"error\":\"only accept GET method\"}");
  }

  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<String> handle(HttpRequestMethodNotSupportedException e) {
    return errorResponse;
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public GetExceptionResponseDto handle(RuntimeException e) {
    e.printStackTrace();
    return new GetExceptionResponseDto(e.toString());
  }
}
