import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectControllerService } from 'src/app/servicesAbir/services';

@Component({
  selector: 'app-project-location',
  templateUrl: './project-location.component.html',
  styleUrls: ['./project-location.component.css']
})
export class ProjectLocationComponent implements OnInit {

  idProject!: number;
  geoData: { [key: string]: any } | null = null;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private projectService: ProjectControllerService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.idProject = +id;
        this.getGeoAndRemainingData();
      } else {
        this.errorMessage = 'Aucun identifiant de projet trouvé dans l’URL';
      }
    });
  }

  getGeoAndRemainingData(): void {
    this.projectService.getGeoAndRemaining({ idProject: this.idProject })
      .subscribe({
        next: (data) => {
          this.geoData = data;
          console.log("Résultat Geo + Remaining : ", data);
        },
        error: (err) => {
          this.errorMessage = 'Erreur lors de la récupération des données';
          console.error(err);
        }
      });
  }

}