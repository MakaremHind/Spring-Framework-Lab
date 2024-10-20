package com.emse.spring.automacorp.mapper;

import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.util.FakeEntityBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindowMapperTest {

    @Test
    public void testWindowMapper() {
        // Arrange: Create a fake RoomEntity and WindowEntity using the FakeEntityBuilder
        RoomEntity room = FakeEntityBuilder.createRoomEntity(1L, "Test Room", null);
        WindowEntity windowEntity = FakeEntityBuilder.createWindowEntity(1L, "Test Window", room);

        // Act: Map WindowEntity to Window DTO using WindowMapper
        Window windowDto = WindowMapper.of(windowEntity);

        // Assert: Validate that the fields have been correctly mapped
        assertEquals(windowEntity.getId(), windowDto.id());
        assertEquals(windowEntity.getName(), windowDto.name());
        assertEquals(windowEntity.getWindowStatus(), windowDto.windowStatus());
        assertEquals(windowEntity.getRoom().getId(), windowDto.roomId());
    }
}
