package com.example.sunrisesunset.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import java.math.BigDecimal;

@Entity
@Table(name = "coordinates")
public class CoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude; // Изменено с Double на BigDecimal

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude; // Изменено с Double на BigDecimal

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "coordinate_sun_times",
            joinColumns = @JoinColumn(name = "coordinate_id"),
            inverseJoinColumns = @JoinColumn(name = "sunrisesunset_id")
    )

    private Set<SunriseSunsetEntity> sunriseSunsetTimes = new HashSet<>();

    // Constructors, getters and setters
    public CoordinateEntity() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public Set<SunriseSunsetEntity> getSunriseSunsetTimes() { return sunriseSunsetTimes; }

    // Helper methods
    public void addSunriseSunsetTime(SunriseSunsetEntity time) {
        sunriseSunsetTimes.add(time);
        time.getCoordinates().add(this);
    }

    public void removeSunriseSunsetTime(SunriseSunsetEntity time) {
        sunriseSunsetTimes.remove(time);
        time.getCoordinates().remove(this);
    }
}