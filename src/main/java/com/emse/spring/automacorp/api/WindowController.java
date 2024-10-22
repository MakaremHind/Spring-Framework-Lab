package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.dto.WindowCommand;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.newdao.WindowDaoNew;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {

    private final WindowDaoNew windowDao;
    private final RoomDao roomDao;
    private final SensorDao sensorDao;  // Add SensorDao

    public WindowController(WindowDaoNew windowDao, RoomDao roomDao, SensorDao sensorDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.sensorDao = sensorDao;  // Initialize SensorDao
    }

    // Retrieve all windows (GET)
    @GetMapping
    public List<Window> findAll() {
        return windowDao.findAll()
                .stream()
                .map(WindowMapper::of)
                .collect(Collectors.toList());
    }

    // Retrieve a window by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Window> findById(@PathVariable Long id) {
        return windowDao.findById(id)
                .map(window -> ResponseEntity.ok(WindowMapper.of(window)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a window (POST)
    @PostMapping
    public ResponseEntity<Window> create(@RequestBody WindowCommand windowCommand) {
        // Fetch the RoomEntity based on roomId from the command
        RoomEntity room = roomDao.findById(windowCommand.roomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Fetch the SensorEntity based on sensorId from the command
        SensorEntity sensor = sensorDao.findById(windowCommand.sensorid())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        // Create the WindowEntity and assign the room and sensor status
        WindowEntity window = new WindowEntity(
                windowCommand.name(),
                sensor,  // Assign SensorEntity as the window status (related to sensor status)
                room  // Assign the RoomEntity to the window
        );

        // Save and return the created window
        WindowEntity savedWindow = windowDao.save(window);
        return ResponseEntity.ok(WindowMapper.of(savedWindow));
    }

    // Update a window (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Window> update(@PathVariable Long id, @RequestBody WindowCommand windowCommand) {
        // Fetch the sensor using sensorId
        SensorEntity sensor = sensorDao.findById(windowCommand.sensorid())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        return windowDao.findById(id).map(window -> {
            window.setName(windowCommand.name());
            window.setSensorStatus(sensor);  // Update the sensorStatus using the fetched SensorEntity
            WindowEntity updatedWindow = windowDao.save(window);
            return ResponseEntity.ok(WindowMapper.of(updatedWindow));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a window (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (windowDao.existsById(id)) {
            windowDao.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
