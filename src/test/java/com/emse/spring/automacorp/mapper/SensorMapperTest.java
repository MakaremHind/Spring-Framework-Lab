package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SensorMapperTest {

    @Test
    public void shouldMapSensor() {
        // Arrange
        SensorEntity sensorEntity = new SensorEntity(SensorType.TEMPERATURE, "Temp Sensor");
        sensorEntity.setId(1L);
        sensorEntity.setValue(22.0);

        // Act
        Sensor sensor = SensorMapper.of(sensorEntity);

        // Assert
        Assertions.assertThat(sensor.id()).isEqualTo(1L);
        Assertions.assertThat(sensor.name()).isEqualTo("Temp Sensor");
        Assertions.assertThat(sensor.value()).isEqualTo(22.0);
        Assertions.assertThat(sensor.sensorType()).isEqualTo(SensorType.TEMPERATURE);
    }
}
