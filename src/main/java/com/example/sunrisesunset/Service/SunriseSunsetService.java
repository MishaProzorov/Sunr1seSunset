package com.example.sunrisesunset.Service;

import com.example.sunrisesunset.Model.CoordinateEntity;
import com.example.sunrisesunset.Model.SunriseSunsetEntity;
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
public class SunriseSunsetService {
    private final SunriseSunsetRepository repository;

    @Autowired
    public SunriseSunsetService(SunriseSunsetRepository repository) {
        this.repository = repository;
    }

    public SunriseSunsetEntity create(SunriseSunsetEntity sunriseSunset) {
        return repository.save(sunriseSunset);
    }

    public List<SunriseSunsetEntity> findAll() {
        return repository.findAll();
    }

    public Optional<SunriseSunsetEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public SunriseSunsetEntity update(Integer id, SunriseSunsetEntity updatedSunriseSunset) {
        return repository.findById(id)
                .map(sunriseSunset -> {
                    sunriseSunset.setDate(updatedSunriseSunset.getDate());
                    sunriseSunset.setSunrise(updatedSunriseSunset.getSunrise());
                    sunriseSunset.setSunset(updatedSunriseSunset.getSunset());
                    return repository.save(sunriseSunset);
                })
                .orElseThrow(() -> new RuntimeException("SunriseSunset not found with id: " + id));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<CoordinateEntity> getCoordinates(Integer sunriseSunsetId) {
        return repository.findById(sunriseSunsetId)
                .orElseThrow(() -> new RuntimeException("SunriseSunset not found"))
                .getCoordinates()
                .stream()
                .collect(Collectors.toList());
    }
}