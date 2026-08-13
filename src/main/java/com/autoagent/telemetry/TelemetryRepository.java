package com.autoagent.telemetry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryEntity, Long> {}
