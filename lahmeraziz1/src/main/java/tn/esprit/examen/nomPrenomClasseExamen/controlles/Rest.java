package tn.esprit.examen.nomPrenomClasseExamen.controlles;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Facture;
import tn.esprit.examen.nomPrenomClasseExamen.services.Servicelahmer;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/Rest")

public class Rest {
    @Autowired
    private Servicelahmer servicelahmer;

    @PostMapping("/ajouterFacture")
    public Facture ajouterFacture(@RequestBody Facture facture) {
        return servicelahmer.ajouterFacture(facture);
    }
    @PutMapping("/updateFacture")
    public Facture updateFacture(@RequestBody Facture facture) {
        return servicelahmer.updateFacture(facture);
    }
    @GetMapping("/getAllFacture")
    public List<Facture> retrieveAllFacture() {
        return servicelahmer.retrieveAllFacture();
    }
    @GetMapping("/findById/{idF}")
    public Facture retrieveById(@PathVariable long idF) {
        return servicelahmer.retrieveFactureById(idF);
    }

    @DeleteMapping("/deleteE/{idF}")
    public void deleteFactureById(@PathVariable long idF) {
        servicelahmer.deleteFactureById(idF);
    }


}
