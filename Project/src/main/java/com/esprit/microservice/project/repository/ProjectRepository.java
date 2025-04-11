package com.esprit.microservice.project.repository;

import com.esprit.microservice.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    // Compter les projets par statut
    @Query("SELECT p.status, COUNT(p) FROM Project p GROUP BY p.status")
    List<Object[]> countProjectsByStatus();

    // Calculer la durée moyenne des projets
    @Query("SELECT AVG(DATEDIFF(p.endDate, p.startDate)) FROM Project p")
    Double averageProjectDuration();
}
