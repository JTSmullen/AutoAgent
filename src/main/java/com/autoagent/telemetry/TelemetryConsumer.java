package com.autoagent.telemetry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TelemetryConsumer {

    private final TelemetryService telServ;

    public TelemetryConsumer(TelemetryService telServ) {
        this.telServ = telServ;
    }

    @KafkaListener(topics = "telemetry-ingestion-events")
    public void fetchTelemetryIngestion(TelemetryEvent event) {
        log.info("fetching telemetry event");

        TelemetryEntity entity = new TelemetryEntity(
                event.satelliteId(),
                event.timestamp(),
                event.latitude(),
                event.longitude(),
                event.altitude(),
                event.velocity()
        );

        telServ.saveTelEvent(entity);

        log.info("saved tel event");

    }

}
