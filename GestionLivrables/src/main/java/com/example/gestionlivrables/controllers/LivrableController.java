package com.example.gestionlivrables.controllers;

import com.example.gestionlivrables.dto.LivrableDTO;
import com.example.gestionlivrables.entities.Livrable;
import com.example.gestionlivrables.entities.Status;
import com.example.gestionlivrables.services.LivrableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Livrables")
@CrossOrigin(origins = "http://localhost:4200")
public class LivrableController {

    @Autowired
    private LivrableService livrableService;

    // CREATE
    @PostMapping("/add")
    public Livrable createLivrable(@RequestBody Livrable livrable) {

        return livrableService.createLivrable(livrable);
    }

    @PutMapping("/update/{idLivrable}")
    public Livrable updateLivrable(@RequestBody Livrable livrable, @PathVariable Long idLivrable) {
        return livrableService.updateLivrable(idLivrable, livrable);
    }

    @GetMapping("")
    public List<Livrable> getAllLivrables() {
        return livrableService.getAllLivrables();
    }
    @GetMapping("/getById/{idLivrable}")
    public Livrable getLivrableById(@PathVariable Long idLivrable) {
        return livrableService.getLivrableById(idLivrable);
    }
    @GetMapping("/getByTitle/{title}")
    public Livrable getLivrableByTitle(@PathVariable String title) {
        return livrableService.getLivrableByTitle(title);
    }
    @GetMapping("/getByFormat/{format}")
    public List<Livrable> getLivrableByFormat(@PathVariable String format) {
        return livrableService.getLivrableByFormat(format);
    }
    @GetMapping("/getByStatus/{status}")
    public List<Livrable> getLivrableByStatus(@PathVariable Status status) {
        return livrableService.getLivrableByStatus(status);
    }
    @GetMapping("/getByProject/{projectName}")
    public List<Livrable> getLivrableByProjectName(@PathVariable String projectName) {
        return livrableService.getLivrableByProjectName(projectName);
    }
    @DeleteMapping("/delete/{idLivrable}")
    public void deleteLivrable(@PathVariable Long idLivrable) {
        livrableService.deleteLivrable(idLivrable);
    }

    @GetMapping("/groupedByProject")
    public Map<String, List<Livrable>> getLivrablesGroupedByProject() {
        return livrableService.getLivrablesGroupedByProject();
    }


    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        byte[] pdfBytes = livrableService.generatePdfForLivrable(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("livrable_" + id + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/grouped-by-project")
    public ResponseEntity<Map<Long, List<Livrable>>> getGroupedLivrables() {
        return ResponseEntity.ok(livrableService.getLivrablesGroupedByProjectName());
    }





}
