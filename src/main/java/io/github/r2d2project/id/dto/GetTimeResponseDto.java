package io.github.r2d2project.id.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimeResponseDto {
  private static final SimpleDateFormat RFC3339 =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

  private Long id;
  private Long timestamp;
  private final String rfc3339;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getRfc3339() {
    return rfc3339;
  }

  public GetTimeResponseDto(Long id, Long timestamp) {
    this.id = id;
    this.timestamp = timestamp;
    this.rfc3339 = RFC3339.format(new Date(timestamp));
  }
}
