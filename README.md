# 🏗️ Microservice - Gestion de Projets de Construction

Ce microservice fait partie d'un système plus large de gestion de projets de construction. Il permet la création, gestion, suivi, prédiction de statut et géolocalisation des projets via des APIs RESTful.

## 🚀 Fonctionnalités principales

- 🔧 **CRUD** complet sur les projets
- 📊 **Statistiques** :
  - Nombre de projets par statut (`ON_GOING`, `COMPLETED`, `DELAYED`)
  - Durée moyenne des projets
- 🔮 **Prédiction du statut** via un modèle de Machine Learning externe (Flask)
- 🌍 **Géolocalisation** des projets via l'API Nominatim (OpenStreetMap)
- ⏳ Calcul des **jours restants** avant la fin d’un projet
- 📈 Suivi du **progrès du projet**

## 🧱 Stack Technique

- **Java 17**
- **Spring Boot**
- **REST API**
- **JPA / Hibernate**
- **PostgreSQL ou MySQL**
- **Flask** (service externe pour la prédiction)
- **OpenStreetMap Nominatim API** (pour la géolocalisation)

## 📁 Structure du code

### 📦 `ProjectService.java`

Ce fichier contient la logique métier du microservice, avec notamment :

#### CRUD :
- `addProject(Project project)`
- `updateProject(Long id, Project project)`
- `deleteProject(Long id)`
- `getAll()`
- `findProjectById(Long id)`

#### Statistiques :
- `countProjectsByStatus(Status status)`
- `getProjectsByStatus()`
- `getAverageProjectDuration()`

#### Avancée :
- `getProjectProgress(Long id)`

#### IA & Géolocalisation :
- `predictStatus(Project project)`  
Appelle un modèle Python hébergé sur `localhost:5000/predict_status`  
- `getGeoAndRemainingInfo(Long id)`  
Appelle l’API [Nominatim](https://nominatim.org/release-docs/develop/api/Search/) pour obtenir la latitude, la longitude et le nom de l’endroit du projet.

## 📡 Exemple d'appel à l'API Flask (prédiction)

```json
POST http://localhost:5000/predict_status
Content-Type: application/json

{
  "name": "Projet A",
  "description": "Construction d’un immeuble",
  "startDate": "2023-01-01",
  "endDate": "2024-01-01",
  "status": "ON_GOING"
}
```

Réponse :
```json
{
  "predicted_status": "COMPLETED"
}
```

## 🔐 Sécurité

- Envisage d'ajouter **Spring Security** si le microservice est exposé à des utilisateurs externes.
- Les appels aux services externes doivent idéalement être sécurisés et résilients (avec timeout, retry, etc.)

## 📌 À faire

- ✅ Ajouter des tests unitaires et d’intégration
- ✅ Externaliser les URL dans `application.properties`
- 🔲 Ajouter la gestion des erreurs avec `@ControllerAdvice`
- 🔲 Ajouter Swagger pour la documentation de l'API

## 🧪 Test

Lancement des tests :
```bash
./mvnw test
```

## 👨‍💻 Auteur

- 🧑‍💻 Projet développé par Abir et l'équipe du module MSA (Microservices Architecture)
- 📅 Avril 2025
