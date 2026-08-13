package com.autoagent.telemetry;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class TelemetryId {

    @Column(name = "tel_id", nullable = false)
    private Long telId;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    public TelemetryId(Long telId, Instant timestamp) {
        this.telId = telId;
        this.timestamp = timestamp;
    }

}
