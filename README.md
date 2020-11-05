# ACL-2020-pacmasque

## Introduction

Pacmasque est un jeu de plateau où le joueur se déplace dans un labyrinthe et 
doit en trouver la sortie. Il doit, sur son chemin, ramasser des objets pour 
augmenter son score et il pourra affronter des monstres qui lui barrent la 
route.

## Développement

Pacmasque est développé à l'aide du moteur LibGDX, en java. Il est développé 
sous forme de release, ou chacune apporte une nouvelle fonctionnalité.

## Utilisation

### Installation
Clone le dépot

Pour les commandes suivantes, deux exécutables sont disponibles:
- Linux / MacOS: `gradlew`
- Windows: `gradlew.bat`

### Build

`gradlew :desktop:build`

### Exécuter

`gradlew :desktop:run`

### Créer une archive JAR

`gradlew :desktop:dist`

## Objectifs et fonctionnalités

- [ ] Première version, 1.0.0
    - [x] Affichage graphique d'un labyrinthe (0.2.0)
    - [x] Déplacement d'un joueur avec les touches directionnelles du clavier (0.2.0)
    - [x] Murs, ne pouvant être traversés
    - [ ] Présence sur le plateau de pastilles (points à récupérer), et de 
    monstres statiques
    - [x] Textures basiques
    - [x] Chargement d'un niveau depuis un fichier

- [ ] Deuxième version, 2.0.0
    - [ ] Déplacements linéaires
    - [ ] Déplacements des monstres dans le labyrinthe
    - [ ] Mort du joueur en cas de contact avec un monstre
    - [ ] Niveaux plus élaborés (monstres, pastilles, plus grand plateau, plusieurs types de murs, de sols…)
    - [ ] Texture pack
    
Plus d'autres fonctionnalités dans les futures releases

## Développé par:
- Valérie MARISSENS CUEVA.  
- Nicolas BOMBARDE.  
- Ugo COTTIN.  
- hayet slimani.