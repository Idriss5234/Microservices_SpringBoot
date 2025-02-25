# shell

> La doc de l'api est dispo sur <https://swapi.dev/documentation>

Créez un script Bash qui effectue les opérations suivantes :

- Vérifiez que les outils nécessaires sont installés :
  Le script doit vérifier que curl et jq sont installés. Si ce n'est pas le cas, affichez un message d'erreur clair et terminez le script.

- Listez les personnages de Star Wars apparaissant dans un film spécifique :
  Demandez à l'utilisateur d'entrer un numéro d'épisode (ex. : 1 pour "The Phantom Menace").
  Utilisez l'API pour récupérer la liste des personnages de cet épisode et affichez leurs noms triés par ordre alphabétique.

- Comptabilisez les espèces présentes dans un film :
  Récupérez toutes les espèces présentes dans le même épisode et affichez combien de fois chaque espèce apparaît.

  **Exemple attendu :**

  ```
  Espèce : Human (12)
  Espèce : Droid (5)
  ```

- Récupérez les informations sur le véhicule le plus rapide :
  Récupérez la liste des véhicules disponibles dans l'API.
  Identifiez le véhicule avec la plus grande vitesse maximale (max_atmosphering_speed) et affichez son nom, sa vitesse et son coût en crédits.

**Tips**

> Le script doit être structuré en fonctions : une fonction par tâche.
> Utilisez curl pour interroger l'API et jq pour traiter les données JSON.
> Utilisez des outils comme awk, sed, ou grep si nécessaire pour des manipulations supplémentaires.
> Le script doit valider les entrées utilisateur (par exemple, le numéro d'épisode doit être un entier valide).

# Docker

On va utiliser script-server pour mettre à disposition ce script.
A partir de l'image docker `bugy/script-server:1.18.0`

**Tips**

> - copier le script shell dans `conf/script/swapi.sh`
> - copier le fichier de configuration dans `conf/runners/swapi.json`
> - pour tester l'image docker, il est nécessaire de `kubectl port-forward deployment/docker 5000:5000 --address='0.0.0.0'`

**Push**

Pour la suite, il est nécessaire de push l'image docker sur

```
849389115637.dkr.ecr.eu-central-1.amazonaws.com/other:devops
```

Pour se logguer sur ecr:

```
ECR_REGISTRY=849389115637.dkr.ecr.eu-central-1.amazonaws.com
aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin ${ECR_REGISTRY}
```

# K8S

On va maintenant mettre à disposition l'image docker sur un cluster K8S pour y accéder depuis internet

**Tips**

<https://kubernetes.io/fr/docs/concepts/services-networking/service/>
<https://kubernetes.io/docs/concepts/workloads/controllers/deployment/>

Pour exposer le service `swapi` sur internet, on va utiliser l'ingress suivant:

```
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  annotations:
    alb.ingress.kubernetes.io/certificate-arn: arn:aws:acm:eu-central-1:849389115637:certificate/194203de-2ee1-45ac-bd81-0597390afbb8,arn:aws:acm:eu-central-1:849389115637:certificate/94f91138-3b6a-4645-8b45-31c83c57a608
    alb.ingress.kubernetes.io/group.name: deploy-branch
    alb.ingress.kubernetes.io/listen-ports: '[{"HTTPS":443}]'
    alb.ingress.kubernetes.io/scheme: internet-facing
    alb.ingress.kubernetes.io/security-groups: sg-0ae6a4b757f39f93a, sg-02df99929514e1466
    alb.ingress.kubernetes.io/subnets: subnet-0abb45b7fd5cecabc,subnet-0e030039fa13eeedf,subnet-0be02305cf7df421a
    alb.ingress.kubernetes.io/success-codes: 200-499
    alb.ingress.kubernetes.io/target-node-labels: eks.amazonaws.com/nodegroup=ng-1-workers
    alb.ingress.kubernetes.io/tags: costcenter=deploy-branch
  name: devops
spec:
  ingressClassName: alb
  rules:
  - host: swapi.branch.dev.napta.tech
    http:
      paths:
      - backend:
          service:
            name: swapi
            port:
              number: 5000
        path: /
        pathType: Prefix
```
