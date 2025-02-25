                                                                # Déploiement des Microservices avec Docker et Docker Compose

## Introduction

Dans ce projet, nous allons déployer plusieurs microservices pour gérer une application complète. L'architecture de notre application repose sur **4 microservices** et plusieurs services associés comme **RabbitMQ**, **Redis**, et des bases de données MySQL pour chaque microservice.

Tous ces services seront lancés dans des **conteneurs Docker** et communiquent entre eux au sein d'un même réseau Docker.

## Architecture de l'Application

### Microservices

L'application se compose des microservices suivants :

- **Microservice Utilisateur** : Gestion des utilisateurs
- **Microservice Panier** : Gestion des paniers d'achat
- **Microservice Commande** : Gestion des commandes
- **Microservice Paiement** (Non implémenté)

### Services Associés

- **Bases de données MySQL** : Une base pour chaque microservice.
- **RabbitMQ** : Service de messagerie pour la communication entre les microservices.
- **Redis** : Service pour la gestion des sessions et le caching.

### Réseau Docker

Tous ces services seront lancés au sein de **conteneurs Docker** sur un **réseau Docker commun** pour faciliter leur communication.

## Utilisation de Docker Compose

Docker Compose permet de gérer plusieurs conteneurs Docker en même temps. Il définit les services, les réseaux et les volumes dans un fichier `docker-compose.yml`.

Nous utiliserons **Docker Compose** pour lancer tous les microservices et leurs services associés. Il existe un fichier `docker-compose.yml` à la racine de chaque microservice, mais un autre fichier global est aussi présent pour gérer le réseau, RabbitMQ, et Redis.

## Lancer les Microservices

### Étape 1 : Lancer tous les services avec Docker Compose

Pour démarrer tous les services en un seul appel, exécutez la commande suivante à la racine du projet :

```bash
docker-compose up -d
```

Cela va lancer tous les conteneurs dans des processus détachés.

### Étape 2 : Lancer chaque microservice individuellement

Si vous souhaitez lancer les microservices un par un, suivez ces étapes pour chaque répertoire de microservice :

1. **Microservice Utilisateur**

   ```bash
   cd micro-service-utilisateur
   docker-compose up -d
   ```

2. **Microservice Panier**

   ```bash
   cd micro-service-panier
   docker-compose up -d
   ```

3. **Microservice Commande**

   ```bash
   cd micro-service-commande
   docker-compose up -d
   ```

### Étape 3 : Attendre la préparation des microservices

Après avoir lancé les conteneurs, il vous faudra quelques minutes pour que tous les services soient entièrement démarrés et prêts à recevoir des requêtes.

## Tester les Microservices

### Utiliser Postman pour tester

Une fois les microservices lancés, vous pouvez tester les points de terminaison avec **Postman**. Par exemple, pour obtenir la liste des commandes, effectuez une requête GET sur l'URL suivante :

```
GET http://localhost:9092/Commandes
```

### Utiliser un navigateur pour tester

Vous pouvez aussi tester directement dans votre navigateur en accédant à l'URL suivante :

```
http://localhost:9092/Commandes
```

### Passer une commande

Pour passer une commande en utilisant **Postman**, vous pouvez appeler l'URL suivante avec les bons paramètres :

```text
GET http://localhost:9092/test-saga/${user-id}/${panier-id}/${quantité}/
```

## Remarques et Prérequis

Avant de commencer, voici quelques prérequis et remarques importantes :

### Prérequis

- **Docker et Docker Compose** doivent être installés sur votre machine. Suivez ces liens pour les installer :

  - [Installation de Docker](https://docs.docker.com/engine/install/)
  - [Installation de Docker Compose](https://docs.docker.com/compose/install/standalone/)

- **Postman** doit être installé pour tester les microservices. Téléchargez-le ici :
  - [Télécharger Postman](https://www.postman.com/downloads/)

### Problèmes de Ports

Si l'un des ports est déjà utilisé sur votre machine ou indisponible, vous pouvez modifier les ports dans le fichier `docker-compose.yml`. Par exemple, si le port 9092 est occupé, vous pouvez le remplacer par un autre port (par exemple 7092) comme suit :

```yaml
ports:
  - "9092:8092" # Avant
```

Changez-le en :

```yaml
ports:
  - "7092:8092" # Après
```

Ensuite, relancez le `docker-compose.yml` pour appliquer les modifications.

---

## Conclusion

En suivant ce guide, vous serez en mesure de lancer l'ensemble des microservices de votre application et de tester leurs fonctionnalités à l'aide de **Docker** et **Docker Compose**. Vous avez aussi la possibilité d'ajuster les ports si nécessaire, et de tester les services avec **Postman** ou un navigateur.

N'oubliez pas d'installer **Docker**, **Docker Compose**, et **Postman** avant de commencer.
