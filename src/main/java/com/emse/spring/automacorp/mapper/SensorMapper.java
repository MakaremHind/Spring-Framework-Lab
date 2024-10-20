package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.model.SensorEntity;

public class SensorMapper {
    public static Sensor of(SensorEntity sensor) {
        return new Sensor(
                sensor.getId(),
                sensor.getName(),
                sensor.getValue(),
                sensor.getSensorType()
        );
    }
}