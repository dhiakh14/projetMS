package com.esprit.microservice.project.controller;

import com.esprit.microservice.project.entity.Project;
import com.esprit.microservice.project.entity.Status;
import com.esprit.microservice.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/project")
public class ProjectRestAPI {
    @Autowired
    private ProjectService projectService;

    //CRUD
    @PostMapping("/addProject")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @PutMapping("/updateProject/{idProject}")
    public Project updateProject(@PathVariable Long idProject, @RequestBody Project project) {
        return projectService.updateProject(idProject,project);
    }
    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> getAllProjects() {
        System.out.println("API /getAllProjects called");
        List<Project> projects = projectService.getAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/getProjectById/{idProject}")
    public Project findProjectById(@PathVariable Long idProject) {
        return projectService.findProjectById(idProject);
    }

    @DeleteMapping("/deleteProject/{idProject}")
    public void deleteProject(@PathVariable(value = "idProject") Long idProject){
        projectService.deleteProject(idProject);
    }

    // Statistiques par statut
    @GetMapping("/statisticsByStatus")
    public Map<Status, Long> getStatisticsByStatus() {
        return projectService.getProjectsByStatus();
    }

    // Durée moyenne des projets
    @GetMapping("/averageDuration")
    public Map<String, Object> getAverageDuration() {
        return projectService.getAverageProjectDuration();
    }

    // Progrès
    @GetMapping("/progress/{idProject}")
    public ResponseEntity<?> getProjectProgress(@PathVariable Long idProject) {
        int progress = projectService.getProjectProgress(idProject);
        if (progress == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Projet non trouvé");
        }
        return ResponseEntity.ok(Map.of("projectId", idProject, "progress", progress + "%"));
    }

    @GetMapping("/geoAndRemaining/{idProject}")
    public ResponseEntity<Map<String, Object>> getGeoAndRemaining(@PathVariable Long idProject) {
        Map<String, Object> data = projectService.getGeoAndRemainingInfo(idProject);
        if (data == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(data);
    }


}
