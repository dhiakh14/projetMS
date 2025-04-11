package com.esprit.microservices.livrablems;

import com.esprit.microservices.livrablems.entities.Livrable;
import com.esprit.microservices.livrablems.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LivrableService {
    @Autowired
    private LivrableRepository livrableRepository;

    public Livrable addLiv(Livrable livrable) {
        return livrableRepository.save(livrable);
    }

    public List<Livrable> getAllLiv() {
        return livrableRepository.findAll();
    }

    public Livrable getLivrableById(Long id) {
        return livrableRepository.findById(id).orElse(null);
    }
    // Dynamic calculation of completed_count
    private int calculateCompletedCount(String projectName) {
        // Count the livrables that have the status 'COMPLETED' for the same project
        return livrableRepository.countByProjectNameAndStatus(projectName, Status.Completed);
    }

    // Dynamic calculation of total_count
    private int calculateTotalCount(String projectName) {
        // Count all livrables for the same project name
        return livrableRepository.countByProjectName(projectName);
    }
    @Transactional
    public Livrable updateLivrable(Long livrableId, Livrable updatedLivrable) {
        // Step 1: Retrieve the existing livrable
        Livrable livrable = livrableRepository.findById(livrableId)
                .orElseThrow(() -> new IllegalArgumentException("Livrable not found"));

        // Step 2: Update the fields of the livrable based on the updatedLivrable
        if (updatedLivrable.getTitle() != null && !updatedLivrable.getTitle().isEmpty()) {
            livrable.setTitle(updatedLivrable.getTitle());
        }
        if (updatedLivrable.getProjectName() != null && !updatedLivrable.getProjectName().isEmpty()) {
            livrable.setProjectName(updatedLivrable.getProjectName());
        }
        if (updatedLivrable.getDescription() != null && !updatedLivrable.getDescription().isEmpty()) {
            livrable.setDescription(updatedLivrable.getDescription());
        }
        if (updatedLivrable.getDue_date() != null) {
            livrable.setDue_date(updatedLivrable.getDue_date());
        }
        if (updatedLivrable.getStatus() != null) {
            livrable.setStatus(updatedLivrable.getStatus());
        }


        // Step 3: Update the updatedAt field to the current system date
        livrable.setUpdatedAt(new Date());

        // Step 4: Recalculate completed_count if the status was updated
        if (updatedLivrable.getStatus() != null) {
            livrable.setCompleted_count(calculateCompletedCount(livrable.getProjectName()));
        }

        // Step 5: Save the updated livrable
        return livrableRepository.save(livrable);
    }
    public String deleteLivrable(Long id) {
        if (livrableRepository.findById(id).isPresent()) {
            livrableRepository.deleteById(id);
            return "Livrable supprimé";
        } else
            return "Livrable Not Found";
    }
    public List<Livrable> getLivrableByType(String type) {
        return livrableRepository.findByType(type);
    }
    public long getTotalLivrables() {
        return livrableRepository.count();
    }
    public long getCompletedLivrables() {
        return livrableRepository.countByStatus(Status.Completed);
    }
    public long getInProgressLivrables() {
        return livrableRepository.countByStatus(Status.InProgress);
    }

}


