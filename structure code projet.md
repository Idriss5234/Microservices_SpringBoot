Voici une description des dossiers de notre structure microservices dans micro-service/src/main/java

1. **Controllers** : Contient les contrôleurs gérant les requêtes HTTP (API REST).
2. **Model** : Définit les classes de données et les entités du domaine.
3. **Repository** : Implémente l’accès aux données, souvent avec JPA ou MongoDB.
4. **Services** : Contient la logique métier et les traitements appliqués aux données avant de les renvoyer au contrôleur.


Et on a ajouté aussi un microservice Orchestrateur qui se compose de:
1. **Controllers** : Contient les contrôleurs gérant les requêtes HTTP (API REST).
2. **Services** : Contient la logique métier d'orchestration, c'est-à-dire la coordination des appels aux autres microservices pour assurer le bon déroulement du processus global (validation d’une commande en interagissant avec le panier).
