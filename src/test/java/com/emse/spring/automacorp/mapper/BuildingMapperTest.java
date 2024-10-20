package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Building;
import com.emse.spring.automacorp.model.BuildingEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.util.FakeEntityBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingMapperTest {

    @Test
    void testBuildingToDTO() {
        // Arrange: Use the FakeEntityBuilder to create a sample BuildingEntity
        Long buildingId = 1L;
        String buildingName = "MainBuilding";
        BuildingEntity buildingEntity = FakeEntityBuilder.createBuildingEntity(buildingId, buildingName);

        // Act: Map the BuildingEntity to Building DTO using the mapper
        Building buildingDTO = BuildingMapper.toDTO(buildingEntity);

        // Assert: Check the properties of the resulting DTO
        assertEquals(buildingId, buildingDTO.id());
        assertEquals(buildingName, buildingDTO.name());
        assertEquals(buildingEntity.getOutsideTemperature().getId(), buildingDTO.outsideTemperatureSensorId());

        List<RoomEntity> roomEntities = buildingEntity.getRooms();
        assertEquals(roomEntities.size(), buildingDTO.rooms().size());

        // Verify room mapping
        for (int i = 0; i < roomEntities.size(); i++) {
            RoomEntity roomEntity = roomEntities.get(i);
            assertEquals(roomEntity.getId(), buildingDTO.rooms().get(i).id());
            assertEquals(roomEntity.getName(), buildingDTO.rooms().get(i).name());
            assertEquals(roomEntity.getCurrentTemperature().getValue(), buildingDTO.rooms().get(i).currentTemperature());
            assertEquals(roomEntity.getTargetTemperature(), buildingDTO.rooms().get(i).targetTemperature());
        }
    }
}