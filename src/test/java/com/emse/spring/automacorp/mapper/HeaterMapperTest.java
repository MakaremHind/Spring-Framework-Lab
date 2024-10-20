package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Heater;
import com.emse.spring.automacorp.mapper.HeaterMapper;
import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.HeaterStatus;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.util.FakeEntityBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeaterMapperTest {

    @Test
    public void testHeaterEntityToDTO() {
        // Arrange: Create a HeaterEntity using FakeEntityBuilder or manually
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "Test Room", null);
        SensorEntity statusSensor = FakeEntityBuilder.createSensorEntity(2L, "Heater Status", null, 0.0);
        HeaterEntity heaterEntity = FakeEntityBuilder.createHeaterEntity(1L, "Test Heater", room);
        heaterEntity.setStatus(statusSensor);
        heaterEntity.setHeaterStatus(HeaterStatus.ON);

        // Act: Map HeaterEntity to Heater DTO using HeaterMapper
        Heater heaterDTO = HeaterMapper.toDTO(heaterEntity);

        // Assert: Validate that the fields have been correctly mapped
        assertEquals(heaterEntity.getId(), heaterDTO.id());
        assertEquals(heaterEntity.getName(), heaterDTO.name());
        assertEquals(heaterEntity.getHeaterStatus(), heaterDTO.status());
        assertEquals(heaterEntity.getRoom().getId(), heaterDTO.roomId());
    }

    @Test
    public void testHeaterDTOToEntity() {
        // Arrange: Create a Heater DTO and corresponding RoomEntity and SensorEntity
        Heater heaterDTO = new Heater(1L, "Test Heater", HeaterStatus.ON, 1L);
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "Test Room", null);
        SensorEntity statusSensor = FakeEntityBuilder.createSensorEntity(2L, "Heater Status", null, 0.0);

        // Act: Map Heater DTO to HeaterEntity using HeaterMapper
        HeaterEntity heaterEntity = HeaterMapper.toEntity(heaterDTO, room, statusSensor);

        // Assert: Validate that the fields have been correctly mapped
        assertEquals(heaterDTO.id(), heaterEntity.getId());
        assertEquals(heaterDTO.name(), heaterEntity.getName());
        assertEquals(room, heaterEntity.getRoom());
        assertEquals(statusSensor, heaterEntity.getStatus());
    }
}
