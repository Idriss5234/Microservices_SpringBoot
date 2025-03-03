# Microservices avec Orchestrateur et Processus Saga

Ce projet implémente une architecture de microservices utilisant un orchestrateur pour gérer les transactions distribuées via un processus Saga. Contrairement à la chorégraphie basée sur RabbitMQ, ici, un service dédié **Orchestrateur** est responsable de la coordination des différentes étapes de la transaction.

---

## 📋 Sommaire

1. [Outils et versions](#outils-et-versions)
2. [Architecture](#architecture)
3. [Installation](#installation)
4. [Configuration](#configuration)
5. [Exécution du projet](#exécution-du-projet)
6. [Tests](#tests)

---

## 🛠️ Outils et versions

Voici la liste des outils utilisés dans ce projet avec leurs versions exactes :

- **Java** : OpenJDK 17
- **Spring Boot** : 2.7.5
- **Docker** : 26.0.0
- **MySQL** : 8.0.40 (MySQL Community Server - GPL)
- **Maven** : 3.9.8

---

## ⚙️ Architecture

Ce projet comprend quatre microservices interconnectés :

1. **Utilisateur** : Gestion des utilisateurs et des authentifications.
2. **Panier** : Gestion des articles ajoutés au panier par les utilisateurs.
3. **Commande** : Gestion des commandes passées par les utilisateurs.
4. **Paiement** : Gestion des paiements associés aux commandes.

### Communication entre services

- **L'Orchestrateur** est un service Spring Boot qui coordonne les étapes du processus Saga en appelant directement les API REST des différents microservices.
- Il assure la gestion des transactions distribuées en contrôlant l'ordre des opérations et en exécutant des actions compensatoires en cas d'échec.

### Gestion des transactions Saga

1. **Début de Saga** : L'utilisateur passe une commande, et l'orchestrateur initie le processus.
2. **Étapes intermédiaires** :
   - Vérification de la disponibilité du panier.
   - Mise à jour du panier si les articles sont disponibles.
   - Récupération du prix total du panier.
   - Création de la commande avec les informations récupérées.
3. **Compensation** : Si une étape échoue, l'orchestrateur exécute des actions compensatoires (exemple : annulation de la mise à jour du panier).

---

## 🚀 Installation

### Prérequis

1. Installer **Java 17** ou une version supérieure.
2. Installer **Docker**.
3. Installer **Maven**.
4. Installer **Spring Boot**.
5. Installer **MySQL 8.0.40**.

### Étapes

1. Clonez ce dépôt :

   ```sh
   git clone https://github.com/Idriss5234/Microservices_SpringBoot.git
   cd Microservices_SpringBoot
   ```

2. Construisez les microservices avec Maven :

   ```sh
   mvn clean install
   ```

3. Configurez la base de données MySQL pour chaque service. Exemple de création d'une base pour le service Panier :

   ```sql
   CREATE DATABASE panier_db;
   ```

---

## 🔧 Configuration

### Fichiers de configuration

Chaque microservice possède son propre fichier `application.yml`. Voici un exemple pour le service Panier :

spring.application.name=micro-service-panier
server.servlet.context-path=/
server.port=8091
spring.datasource.url = jdbc:mysql://localhost:3306/panier_db
createDatabaseIfNotExist=true
spring.datasource.username = root
spring.datasource.password =password
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql = true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.cloud.discovery.enabled=false

### Assurez-vous que les variables suivantes sont définies pour tous les services :

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

---

## ▶️ Exécution du projet

1. Démarrez chaque microservice :

   ```sh
   cd micro-service-panier
   mvn spring-boot:run
   ```

   ```sh
   cd micro-service-commande
   mvn spring-boot:run
   ```

   ```sh
   cd micro-service-utilisateur
   mvn spring-boot:run
   ```

   ```sh
   cd orchestrateur-service
   mvn spring-boot:run
   ```

2. L'Orchestrateur est accessible via son API REST à l'adresse :

   ```sh
   http://localhost:8090/api/orchestrateur
   ```

---

## 🧪 Tests

### Tests manuels avec Postman

Scénarios disponibles :

1. Effectuez les tests pour vérifier les fonctionnalités principales (CRUD).
2. Testez les scénarios Saga :
   - **Succès** : Simulez un workflow complet où tous les services fonctionnent correctement.
   - **Échec** : Simulez un échec dans l'un des services (exemple : rupture de stock dans Panier).

### Vérification avec les logs

Surveillez les logs de l'Orchestrateur pour vérifier l'exécution des différentes étapes et la gestion des erreurs.

