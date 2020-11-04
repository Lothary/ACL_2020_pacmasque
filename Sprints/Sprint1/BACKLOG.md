# Backlog  

## Sprint1

### Chargement d'un labyrinthe par un fichier texte

Quelques soucis dans la gestion des chemins d'accès aux différents fichiers dû à l'utilisation de 
différents systèmes d'exploitation (Windows/MAC).

### Affichage graphique d'un labyrinthe

Le choix du sens des axes (abscisse / ordonnée) différent dans certaines classes a causé quelques problèmes qui
ont été réglés en normalisant le sens de ces axes pour tout le projet.


### Déplacement d'un joueur avec les touches directionnelles du clavier 

Le déplacement du joueur était ten premier lieu géré par la classe Player, il est apparu plus logique par la suite
que la classe World s'occupe de ce déplacement.
Nous avons donc modifié et interverti les fonctions de déplacement entre la classe Player et la classe World.

### Murs, ne pouvant être traversés

Nous avons eu un soucis de type lié à l'utilisation de libgdx, plus précisement de l'objet Vector2 de la librairie.
Les Murs étaient tout d'abord définis par une position de type "int" qui a été modifiée en "float" afin de coller avec
le type Vector2.

### Présence sur le plateau de pastilles (points à récupérer) et de monstres statiques

Nous avons eu un problème avec un champ dans une interface, nous ne savions pas qu'il était static. 
Nous avons donc redéfinit l'interface par des getters.

### Textures basiques

L'utilisation de libgdx comme moteur graphique a entrainé quelques modifications de la structure du projet.
Nous avons alors pris le temps de tous régler nos IDE.

