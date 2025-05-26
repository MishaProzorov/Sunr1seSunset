package com.example.sunrisesunset.Controller;

import com.example.sunrisesunset.Model.CoordinateEntity;
import com.example.sunrisesunset.Model.SunriseSunsetEntity;
import com.example.sunrisesunset.Service.CoordinateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinates")
public class CoordinateController {
    private final CoordinateService coordinateService;

    public CoordinateController(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    @PostMapping
    public ResponseEntity<CoordinateEntity> create(@RequestBody CoordinateEntity coordinate) {
        return ResponseEntity.ok(coordinateService.create(coordinate));
    }

    @GetMapping
    public ResponseEntity<List<CoordinateEntity>> findAll() {
        return ResponseEntity.ok(coordinateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinateEntity> findById(@PathVariable Integer id) {
        return coordinateService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoordinateEntity> update(
            @PathVariable Integer id,
            @RequestBody CoordinateEntity coordinate) {
        return ResponseEntity.ok(coordinateService.update(id, coordinate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        coordinateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{coordinateId}/sunrise-sunset/{sunriseSunsetId}")
    public ResponseEntity<CoordinateEntity> addSunriseSunsetTime(
            @PathVariable Integer coordinateId,
            @PathVariable Integer sunriseSunsetId) {
        return ResponseEntity.ok(
                coordinateService.addSunriseSunsetTime(coordinateId, sunriseSunsetId));
    }

    @GetMapping("/{coordinateId}/sunrise-sunset")
    public ResponseEntity<List<SunriseSunsetEntity>> getSunriseSunsetTimes(
            @PathVariable Integer coordinateId) {
        return ResponseEntity.ok(
                coordinateService.getSunriseSunsetTimes(coordinateId));
    }
}