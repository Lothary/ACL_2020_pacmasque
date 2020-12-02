# Rétrospective 

## Sprint3


### Algorithme de cases spéciales

À sa première version l'algorithme qui se chargeait de vérifier si le joueur était sur une case spéciale était 
trop lent, ainsi avec un labyrinthe de grande taille le jeu prenait beaucoup de temps à se charger. L'algorithme a
donc été modifié et optimisé.

### La fonctionnalité "Cases spéciales" n'est pas terminée

Les effets des cases spéciales ne sont pas tous fonctionnels. Nous avons aussi pensé à faire disparaître
la case une fois que le joueur est passé dessus. L'apparition de ces cases se fait pour l'instant de manière
aléatoire, ce qui n'est pas idéal (il se peut que le trésor soit à côté de la position initiale). Nous allons
affiner cela au sprint 4.

### La fonctionnalité "Déplacements intelligents" n'est pas réalisée

Nous avons envisagé d'utiliser l'algorithme A* pour le déplacement intelligent des monstres. Cependant, comme
les labyrinthes générés n'ont qu'un seul chemin libre, un déplacement plus intelligent des monstres aurait 
provoqué l'impossibilité de gagner. Nous avons donc décidé de repousser cette fonctionnalité au sprint 4.

### La fonctionnalité de la case téléportation 

La fonctionnalité de la case téléportation et celle du mouvement du joueur n'ont pas été écrites par les mêmes personnes
ainsi après téléportation, le mouvement du joueur n'était pas ré-initialisé correctement et l'algorithme du 
mouvement du personnage ne fonctionnait plus correctement.
Le correctif a été ajouté au sprint 4.

### Génération du labyrinthe

Nous avons utilisé Kruskal afin de générer des labyrinthes aléatoires, la mise en place de l'algorithme a été difficile et nous avons 
dû ajouter plusieurs classes auxiliaires pour implémenter l'algorithme dans notre programme existant et
améliorer sa visibilité.



