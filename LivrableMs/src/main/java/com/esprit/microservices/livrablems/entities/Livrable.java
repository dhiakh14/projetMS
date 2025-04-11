package com.esprit.microservices.livrablems.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Livrable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivrable;
    private String projectName;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String description;

    private int completed_count; // Nombre de livrables complétés
    private int total_count; // Nombre total de livrables

    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date due_date; // date d'échéance
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Livrable() {
    }

    public Livrable(String projectName, String title, Type type, String description, int completed_count, int total_count, Status status, Date due_date) {
        this.projectName = projectName;
        this.title = title;
        this.type = type;
        this.description = description;
        this.completed_count = completed_count;
        this.total_count = total_count;
        this.status = status;
        this.due_date = due_date;
    }

    // Getters and Setters
    public Long getIdLivrable() {
        return idLivrable;
    }

    public void setIdLivrable(Long idLivrable) {
        this.idLivrable = idLivrable;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompleted_count() {
        return completed_count;
    }

    public void setCompleted_count(int completed_count) {
        this.completed_count = completed_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Livrable{" +
                "idLivrable=" + idLivrable +
                ", projectName='" + projectName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", completed_count=" + completed_count +
                ", total_count=" + total_count +
                ", status=" + status +
                ", due_date=" + due_date +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
