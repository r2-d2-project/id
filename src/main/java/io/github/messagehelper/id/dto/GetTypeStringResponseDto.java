package io.github.messagehelper.id.dto;

public class GetTypeStringResponseDto {
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public GetTypeStringResponseDto(Long id) {
    this.id = id.toString();
  }
}
