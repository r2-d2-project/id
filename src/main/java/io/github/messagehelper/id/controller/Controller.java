package io.github.messagehelper.id.controller;

import io.github.messagehelper.id.dao.IdDao;
import io.github.messagehelper.id.dto.GetTypeLongResponseDto;
import io.github.messagehelper.id.dto.GetTypeStringResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  private IdDao idDao;
  private HttpHeaders headers;

  public Controller(@Autowired IdDao idDao) {
    this.idDao = idDao;
    //
    headers = new HttpHeaders();
    headers.add("content-type", "application/json;charset=utf-8");
    headers.add("cache-control", "no-store, no-cache, must-revalidate, proxy-revalidate");
    headers.add("pragma", "no-cache");
    headers.add("expires", "no-store");
    headers.add("surrogate-control", "no-store");
  }

  @GetMapping("/*")
  public ResponseEntity<GetTypeLongResponseDto> get() {
    return ResponseEntity.status(200)
        .headers(headers)
        .body(new GetTypeLongResponseDto(idDao.generate()));
  }

  @GetMapping("/type/string")
  public ResponseEntity<GetTypeStringResponseDto> getTypeString() {
    return ResponseEntity.status(200)
        .headers(headers)
        .body(new GetTypeStringResponseDto(idDao.generate()));
  }
}
