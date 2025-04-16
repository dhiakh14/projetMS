**Project Management Microservice**

Description :
Ce microservice gère les projets dans une plateforme de gestion. Il offre des fonctionnalités telles que l'ajout, la mise à jour, la suppression de projets, ainsi que des statistiques sur la durée des projets, leur progression et leur géolocalisation via une API externe.

Fonctionnalités
Gestion des projets :

Ajouter un projet
Mettre à jour un projet
Supprimer un projet
Récupérer tous les projets
Récupérer un projet par son identifiant

Statistiques :
Récupérer des statistiques sur les projets par statut (en cours, terminé, retardé)
Calculer la durée moyenne des projets (en mois et jours)
Progrès des projets :
Calculer la progression d'un projet selon son statut (50% pour en cours, 100% pour terminé, 25% pour retardé)
Géolocalisation et jours restants :
Utilisation de l'API Nominatim pour obtenir la latitude, la longitude et le nom de la ville associée à un projet
Calcul des jours restants avant la fin du projet

Prérequis
Java 11 ou supérieur
Spring Boot 2.x
Maven
Base de données relationnelle (ex. MySQL, PostgreSQL)

Installation
Clonez ce repository :
git clone https://github.com/dhoakh14/projetMS.git


Installez les dépendances avec Maven :
mvn clean install

Configurez la base de données dans le fichier application.properties ou application.yml selon vos préférences.

Lancez l'application Spring Boot :
mvn spring-boot:run

API Endpoints
Ajouter un projet
URL : /project Méthode : POST

Corps de la requête :
{
  "name": "Nom du projet",
  "description": "Description du projet",
  "startDate": "yyyy-MM-dd",
  "endDate": "yyyy-MM-dd",
  "status": "ON_GOING"
}

Mettre à jour un projet
URL : /project/{id} Méthode : PUT

Corps de la requête : Identique à l'ajout d'un projet

Supprimer un projet
URL : /project/{id} Méthode : DELETE

Récupérer tous les projets
URL : /project Méthode : GET

Récupérer un projet par ID
URL : /project/{id} Méthode : GET

Durée moyenne des projets
URL : /project/averageDuration Méthode : GET

Statistiques des projets par statut
URL : /project/statusStats Méthode : GET

Progrès d'un projet
URL : /project/progress/{id} Méthode : GET

Informations géographiques et jours restants
URL : /project/geoAndRemaining/{id} Méthode : GET

Tests
Lancer l'application et vérifier que tous les endpoints sont accessibles via Postman ou un autre client HTTP.

Exécuter les tests unitaires :
mvn test

Contribuer
Fork ce repository
Créez une branche (git checkout -b feature/nouvelle-fonctionnalite)
Faites vos changements
Committez vos modifications (git commit -am 'Ajout de fonctionnalité')
Push vers la branche (git push origin feature/nouvelle-fonctionnalite)
Ouvrez une Pull Request

Auteurs
Bahraoui Abir - Développeur principal - (https://github.com/Abirbahraoui)

Collaborateurs - Collaborateurs du projet

License
Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.
