package com.esprit.microservices.livrablems;

import com.esprit.microservices.livrablems.entities.Livrable;
import com.esprit.microservices.livrablems.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Long> {
    Livrable findByTitleContainingIgnoreCase(String title);

    List<Livrable> findByType(String type);

    long countByStatus(Status status);


    int countByProjectNameAndStatus(String projectName, Status status);

    int countByProjectName(String projectName);
}
