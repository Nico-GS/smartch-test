
# Smartch - test technique

Service permettant de supprimer les doublons d'une liste d'entiers.

## Présentation
L'objectif est de créer une API REST simple avec un seul endpoint pour supprimer les valeurs en double d'une liste.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants sur votre machine :

- Java 17
- Kotlin
- Ktor
- Gradle
- Docker

## Utilisation
### Avec Gradle

Cloner le repo : `git clone https://github.com/username/smartch.git`

Ensuite, naviguez jusqu'au répertoire du projet : `cd smartch`

Et enfin, exécutez la commande suivante pour démarrer l'application : `./gradlew run`

L'application sera alors accessible à l'adresse `http://localhost:8080`

### Build image Docker en local

#### Prérequis

Docker installé en local

#### Build de l'image

`docker build -t smartch:latest .`

#### Lancement du container

`docker run -p 8080:8080 smartch:latest`


L'application sera alors accessible à l'adresse `http://localhost:8080`.

### Récupérer l'image sur le Docker Hub

`docker pull lornmalvo/smartch:latest`

## Documentation

L'endpoint accepte du JSON avec un payload contenant :

- input : liste d'entiers
- n : nombre entier

Il renvoie un JSON avec une liste output sans les valeurs dupliquées.

Exemple de requête POST sur `http://localhost:8080/api/remove-duplicated` :

```json
{
  "input": [1, 3, 8, 8, 1, 1, 1, 3, 5, 2],
  "n": 1 
}
```

Retour :

```json
{
    "output": [
        5,
        2
    ]
}
```