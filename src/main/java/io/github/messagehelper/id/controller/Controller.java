package io.github.messagehelper.id.controller;

import io.github.messagehelper.id.dao.IdDao;
import io.github.messagehelper.id.dto.GetTimeResponseDto;
import io.github.messagehelper.id.dto.GetTypeLongResponseDto;
import io.github.messagehelper.id.dto.GetTypeStringResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  private final IdDao idDao;
  private final HttpHeaders headers;

  public Controller(@Autowired IdDao idDao) {
    this.idDao = idDao;
    //
    headers = new HttpHeaders();
    headers.add("Content-Type", "application/json;charset=utf-8");
    headers.add("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "no-store");
    headers.add("Surrogate-Control", "no-store");
  }

  @GetMapping("/")
  public ResponseEntity<GetTypeLongResponseDto> get() {
    return ResponseEntity.status(200)
        .headers(headers)
        .body(new GetTypeLongResponseDto(idDao.generate()));
  }

  @GetMapping("/time")
  public ResponseEntity<GetTimeResponseDto> getTime() {
    Long id = idDao.generate();
    return ResponseEntity.status(200)
        .headers(headers)
        .body(new GetTimeResponseDto(id, idDao.parse(id)));
  }

  @GetMapping("/time/{id}")
  public ResponseEntity<GetTimeResponseDto> getTimeId(@PathVariable("id") String idString) {
    Long id;
    try {
      id = Long.parseLong(idString);
    } catch (NumberFormatException e) {
      return getTime();
    }
    return ResponseEntity.status(200)
        .headers(headers)
        .body(new GetTimeResponseDto(id, idDao.parse(id)));
  }

  @GetMapping("/type/string")
  public ResponseEntity<GetTypeStringResponseDto> getTypeString() {
    return ResponseEntity.status(200)
        .headers(headers)
        .body(new GetTypeStringResponseDto(idDao.generate()));
  }

  @GetMapping("/type/plain")
  public String getTypePlain() {
    return idDao.generate().toString();
  }
}
