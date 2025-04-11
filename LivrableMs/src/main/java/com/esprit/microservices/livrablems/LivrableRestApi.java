package com.esprit.microservices.livrablems;

import com.esprit.microservices.livrablems.entities.Livrable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livrables")
public class LivrableRestApi {
    @Autowired
    private LivrableService livrableService;

    @RequestMapping
    public ResponseEntity<List<Livrable>>getAllLiv(){
        return ResponseEntity.ok(livrableService.getAllLiv());
    }



}
