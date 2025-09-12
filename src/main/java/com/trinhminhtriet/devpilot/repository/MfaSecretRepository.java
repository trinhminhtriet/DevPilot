package com.trinhminhtriet.devpilot.repository;

import com.trinhminhtriet.devpilot.entity.MfaSecret;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MfaSecretRepository extends JpaRepository<MfaSecret, Long> {

  Optional<MfaSecret> findByIssuerAndIdentifier(String issuer, String identifier);

  boolean existsByIssuerAndIdentifier(String issuer, String identifier);
}
