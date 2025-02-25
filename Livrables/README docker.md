# Documentation Docker - Build & Push des Images de Microservices

Ce projet inclut la création, la construction et le push d'images Docker pour quatre microservices. Chaque microservice possède son propre fichier `Dockerfile`, permettant de générer une image Docker qui sera ensuite poussée vers Docker Hub.

## Architecture des Microservices

Nous avons quatre microservices dans cette application :

1. **Microservice Utilisateur** : Gestion des utilisateurs.
2. **Microservice Panier** : Gestion des paniers d'achat.
3. **Microservice Commande** : Gestion des commandes.
4. **Microservice Paiement** : (Non implémenté pour le moment, mais structure prévue).

### Images Docker

Chaque microservice a une image Docker spécifique que nous allons créer à partir de son `Dockerfile` et ensuite la pousser vers Docker Hub. Vous pouvez retrouver les `Dockerfile` de chaque microservice dans leurs répertoires respectifs.

## Prérequis

Avant de commencer, assurez-vous que Docker est installé sur votre machine. Vous pouvez le télécharger et l'installer ici :

- [Installation de Docker](https://docs.docker.com/engine/install/)

De plus, vous devez disposer d'un compte Docker Hub pour pouvoir pousser les images créées. Vous pouvez vous inscrire ici :

- [Docker Hub](https://hub.docker.com/)

## Étapes pour Créer et Pousser les Images Docker

### 1. Se Connecter à Docker Hub

Si vous n'êtes pas encore connecté à Docker Hub, ouvrez un terminal et exécutez la commande suivante pour vous connecter avec vos identifiants Docker Hub :

```
docker login
```

### 2. Créer les Images Docker pour chaque Microservice

#### a. Microservice Utilisateur

Accédez au répertoire du microservice utilisateur et construisez l'image Docker à partir du `Dockerfile` :

```bash
cd micro-service-utilisateur
docker build -t <votre-nom-utilisateur-docker-hub>/ms_utilisateur:latest .
```

#### b. Microservice Panier

Accédez au répertoire du microservice panier et construisez l'image Docker :

```bash
cd micro-service-panier
docker build -t <votre-nom-utilisateur-docker-hub>/ms_panier:latest .
```

#### c. Microservice Commande

Accédez au répertoire du microservice commande et construisez l'image Docker :

```bash
cd micro-service-commande
docker build -t <votre-nom-utilisateur-docker-hub>/ms_commande:latest .
```

#### d. Microservice Paiement (Non Implémenté)

Accédez au répertoire du microservice paiement et construisez l'image Docker :

```bash
cd micro-service-paiement
docker build -t <votre-nom-utilisateur-docker-hub>/ms_paiement:latest .
```

### 3. Pousser les Images vers Docker Hub

Une fois les images créées, vous pouvez les pousser sur Docker Hub pour qu'elles soient accessibles depuis n'importe où.

#### a. Pousser l'image du microservice utilisateur

```bash
docker push <votre-nom-utilisateur-docker-hub>/ms_utilisateur:latest
```

#### b. Pousser l'image du microservice panier

```bash
docker push <votre-nom-utilisateur-docker-hub>/ms_panier:latest
```

#### c. Pousser l'image du microservice commande

```bash
docker push <votre-nom-utilisateur-docker-hub>/ms_commande:latest
```

#### d. Pousser l'image du microservice paiement (Non implémenté)

```bash
docker push <votre-nom-utilisateur-docker-hub>/ms_paiement:latest
```

### 4. Vérification des Images sur Docker Hub

Après avoir poussé les images, vous pouvez les vérifier sur Docker Hub en vous rendant sur votre profil ou en exécutant la commande :

```bash
docker images
```

Cela vous donnera la liste des images Docker disponibles localement, ainsi que leur tag et ID.

### 6. Lancer les Conteneurs

Une fois votre fichier `docker-compose.yml` préparé, vous pouvez lancer tous les services avec Docker Compose en suivant le Readme Docker-compose.

### Notes Importantes

- **Docker et Docker Compose** : Assurez-vous que Docker et Docker Compose sont installés sur votre machine pour pouvoir construire et déployer les images. Pour les installer, consultez les liens ci-dessus.
- **Docker Hub** : Pour pousser les images, vous devez disposer d'un compte Docker Hub.
- **Ports** : Si vous avez un conflit de ports, vous pouvez les modifier dans votre fichier `docker-compose.yml` en conséquence.

---
