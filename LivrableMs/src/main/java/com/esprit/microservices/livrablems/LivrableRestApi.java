package com.esprit.microservices.livrablems;

import com.esprit.microservices.livrablems.dto.ProjectStats;
import com.esprit.microservices.livrablems.dto.StatusStatsDTO;
import com.esprit.microservices.livrablems.entities.Livrable;
import com.esprit.microservices.livrablems.entities.Status;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livrables")
public class LivrableRestApi {

    @Autowired
    private LivrableService livrableService;

    //create
    @PostMapping("/add")
    public ResponseEntity<Livrable> addLiv(@RequestBody Livrable livrable) {
        return ResponseEntity.ok(livrableService.createLivrable(livrable));
    }

    //get
    @GetMapping("/all")
    public List<Livrable> getAllLivrables() {
        return livrableService.getAllLivrables();
    }

    //update
    @PutMapping("/update/{livrableId}")
    public ResponseEntity<Livrable> updateLivrable(
            @PathVariable Long livrableId,
            @RequestBody Livrable updatedLivrable) {
        Livrable updated = livrableService.updateLivrable(livrableId, updatedLivrable);
        return ResponseEntity.ok(updated);
    }

    //delete
    @DeleteMapping("/delete/{idLivrable}")
    public void deleteLivrable(@PathVariable Long idLivrable) {
        livrableService.deleteLivrable(idLivrable);
    }


    @GetMapping("/type/{type}")
    public ResponseEntity<List<Livrable>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(livrableService.getLivrableByType(type));
    }

    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotalLivrables() {
        return ResponseEntity.ok(livrableService.getTotalLivrables());
    }

    @GetMapping("/count/completed")
    public ResponseEntity<Long> getCompletedLivrables() {
        return ResponseEntity.ok(livrableService.getCompletedLivrables());
    }

    @GetMapping("/count/inprogress")
    public ResponseEntity<Long> getInProgressLivrables() {
        return ResponseEntity.ok(livrableService.getInProgressLivrables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livrable> getLivrableById(@PathVariable Long id) {
        Livrable livrable = livrableService.getLivrableById(id);
        return livrable != null ? ResponseEntity.ok(livrable) : ResponseEntity.notFound().build();
    }

    // Endpoint for advanced filtering
    @GetMapping("/livrables/filter")
    public List<Livrable> filterLivrables(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return livrableService.filterLivrables(status, projectName, fromDate, toDate);
    }

    //  fetch project stats
    @GetMapping("/stats/{projectName}")
    public ProjectStats getProjectStats(@PathVariable String projectName) {
        return livrableService.getProjectStats(projectName);
    }

    // fetch livrables by project
    @GetMapping("/by-project/{projectName}")
    public List<Livrable> getLivrableByProjectName(@PathVariable String projectName) {
        return livrableService.getLivrableByProjectName(projectName);
    }

    @GetMapping("/{livrableId}/download-pdf")
    public ResponseEntity<byte[]> downloadLivrablePdf(@PathVariable Long livrableId) {
        try {
            // Fetch the Livrable data by its ID
            Livrable livrable = livrableService.getLivrableById(livrableId);

            // Generate the PDF document
            ByteArrayOutputStream pdf = livrableService.generateLivrablePdf(livrable);

            // Prepare the response with the generated PDF
            byte[] pdfContent = pdf.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=livrable_" + livrableId + ".pdf");

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error generating PDF.".getBytes(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/statuses/{projectName}")
    public StatusStatsDTO getStatusStats(@PathVariable String projectName) {
        return livrableService.getStatusStats(projectName);
    }


    // Mettre à jour uniquement le statut
    @PutMapping("/{id}/status")
    public ResponseEntity<Livrable> updateStatus(@PathVariable Long id, @RequestBody Livrable livrable) {
        // Updating status based on the ID and new status from request body
        Livrable updatedLivrable = livrableService.updateStatus(id, livrable.getStatus());
        return ResponseEntity.ok(updatedLivrable);
    }




}