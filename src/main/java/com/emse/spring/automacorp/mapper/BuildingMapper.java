package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Building;
import com.emse.spring.automacorp.model.BuildingEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BuildingMapper {

    // Map BuildingEntity to BuildingDTO
    public static Building toDTO(BuildingEntity buildingEntity) {
        return new Building(
                buildingEntity.getId(),
                buildingEntity.getName(),
                buildingEntity.getOutsideTemperature().getId(),
                buildingEntity.getRooms().stream()
                        .map(RoomMapper::of)
                        .collect(Collectors.toList())
        );
    }
}
