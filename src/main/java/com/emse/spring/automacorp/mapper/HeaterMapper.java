package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Heater;
import com.emse.spring.automacorp.model.HeaterEntity;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;

public class HeaterMapper {

    // Map HeaterEntity to HeaterDTO
    public static Heater toDTO(HeaterEntity heaterEntity) {
        return new Heater(
                heaterEntity.getId(),
                heaterEntity.getName(),
                heaterEntity.getHeaterStatus(),
                heaterEntity.getRoom().getId()
        );
    }

    // Map HeaterDTO to HeaterEntity
    public static HeaterEntity toEntity(Heater heaterDTO, RoomEntity room, SensorEntity statusSensor) {
        HeaterEntity heaterEntity = new HeaterEntity();
        heaterEntity.setId(heaterDTO.id());
        heaterEntity.setName(heaterDTO.name());
        heaterEntity.setRoom(room);  // Assuming RoomEntity is passed as parameter
        heaterEntity.setStatus(statusSensor);  // Assuming SensorEntity is passed as parameter
        return heaterEntity;
    }
}
