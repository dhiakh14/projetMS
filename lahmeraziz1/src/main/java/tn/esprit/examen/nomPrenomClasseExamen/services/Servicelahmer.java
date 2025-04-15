package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Facture;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.IFactureRespository;

import java.util.List;

@org.springframework.stereotype.Service
@Data
public class Servicelahmer {
    @Autowired
    private IFactureRespository factureRespository ;



    public Facture ajouterFacture(Facture f){
         return factureRespository.save(f);
    }

    public Facture updateFacture(Facture facture) {
        return factureRespository.save(facture);
    }
    public List<Facture> retrieveAllFacture() {
        return factureRespository.findAll();
    }

    public Facture retrieveFactureById(long idF) {
        return factureRespository.findById(idF).orElse(null);
    }

    public void deleteFactureById(long idF) {
        factureRespository.deleteById(idF);
    }







}
