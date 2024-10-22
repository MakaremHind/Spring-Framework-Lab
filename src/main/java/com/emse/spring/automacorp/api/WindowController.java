package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dto.Window;
import com.emse.spring.automacorp.dto.WindowCommand;
import com.emse.spring.automacorp.mapper.WindowMapper;
import com.emse.spring.automacorp.model.WindowEntity;
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

    public WindowController(WindowDaoNew windowDao) {
        this.windowDao = windowDao;
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
        WindowEntity window = new WindowEntity(
                windowCommand.name(),
                windowCommand.windowStatus(),
                windowCommand.roomId()
        );
        WindowEntity savedWindow = windowDao.save(window);
        return ResponseEntity.ok(WindowMapper.of(savedWindow));
    }

    // Update a window (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Window> update(@PathVariable Long id, @RequestBody WindowCommand windowCommand) {
        return windowDao.findById(id).map(window -> {
            window.setName(windowCommand.name());
            window.setWindowStatus(windowCommand.windowStatus());
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
