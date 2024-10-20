package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.Comparator;
import java.util.stream.Collectors;

public class RoomMapper {
    public static Room of(RoomEntity room) {
        return new Room(
                room.getId(),
                room.getName(),
                room.getFloor(),
                room.getCurrentTemperature().getValue(),
                room.getTargetTemperature(),
                room.getWindows().stream().sorted(Comparator.comparing(WindowEntity::getName))
                        .map(WindowMapper::of)  // Use method reference to call WindowMapper.of
                        .collect(Collectors.toList()),
                room.getHeaters().stream()
                        .map(HeaterMapper::toDTO)  // Use method reference to call WindowMapper.of
                        .collect(Collectors.toList())// assuming RoomEntity has a getBuilding() method returning a BuildingEntity
        );
    }
}