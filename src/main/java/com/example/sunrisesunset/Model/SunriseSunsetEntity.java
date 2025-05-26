package com.example.sunrisesunset.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sunrise-sunset")
public class SunriseSunsetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @Column(name = "sunrise")
    @JsonProperty("sunrise")
    private OffsetTime sunrise;

    @Column(name = "sunset")
    @JsonProperty("sunset")
    private OffsetTime sunset;

    @Transient
    @JsonProperty("status")
    private String status;

    @ManyToMany(mappedBy = "sunriseSunsetTimes")
    private Set<CoordinateEntity> coordinates = new HashSet<>();

    // Constructors, getters and setters
    public SunriseSunsetEntity() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public OffsetTime getSunrise() { return sunrise; }
    public void setSunrise(OffsetTime sunrise) { this.sunrise = sunrise; }
    public OffsetTime getSunset() { return sunset; }
    public void setSunset(OffsetTime sunset) { this.sunset = sunset; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Set<CoordinateEntity> getCoordinates() { return coordinates; }
}