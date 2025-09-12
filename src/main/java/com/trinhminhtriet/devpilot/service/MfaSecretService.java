package com.trinhminhtriet.devpilot.service;

import com.trinhminhtriet.devpilot.dto.CreateMfaSecretRequest;
import com.trinhminhtriet.devpilot.dto.MfaSecretDto;
import com.trinhminhtriet.devpilot.dto.UpdateMfaSecretRequest;
import java.util.List;

public interface MfaSecretService {

  MfaSecretDto create(CreateMfaSecretRequest request);

  MfaSecretDto update(Long id, UpdateMfaSecretRequest request);

  void delete(Long id);

  MfaSecretDto get(Long id);

  List<MfaSecretDto> getAll();
}
