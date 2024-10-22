package com.emse.spring.automacorp.api;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dto.Room;
import com.emse.spring.automacorp.dto.RoomCommand;
import com.emse.spring.automacorp.mapper.RoomMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.model.WindowStatus;
import com.emse.spring.automacorp.newdao.HeaterDaoNew;
import com.emse.spring.automacorp.newdao.WindowDaoNew;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    private final RoomDao roomDao;
    private final WindowDaoNew windowDao;
    private final HeaterDaoNew heaterDao;

    public RoomController(RoomDao roomDao, WindowDaoNew windowDao, HeaterDaoNew heaterDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;
        this.heaterDao = heaterDao;
    }

    // Retrieve all rooms (GET)
    @GetMapping
    public List<Room> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomMapper::of)
                .collect(Collectors.toList());
    }

    // Retrieve a room by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        return roomDao.findById(id)
                .map(room -> ResponseEntity.ok(RoomMapper.of(room)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Add or update a room (POST/PUT)
    @PostMapping
    public ResponseEntity<Room> createOrUpdate(@RequestBody RoomCommand roomCommand) {
        RoomEntity room = new RoomEntity(
                roomCommand.name(),
                roomCommand.currentTemperature(),
                roomCommand.floor()
        );
        room.setTargetTemperature(roomCommand.targetTemperature());
        RoomEntity savedRoom = roomDao.save(room);
        return ResponseEntity.ok(RoomMapper.of(savedRoom));
    }

    // Delete a room (and associated windows/heaters) (DELETE)
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        if (roomDao.existsById(roomId)) {
            roomDao.deleteById(roomId);
            // Delete associated windows and heaters
            windowDao.deleteByRoomId(roomId);
            heaterDao.deleteByRoomId(roomId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Switch room windows to OPEN (PUT)
    @PutMapping("/{roomId}/openWindows")
    public ResponseEntity<Void> openWindows(@PathVariable Long roomId) {
        List<WindowEntity> windows = windowDao.findByRoomId(roomId);
        windows.forEach(window -> window.setWindowStatus(WindowStatus.OPEN));
        windowDao.saveAll(windows);
        return ResponseEntity.ok().build();
    }

    // Switch room windows to CLOSED (PUT)
    @PutMapping("/{roomId}/closeWindows")
    public ResponseEntity<Void> closeWindows(@PathVariable Long roomId) {
        List<WindowEntity> windows = windowDao.findByRoomId(roomId);
        windows.forEach(window -> window.setWindowStatus(WindowStatus.CLOSED));
        windowDao.saveAll(windows);
        return ResponseEntity.ok().build();
    }
}
