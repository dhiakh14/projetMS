📦 Microservice de Gestion des Livrables
Le microservice de gestion des livrables est un composant clé d'un système modulaire de gestion de projets, destiné à encadrer le cycle de vie des documents produits dans le cadre d’un projet (rapports, fichiers techniques, documents contractuels, etc.). Il permet de créer, consulter, suivre, modifier et supprimer les livrables tout en assurant leur suivi d'avancement.

🛠️ Technologies utilisées
Ce microservice est développé en utilisant :

Java 17+

Spring Boot

Spring Data JPA

REST API

Maven

Base de données : MySQL 

FeignClient : pour la communication avec le microservice de gestion de projets

Lombok (facultatif pour réduire le boilerplate)

iText : pour la génération future de rapports PDF

Docker (optionnel pour le déploiement)

Eureka (dans l’architecture microservices globale)

📌 Fonctionnalités principales
🔄 CRUD des livrables : création, lecture, mise à jour, suppression.

📊 Suivi des statuts : chaque livrable a un statut comme InProgress, Completed, Late, Rejected, Approved.

⏰ Détection automatique des retards : un livrable dépassant sa date d’échéance et non complété est automatiquement marqué comme Late.

🔗 Liaison avec un projet : chaque livrable est lié à un projet existant via le microservice de gestion de projets.

📈 Calcul automatique de progression : le backend calcule le pourcentage d'avancement estimé en fonction des dates et du statut.

📄 Export PDF (prévu) : les données des livrables pourront être exportées sous forme de rapports PDF.

📦 Mise à jour des totaux : chaque ajout ou suppression de livrable met à jour les compteurs completed_count et total_count dans le projet.

🔗 Intégration avec d'autres microservices
Le microservice utilise un FeignClient (ProjectClient) pour interagir avec le microservice de gestion de projets. Cela permet de :

Récupérer les détails d’un projet à partir de son nom.

Vérifier l’existence du projet avant la création d’un livrable.

Mettre à jour les statistiques d’un projet (comme le nombre total de livrables).

📥 Endpoints REST disponibles
🔹 GET
/livrables : Retourne tous les livrables.

/livrables/{id} : Retourne un livrable par son identifiant.

/livrables/title/{title} : Recherche d’un livrable par titre.

/livrables/status/{status} : Filtrage par statut (InProgress, Completed, etc.).

/livrables/format/{format} : Recherche par format (PDF, Word, Excel...).

/livrables/project/{projectName} : Liste des livrables associés à un projet spécifique.

/livrables/grouped-by-project : Liste groupée des livrables par projet.

🔹 POST
/livrables : Crée un nouveau livrable. Ce livrable est automatiquement lié à un projet et son statut initial est calculé selon la date d’échéance.

🔹 PUT
/livrables/{id} : Met à jour les informations d’un livrable.

🔹 DELETE
/livrables/{id} : Supprime un livrable et met à jour les totaux du projet associé.

📈 Calculs automatiques
Chaque fois qu’un livrable est consulté, le backend renvoie :

Le pourcentage de progression estimé en fonction du temps écoulé par rapport à la date d’échéance.

Un indicateur booléen s’il est en retard (true si la date de fin est dépassée).

Un indicateur booléen s’il est terminé (true si son statut est Completed).

Ces données sont encapsulées dans un LivrableDTO.

⚙️ Exemple de logique métier
Lors de la création ou mise à jour d’un livrable :

Si la dueDate est dépassée et que le statut n’est pas Completed, alors le statut est automatiquement mis à Late.

Si un livrable est supprimé, le nombre total de livrables et ceux terminés dans le projet sont décrémentés.

En cas de mise à jour de statut vers Completed, le backend met à jour dynamiquement les totaux associés dans le projet.

📦 Déploiement
Prérequis
Java 17+

Maven

Base de données (MySQL/PostgreSQL)

(optionnel) Eureka Server

Commandes de build et d’exécution
bash
Copier
Modifier
# Compilation
mvn clean install

# Lancement de l'application
mvn spring-boot:run
🔐 Sécurité (à intégrer)
Ce microservice peut être sécurisé à l’aide de :

JWT pour l’authentification

OAuth2 pour les interactions multi-clients

Rôles/permissions pour contrôler l’accès aux endpoints (ADMIN, RÉDACTEUR, VALIDATEUR...)

🧪 Tests
Des tests unitaires peuvent être développés avec :

JUnit et Mockito pour la logique métier

MockMvc pour tester les contrôleurs REST

📚 Évolutions futures prévues
📄 Génération de rapports PDF des livrables

🔔 Système de notifications automatiques pour les retards, rejets et validations

🔁 Intégration complète avec le module de workflows (revue/validation/approbation)

📊 Tableaux de bord statistiques pour le suivi des livrables par projet

👨‍💻 Auteur
Ce microservice a été développé dans le cadre du projet PIDEV - Application de gestion des projets pour un bureau d’ingénierie.

Auteur : [Amira Zaier]
