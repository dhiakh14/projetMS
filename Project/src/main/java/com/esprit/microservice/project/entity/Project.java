package com.esprit.microservice.project.entity;

import jakarta.persistence.*;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.Serializable;
import java.util.Date;

@Entity
@EnableDiscoveryClient
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProject;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String city;
    private String latitude;
    private String longitude;
    private String location;

    public Project() {
    }

    public Project(Long idProject, String name, String description, Date startDate, Date endDate, Status status, String city, String latitude, String longitude, String location) {
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    // Getters et Setters

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}