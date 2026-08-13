package com.autoagent.telemetry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class TelemetryIngestionService {

    private final KafkaTemplate<String, TelemetryEvent> kafkaTemplate;

    private final WebClient issWebClient = WebClient.create("http://api.open-notify.org");

    private static final String TELEMETRY_TOPIC = "telemetry-ingestion-events";

    @Scheduled(fixedRate = 10000)
    public void fetchAndIngestTelemetry() {
        log.info("Polling live ISS Satellite telemetry...");

        issWebClient.get()
                .uri("/iss-now.json")
                .retrieve()
                .bodyToMono(Map.class)
                .subscribe(
                        this::processAndPublish,
                        error -> log.error("Failed to fetch satellite telemetry", error)
                );
    }

    @SuppressWarnings("unchecked")
    private void processAndPublish(Map<String, Object> response) {
        try {
            if ("success".equals(response.get("message"))) {
                Map<String, String> position = (Map<String, String>) response.get("iss_position");

                double lat = Double.parseDouble(position.get("latitude"));
                double lon = Double.parseDouble(position.get("longitude"));
                long timestampSeconds = ((Number) response.get("timestamp")).longValue();

                String satelliteId = "ISS-25544";

                TelemetryEvent event = new TelemetryEvent(
                        satelliteId,
                        Instant.ofEpochSecond(timestampSeconds),
                        lat,
                        lon,
                        420.0,
                        7.66
                );

                kafkaTemplate.send(TELEMETRY_TOPIC, event.satelliteId(), event);

                log.info("SUCCESS! Published real ISS Telemetry [Lat: {}, Lon: {}] to Kafka topic '{}'",
                        lat, lon, TELEMETRY_TOPIC);
            }
        } catch (Exception e) {
            log.error("Error parsing telemetry payload", e);
        }
    }
}