package com.autoagent.telemetry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TelemetryEntity {

    @EmbeddedId
    private TelemetryId id;

    private String satelliteId;
    private double latitude;
    private double longitude;
    private double altitude;
    private double velocity;


    public TelemetryEntity (TelemetryId id, String satelliteId, double latitude,
                            double longitude, double altitude, double velocity){
        this.id = id;
        this.satelliteId = satelliteId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.velocity = velocity;
    }

}
