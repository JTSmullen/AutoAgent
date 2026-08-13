package com.autoagent.telemetry;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;

    public TelemetryService (TelemetryRepository telemetryRepository){
        this.telemetryRepository = telemetryRepository;
    }

    public void saveTelEvent(TelemetryEntity entity){
        telemetryRepository.save(entity);
    }

}
