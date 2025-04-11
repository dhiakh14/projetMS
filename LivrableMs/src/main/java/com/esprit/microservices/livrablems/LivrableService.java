package com.esprit.microservices.livrablems;

import com.esprit.microservices.livrablems.dto.ProjectStats;
import com.esprit.microservices.livrablems.entities.Livrable;
import com.esprit.microservices.livrablems.entities.Status;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LivrableService {
    @Autowired
    private LivrableRepository livrableRepository;

    @Transactional
    public Livrable createLivrable(Livrable livrable) {
        // Step 1: Set default status to IN_PROGRESS if not provided
        if (livrable.getStatus() == null) {
            livrable.setStatus(Status.InProgress);
        }

        // Step 2: Set createdAt to the current system date
        livrable.setCreatedAt(new Date());

        // Step 3: Set updatedAt to null
        livrable.setUpdatedAt(null);

        // Step 4: Validate required fields (title, project name, type, description, due_date)
        if (livrable.getTitle() == null || livrable.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required.");
        }

        if (livrable.getProjectName() == null || livrable.getProjectName().isEmpty()) {
            throw new IllegalArgumentException("Project name is required.");
        }

        if (livrable.getType() == null) {
            throw new IllegalArgumentException("Type is required.");
        }

        if (livrable.getDescription() == null || livrable.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description is required.");
        }

        if (livrable.getDue_date() == null) {
            throw new IllegalArgumentException("Due date is required.");
        }

        // Step 5: Save the livrable first
        Livrable savedLivrable = livrableRepository.save(livrable);

        // Step 6: Check if it's the first livrable for the project
        int totalCount = livrableRepository.countByProjectName(savedLivrable.getProjectName());
        savedLivrable.setTotal_count(totalCount);

        // Adjust completed count based on status
        int completedCount = livrableRepository.countByProjectNameAndStatus(savedLivrable.getProjectName(), Status.Completed);
        savedLivrable.setCompleted_count(completedCount);

        // If it's the first livrable, and the status is completed, set counts appropriately
        if (totalCount == 1) {
            if (savedLivrable.getStatus() == Status.Completed) {
                savedLivrable.setCompleted_count(1);
            } else {
                savedLivrable.setCompleted_count(0);
            }
        }

        // Step 7: Save the updated livrable with correct counts
        return livrableRepository.save(savedLivrable);
    }




    public List<Livrable> getAllLivrables() {
        List<Livrable> livrables = livrableRepository.findAll();
        Date currentDate = new Date();

        for (Livrable l : livrables) {
            if (l.getDue_date() != null && l.getDue_date().before(currentDate) && l.getStatus() != Status.Completed) {
                l.setStatus(Status.Late);
                livrableRepository.save(l); // Mise à jour en base
            }
        }

        return livrables;
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
    public void deleteLivrable(Long id) {
        // Vérifier si le livrable existe avant de le supprimer
        Livrable livrable = livrableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livrable not found"));

        // Récupérer le nom du projet avant suppression
        String projectName = livrable.getProjectName();

        // Supprimer le livrable
        livrableRepository.deleteById(id);

        // Mettre à jour le nombre total de livrables pour ce projet
        int totalCount = livrableRepository.countByProjectName(projectName);

        // Mettre à jour tous les livrables restants avec le nouveau total
        List<Livrable> livrables = livrableRepository.findByProjectName(projectName);
        for (Livrable l : livrables) {
            l.setTotal_count(totalCount);
            livrableRepository.save(l);
        }
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

    // Method to filter livrables based on stat/ projectN /date
    public List<Livrable> filterLivrables(Status status, String projectName, Date fromDate, Date toDate) {
        return livrableRepository.filterLivrables(status, projectName, fromDate, toDate);
    }

    // Method to calculate the percentage of completed livrables, average time to completion, and upcoming deadlines
    public ProjectStats getProjectStats(String projectName) {
        // Use existing methods to get the counts
        int total = calculateTotalCount(projectName);
        int completed = calculateCompletedCount(projectName);

        // Calculate the completion percentage
        double completionPercentage = (total > 0) ? ((double) completed / total) * 100 : 0;

        // Calculate the average time to completion for completed livrables
        Double avgTime = findAverageTimeToCompletion(projectName, Status.Completed);

        // Get upcoming deadlines for the next 7 days
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Get the date for 7 days from today
        Date endDate = calendar.getTime();
        List<Livrable> upcomingDeadlines = findUpcomingDeadlines(projectName, endDate);

        // Create and return the ProjectStats object
        return new ProjectStats(completionPercentage, avgTime, upcomingDeadlines);
    }
    // Use the repository method to get the average time to completion for completed livrables
    private Double findAverageTimeToCompletion(String projectName, Status status) {
        return livrableRepository.findAverageTimeToCompletion(projectName, status);
    }

    // Use the repository method to get upcoming deadlines within the next N days
    private List<Livrable> findUpcomingDeadlines(String projectName, Date endDate) {
        return livrableRepository.findUpcomingDeadlines(projectName, endDate);
    }

    public List<Livrable> getLivrableByProjectName(String projectName) {
        return livrableRepository.findByProjectName(projectName);
    }

    // Generate PDF for a specific Livrable
    public ByteArrayOutputStream generateLivrablePdf(Livrable livrable) throws IOException {
        // Create a new PDF document
        PDDocument document = new PDDocument();

        // Create a new page
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a content stream to write content to the PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set font and size
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);

        // Write the Livrable information to the PDF
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 750); // Set starting position

        contentStream.showText("Livrable Details");
        contentStream.newLineAtOffset(0, -20);

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.showText("Title: " + livrable.getTitle());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Project: " + livrable.getProjectName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Description: " + livrable.getDescription());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Due Date: " + livrable.getDue_date());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Status: " + livrable.getStatus());

        contentStream.endText();
        contentStream.close();

        // Convert PDF to ByteArrayOutputStream for downloading
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return outputStream;
    }

}


