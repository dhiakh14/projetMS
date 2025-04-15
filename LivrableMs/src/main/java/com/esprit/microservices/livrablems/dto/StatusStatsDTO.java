package com.esprit.microservices.livrablems.dto;
import java.util.Map;
public class StatusStatsDTO {
    private String projectName;
    private Map<String, Long> statusCounts;

    public StatusStatsDTO(String projectName, Map<String, Long> statusCounts) {
        this.projectName = projectName;
        this.statusCounts = statusCounts;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Map<String, Long> getStatusCounts() {
        return statusCounts;
    }

    public void setStatusCounts(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }
}
