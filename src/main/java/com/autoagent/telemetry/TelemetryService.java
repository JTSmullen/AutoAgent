package com.autoagent.telemetry;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;
    private Long Id = (long) 0;

    public TelemetryService (TelemetryRepository telemetryRepository){
        this.telemetryRepository = telemetryRepository;
    }

    public void saveTelEvent(TelemetryEvent event){
        TelemetryId id = new TelemetryId(getNextId(), event.timestamp());

        TelemetryEntity entity = new TelemetryEntity(
                id,
                event.satelliteId(),
                event.latitude(),
                event.longitude(),
                event.altitude(),
                event.velocity()
        );

        telemetryRepository.save(entity);
    }

    private Long getNextId(){
        Id++;
        return Id;
    }

}
