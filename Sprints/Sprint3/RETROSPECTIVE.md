# Rétrospective 

## Sprint3


### Algorithme de cases spéciales

Au début, l'algorithme qui se chargeait de vérifier si le joueur était dans une case spéciale était beaucoup 
trop lent, donc si on créait un labyrinthe de grande taille, le jeu prenait beaucoup de temps. L'algorithme a
donc été modifié.

### La fonctionnalité "Cases spéciales" n'est pas terminée

Les effets des cases spéciales ne sont pas tous fonctionnels. Nous avons aussi pensé à faire disparaître
la case une fois que le joueur est passé dessus. L'apparition de ces cases se fait pour l'instant de manière
aléatoire, ce qui n'est pas idéal (il se peut que le trésor soit à côté de la position initiale). Nous allons
affiner cela au sprint 4.

### La fonctionnalité "Déplacements intelligents" n'est pas réalisée

Nous avons envisagé d'utiliser l'algorithme A* pour le déplacement intelligent des monstres. Cependant, comme
les labyrinthes générés n'ont qu'un seul chemin libre, un déplacement plus intelligent des monstres aurait 
provoqué l'impossibilité de gagner. Nous avons donc décidé de repousser cette fonctionnalité au sprint 4.