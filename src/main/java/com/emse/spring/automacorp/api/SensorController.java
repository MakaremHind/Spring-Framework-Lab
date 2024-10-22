package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dao.SensorDao;
import com.emse.spring.automacorp.dto.Sensor;
import com.emse.spring.automacorp.dto.SensorCommand;
import com.emse.spring.automacorp.mapper.SensorMapper;
import com.emse.spring.automacorp.model.SensorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sensors")
@Transactional
public class SensorController {

    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    // Retrieve a list of sensors (GET)
    @GetMapping
    public List<Sensor> findAll() {
        return sensorDao.findAll()
                .stream()
                .map(SensorMapper::of)
                .collect(Collectors.toList());
    }

    // Retrieve a particular sensor by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> findById(@PathVariable Long id) {
        return sensorDao.findById(id)
                .map(sensor -> ResponseEntity.ok(SensorMapper.of(sensor)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new sensor (POST)
    @PostMapping
    public ResponseEntity<Sensor> create(@RequestBody SensorCommand sensorCommand) {
        SensorEntity entity = new SensorEntity(sensorCommand.sensorType(), sensorCommand.name());
        entity.setValue(sensorCommand.value());
        SensorEntity saved = sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.of(saved));
    }

    // Update an existing sensor (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> update(@PathVariable Long id, @RequestBody SensorCommand sensorCommand) {
        return sensorDao.findById(id).map(sensor -> {
            sensor.setName(sensorCommand.name());
            sensor.setValue(sensorCommand.value());
            sensor.setSensorType(sensorCommand.sensorType());
            return ResponseEntity.ok(SensorMapper.of(sensor));
        }).orElse(ResponseEntity.badRequest().build());
    }

    // Delete a sensor by ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (sensorDao.existsById(id)) {
            sensorDao.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

