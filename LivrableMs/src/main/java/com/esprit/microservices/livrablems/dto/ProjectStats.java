package com.esprit.microservices.livrablems.dto;
import com.esprit.microservices.livrablems.entities.Livrable;

import java.util.List;

public class ProjectStats {

    private double completionPercentage;
    private Double averageTimeToCompletion; // in days
    private List<Livrable> upcomingDeadlines;

    public ProjectStats(double completionPercentage, Double averageTimeToCompletion, List<Livrable> upcomingDeadlines) {
        this.completionPercentage = completionPercentage;
        this.averageTimeToCompletion = averageTimeToCompletion;
        this.upcomingDeadlines = upcomingDeadlines;
    }

    // Getters and setters
    public double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public Double getAverageTimeToCompletion() {
        return averageTimeToCompletion;
    }

    public void setAverageTimeToCompletion(Double averageTimeToCompletion) {
        this.averageTimeToCompletion = averageTimeToCompletion;
    }

    public List<Livrable> getUpcomingDeadlines() {
        return upcomingDeadlines;
    }

    public void setUpcomingDeadlines(List<Livrable> upcomingDeadlines) {
        this.upcomingDeadlines = upcomingDeadlines;
    }
}

