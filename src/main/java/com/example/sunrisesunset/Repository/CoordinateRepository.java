package com.example.sunrisesunset.Repository;

import com.example.sunrisesunset.Model.CoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<CoordinateEntity, Integer> {
}