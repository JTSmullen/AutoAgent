package com.autoagent.telemetry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter
@Setter
public class TelemetryEntity {

    public TelemetryEntity (String satelliteId, Instant timestamp, double latitude,
                            double longitude, double altitude, double velocity){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tel_id", nullable = false)
    private Long telId;

    private String satelliteId;
    private Instant timestamp;
    private double latitude;
    private double longitude;
    private double altitude;
    private double velocity;

}
