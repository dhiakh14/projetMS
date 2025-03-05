import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/servicesAziz/services';  // Import du service
import { Facture } from 'src/app/servicesAziz/models'; // Import du modèle Facture
import * as XLSX from 'xlsx';
import { Router } from '@angular/router';

@Component({
  selector: 'app-facture-list',
  templateUrl: './facture-list.component.html',
  styleUrls: ['./facture-list.component.css']
})
export class FactureListComponent implements OnInit {
  factures: Facture[] = [];  
  filteredFactures: Facture[] = [];  
  loading: boolean = false; 
  error: string | null = null; 
  selectedEtat: string = 'ALL';
  searchNumber: string = '';
  page: number = 1;
  limit: number = 10;
  totalPages: number = 0;


  constructor(private restService: RestService, private router: Router) {}

  ngOnInit(): void {
    this.loadFactures(); // Charger les factures à l'initialisation
  }

  loadFactures(): void {
    this.loading = true;  // Activation du chargement
    this.restService.retrieveAllFacture().subscribe(
      (data: Facture[]) => {
        this.factures = data; 
        this.filteredFactures = data; // Initialisation des factures filtrées
        this.loading = false;
      },
      (error) => {
        this.error = 'Erreur lors du chargement des factures'; 
        this.loading = false;
      }
    );
  }

  searchFactures(): void {
    if (this.searchNumber.trim() !== '') {
      this.filteredFactures = this.factures.filter(
        facture => facture.number?.toString().includes(this.searchNumber)
      );
    } else {
      this.filteredFactures = this.factures;  // Reset to all factures if no search term
    }
  }

  deleteFacture(idF: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette facture ?')) {
      this.restService.deleteFactureById({ idF }).subscribe(
        () => {
          // Mise à jour de la liste après suppression
          this.factures = this.factures.filter(facture => facture.idF !== idF);
          this.filteredFactures = this.filteredFactures.filter(facture => facture.idF !== idF);
          alert('Facture supprimée avec succès.');
        },
        (error) => {
          this.error = `Erreur lors de la suppression de la facture: ${error.message || error}`;
          alert(this.error);
        }
      );
    }
  }

  filterFactures(): void {
    if (this.selectedEtat === 'ALL') {
      this.filteredFactures = this.factures; 
    } else {
      this.filteredFactures = this.factures.filter(
        facture => facture.etatFacture === this.selectedEtat
      );
    }
  }

  addFacture(): void {
    this.router.navigate(['/addfacture']); 
  }

  showDetails(idF: number): void {
    this.router.navigate(['/facture-details', idF]); // Navigate to details page
  }

exportFacturesToExcel(): void {
  if (!this.filteredFactures || this.filteredFactures.length === 0) {
    alert("Aucune facture à exporter !");
    return;
  }

  // Convertir les données en un format compatible avec Excel
  const factureData = this.filteredFactures.map(facture => ({
    Numéro: facture.number || 'N/A',
    Montant: facture.montant !== undefined ? facture.montant : 0,
    État: facture.etatFacture || 'N/A',
    "Date d'Émission": facture.dateemission ? new Date(facture.dateemission).toLocaleDateString() : 'N/A',
    "Date d'Échéance": facture.dateecheance ? new Date(facture.dateecheance).toLocaleDateString() : 'N/A'
  }));

  // Création d'un fichier Excel
  const worksheet = XLSX.utils.json_to_sheet(factureData);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "Factures");

  // Génération du fichier Excel et téléchargement
  XLSX.writeFile(workbook, 'Liste_Factures.xlsx');
  
  alert('Fichier Excel exporté avec succès ! 🎉');
}

 // Méthode pour changer de page
 changePage(page: number): void {
  this.page = page;
  this.loadFactures();
}
}
