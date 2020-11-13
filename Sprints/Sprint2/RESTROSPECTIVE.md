# Rétrospective 

## Sprint2

### Déplacements linéaires

Quelques soucis dans la gestion des chemins d'accès aux différents fichiers dû à l'utilisation de 
différents systèmes d'exploitation (Windows/MAC).

### Déplacements des monstres dans le labyrinthe

Le choix du sens des axes (abscisse / ordonnée) différent dans certaines classes a causé quelques problèmes qui
ont été réglés en normalisant le sens de ces axes pour tout le projet.

### Mort du joueur en cas de contact avec un monstre

Le déplacement du joueur était en premier lieu géré par la classe Player, il est apparu plus logique par la suite
que la classe World s'occupe de ce déplacement.
Nous avons donc modifié et interverti les fonctions de déplacement entre la classe Player et la classe World.

### Niveaux plus élaborés

Nous avons eu un soucis de type lié à l'utilisation de libgdx, plus précisement de l'objet Vector2 de la librairie.
Les Murs étaient tout d'abord définis par une position de type "int" qui a été modifiée en "float" afin de coller avec
le type Vector2.

### Texture pack

Nous avons eu un problème avec un champ dans une interface, nous ne savions pas qu'il était static. 
Nous avons donc redéfinit l'interface par des getters.
