package com.autoagent.telemetry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryEntity, Long> {

    Optional<TelemetryEntity> findFirstBySatelliteIdOrderByIdTimestampDesc(
            String satelliteId
    );

    List<TelemetryEntity> findBySatelliteIdAndIdTimestampBetweenOrderByIdTimestampAsc(
            String satelliteId,
            Instant startTime,
            Instant endTime
    );

}
