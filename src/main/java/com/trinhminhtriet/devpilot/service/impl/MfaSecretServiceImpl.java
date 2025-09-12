package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.dto.CreateMfaSecretRequest;
import com.trinhminhtriet.devpilot.dto.MfaSecretDto;
import com.trinhminhtriet.devpilot.dto.UpdateMfaSecretRequest;
import com.trinhminhtriet.devpilot.entity.MfaSecret;
import com.trinhminhtriet.devpilot.repository.MfaSecretRepository;
import com.trinhminhtriet.devpilot.service.MfaSecretService;
import com.trinhminhtriet.devpilot.utils.RSAUtil;
import de.vandermeer.asciitable.AsciiTable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MfaSecretServiceImpl implements MfaSecretService {

  private final MfaSecretRepository repository;

  public MfaSecretServiceImpl(MfaSecretRepository repository) {
    this.repository = repository;
  }

  @Override
  public MfaSecretDto create(CreateMfaSecretRequest request) {
    if (repository.existsByIssuerAndIdentifier(request.getIssuer(), request.getIdentifier())) {
      throw new IllegalArgumentException("MfaSecret with issuer and identifier already exists");
    }
    MfaSecret entity = new MfaSecret();
    entity.setIssuer(request.getIssuer());
    entity.setIdentifier(request.getIdentifier());
    entity.setSecret(request.getSecret());
    entity = repository.save(entity);
    return toDto(entity);
  }

  @Override
  public MfaSecretDto update(Long id, UpdateMfaSecretRequest request) {
    MfaSecret entity = repository.findById(id).orElseThrow();
    entity.setIssuer(request.getIssuer());
    entity.setIdentifier(request.getIdentifier());
    entity.setSecret(request.getSecret());
    entity = repository.save(entity);
    return toDto(entity);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  public MfaSecretDto get(Long id) {
    return repository.findById(id).map(this::toDto).orElse(null);
  }

  @Override
  public List<MfaSecretDto> getAll() {
    return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
  }

  @Override
  public String toAsciiTable() {
    List<MfaSecretDto> mfaSecrets = getAll();
    AsciiTable asciiTable = new AsciiTable();
    asciiTable.addRule();
    asciiTable.addRow("ID", "Issuer", "Identifier");
    asciiTable.addRule();
    for (MfaSecretDto dto : mfaSecrets) {
      asciiTable.addRow(dto.getId(), dto.getIssuer(), dto.getIdentifier());
      asciiTable.addRule();
    }
    return asciiTable.render();
  }

  @Override
  public String decode(Long id) throws Exception {
    MfaSecret mfaSecret = repository.findById(id).orElseThrow();
    String secret = mfaSecret.getSecret();
    RSAUtil rsaUtil = RSAUtil.getInstance();
    return rsaUtil.decode(secret);
  }

  private MfaSecretDto toDto(MfaSecret entity) {
    MfaSecretDto dto = new MfaSecretDto();
    dto.setId(entity.getId());
    dto.setIssuer(entity.getIssuer());
    dto.setIdentifier(entity.getIdentifier());
    dto.setSecret(entity.getSecret());
    dto.setCreatedAt(entity.getCreatedAt());
    dto.setUpdatedAt(entity.getUpdatedAt());
    return dto;
  }
}
