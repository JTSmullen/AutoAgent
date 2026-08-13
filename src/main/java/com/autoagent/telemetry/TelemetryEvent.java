package com.autoagent.telemetry;

import java.time.Instant;

/**
 * Standardized time series event to be sent to kafka
 */
public record TelemetryEvent (

    String satelliteId,
    Instant timestamp,
    double latitude,
    double longitude,
    double altitude,
    double velocity

) {}
