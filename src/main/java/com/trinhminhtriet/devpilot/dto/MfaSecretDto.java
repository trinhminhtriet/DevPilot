package com.trinhminhtriet.devpilot.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class MfaSecretDto {

  private Long id;
  private String issuer;
  private String identifier;
  private String secret;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
