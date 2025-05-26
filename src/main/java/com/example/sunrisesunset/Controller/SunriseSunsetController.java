package com.example.sunrisesunset.Controller;

import com.example.sunrisesunset.Model.CoordinateEntity;
import com.example.sunrisesunset.Model.SunriseSunsetEntity;
import com.example.sunrisesunset.Service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sunrise-sunset")
public class SunriseSunsetController {
    private final SunriseSunsetService sunriseSunsetService;

    public SunriseSunsetController(SunriseSunsetService sunriseSunsetService) {
        this.sunriseSunsetService = sunriseSunsetService;
    }

    @PostMapping
    public ResponseEntity<SunriseSunsetEntity> create(@RequestBody SunriseSunsetEntity sunriseSunset) {
        return ResponseEntity.ok(sunriseSunsetService.create(sunriseSunset));
    }

    @GetMapping
    public ResponseEntity<List<SunriseSunsetEntity>> findAll() {
        return ResponseEntity.ok(sunriseSunsetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SunriseSunsetEntity> findById(@PathVariable Integer id) {
        return sunriseSunsetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SunriseSunsetEntity> update(
            @PathVariable Integer id,
            @RequestBody SunriseSunsetEntity sunriseSunset) {
        return ResponseEntity.ok(sunriseSunsetService.update(id, sunriseSunset));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        sunriseSunsetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/coordinates")
    public ResponseEntity<List<CoordinateEntity>> getCoordinates(@PathVariable Integer id) {
        return ResponseEntity.ok(sunriseSunsetService.getCoordinates(id));
    }
}