package io.github.messagehelper.id.dto;

public class GetExceptionResponseDto {
  private String reason;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public GetExceptionResponseDto(String reason) {
    this.reason = reason;
  }
}
