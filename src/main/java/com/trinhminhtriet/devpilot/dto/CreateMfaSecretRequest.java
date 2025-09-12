package com.trinhminhtriet.devpilot.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder(toBuilder = true)
public class CreateMfaSecretRequest {

  private String issuer;
  private String identifier;
  private String secret;

}
