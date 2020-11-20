# Rétrospective 

## Sprint2

### Collision du Joueur avec un monstre

Le test de collision se faisait tout d'abord sur des nombres entiers, or la position du Joueur dans le monde
est un Vector2 de type float, une erreur qui a légèrement ralenti la mise en place de la fonctionnalité.

### La fonctionnalité "Décès en cas de contact" n'est pas terminée

La dernière partie de cette fonctionnalité a necessité une réflexion en équipe à la fin du sprint, nous
avons donc préféré ne pas se précipiter et avons repoussé la fonctionnalité au sprint 3.

### Niveau élaboré

Le format des données a été changé de xml à json pour plus de facilité.
De plus la fonctionnalité nécessite quelques modifications et ajouts qui seront réalisés
dans le sprint 3

### Changements dans les Vues du jeu

Pour plus de facilité dans les transitions entre les vues, nous avons ré-écrit et ré-organisé toutes les classes
qui gèrent les vues.


### Gestion des branches dans Git

Nous avons eu quelques soucis dans les manipulations des branches dans Git, 
notamment du fait d'un trop grand nombre de branches.
Nous avons donc supprimé les branches délaissées à la fin du sprint 2. 
