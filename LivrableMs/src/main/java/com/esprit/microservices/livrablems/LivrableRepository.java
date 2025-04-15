package com.esprit.microservices.livrablems;

import com.esprit.microservices.livrablems.entities.Livrable;
import com.esprit.microservices.livrablems.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Long> {
    Livrable findByTitleContainingIgnoreCase(String title);

    List<Livrable> findByType(String type);

    long countByStatus(Status status);


    int countByProjectNameAndStatus(String projectName, Status status);

    int countByProjectName(String projectName);

    // Custom query for advanced filtering
    @Query("SELECT l FROM Livrable l WHERE " +
            "(l.status = :status OR :status IS NULL) AND " +
            "(l.projectName = :projectName OR :projectName IS NULL) AND " +
            "(l.due_date BETWEEN :fromDate AND :toDate OR (:fromDate IS NULL AND :toDate IS NULL))")
    List<Livrable> filterLivrables(@Param("status") Status status,
                                   @Param("projectName") String projectName,
                                   @Param("fromDate") Date fromDate,
                                   @Param("toDate") Date toDate);

    @Query("SELECT AVG(DATEDIFF(l.due_date, l.createdAt)) FROM Livrable l " +
            "WHERE l.projectName = :projectName AND l.status = :status")
    Double findAverageTimeToCompletion(@Param("projectName") String projectName,
                                       @Param("status") Status status);

    @Query("SELECT l FROM Livrable l WHERE l.projectName = :projectName AND l.due_date <= :endDate ORDER BY l.due_date ASC")
    List<Livrable> findUpcomingDeadlines(@Param("projectName") String projectName, @Param("endDate") Date endDate);


    List<Livrable> findByProjectName(String projectName);


    @Query("SELECT l.status, COUNT(l) FROM Livrable l WHERE l.projectName = :projectName GROUP BY l.status")
    List<Object[]> countLivrablesByStatus(@Param("projectName") String projectName);


}

