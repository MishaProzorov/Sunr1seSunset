package com.example.sunrisesunset.Service;

import com.example.sunrisesunset.Model.CoordinateEntity;
import com.example.sunrisesunset.Model.SunriseSunsetEntity;
import com.example.sunrisesunset.Repository.CoordinateRepository;
import com.example.sunrisesunset.Repository.SunriseSunsetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;
    private final SunriseSunsetRepository sunriseSunsetRepository;

    @Autowired
    public CoordinateService(CoordinateRepository coordinateRepository,
                             SunriseSunsetRepository sunriseSunsetRepository) {
        this.coordinateRepository = coordinateRepository;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
    }

    // CRUD operations
    public CoordinateEntity create(CoordinateEntity coordinate) {
        return coordinateRepository.save(coordinate);
    }

    public List<CoordinateEntity> findAll() {
        return coordinateRepository.findAll();
    }

    public Optional<CoordinateEntity> findById(Integer id) {
        return coordinateRepository.findById(id);
    }

    public CoordinateEntity update(Integer id, CoordinateEntity updatedCoordinate) {
        return coordinateRepository.findById(id)
                .map(coordinate -> {
                    coordinate.setLatitude(updatedCoordinate.getLatitude());
                    coordinate.setLongitude(updatedCoordinate.getLongitude());
                    return coordinateRepository.save(coordinate);
                })
                .orElseThrow(() -> new RuntimeException("Coordinate not found with id: " + id));
    }

    public void delete(Integer id) {
        coordinateRepository.deleteById(id);
    }

    // Relationship operations
    public CoordinateEntity addSunriseSunsetTime(Integer coordinateId, Integer sunriseSunsetId) {
        CoordinateEntity coordinate = coordinateRepository.findById(coordinateId)
                .orElseThrow(() -> new RuntimeException("Coordinate not found"));
        SunriseSunsetEntity time = sunriseSunsetRepository.findById(sunriseSunsetId)
                .orElseThrow(() -> new RuntimeException("SunriseSunset time not found"));

        coordinate.addSunriseSunsetTime(time);
        return coordinateRepository.save(coordinate);
    }

    public List<SunriseSunsetEntity> getSunriseSunsetTimes(Integer coordinateId) {
        return coordinateRepository.findById(coordinateId)
                .orElseThrow(() -> new RuntimeException("Coordinate not found"))
                .getSunriseSunsetTimes()
                .stream()
                .collect(Collectors.toList());
    }
}