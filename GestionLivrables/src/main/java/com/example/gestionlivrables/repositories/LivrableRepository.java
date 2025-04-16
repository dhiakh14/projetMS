package com.example.gestionlivrables.repositories;

import com.example.gestionlivrables.entities.Livrable;
import com.example.gestionlivrables.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface LivrableRepository extends JpaRepository<Livrable,Long> {
    Livrable findByTitle(String title);

    int countByProjectName(String projectName);

    int countByProjectNameAndStatus(String projectName, Status status);

    List<Livrable> findByFormat(String format);

    List<Livrable> findByStatus(Status status);

    List<Livrable> findByProjectName(String projectName);

    long countByStatus(Status status);

    @Query("SELECT COUNT(l) FROM Livrable l WHERE l.due_date < CURRENT_TIMESTAMP AND l.status NOT IN ('COMPLETED', 'APPROVED')")
    long countLateLivrables();

    @Query("SELECT l.status, COUNT(l) FROM Livrable l GROUP BY l.status")
    List<Object[]> countByStatus();

    @Query("SELECT FUNCTION('MONTH', l.createdAt), COUNT(l) FROM Livrable l GROUP BY FUNCTION('MONTH', l.createdAt)")
    List<Object[]> countCreatedByMonth();

    @Query("SELECT l.projectName, COUNT(l) FROM Livrable l GROUP BY l.projectName")
    List<Object[]> countByProject();
}

