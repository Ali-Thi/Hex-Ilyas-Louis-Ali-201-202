# PROJET HEX

## Membres

  - JATTIOUI Ilyas (202) 
  - MASSON Louis (202)
  - TALHAOUI Ali (201)
  
  MZ: Note 12/20 au groupe. La note de l'exo du DST ne sera prise en compte que si elle est supérieure donc cette note ne peut pas baisser.
  
## TABLE DES MATIÈRES

1. INTRODUCTION
2. DIAGRAMME D'ARCHITECTURE
3. TESTS UNITAIRES
4. ORGANISATION
5. RÉFLEXION
6. EXPÉRIENCE GAGNÉE
7. MODIFICATION DE RÈGLES
8. AMÉLIORATIONS POSSIBLES

## 1. INTRODUCTION

Notre projet Hex permet 3 modes de jeux :
1) Jouer en joueur contre joueur
2) Jouer en joueur contre IA
3) Jouer en IA contre IA

La taille du plateau est totalement personnalisable par l'utilisateurs. Toutefois, il est conseillé de choisir une taille parmis les 3 suivants : 11\*11, 14\*14 et 19\*19.
La partie se déroule entièrement sur l'invite de commande, avec des entrées claviers testés et vérifiés. Les entrées suivent un schéma prédéfini, une redirection de l'entrée standard sur un fichier peut être faite via le format :
```
[0, 1, 2]
[0, 1, 2]
{nom du joueur 1}
{nom du joueur 2}
{un coup par ligne sous le format [A-S][1-19]}
```
La règle du changement de couleur n'est, quant à elle, pas implémentée sur cette version du projet.

## 2. DIAGRAMME D'ARCHITECTURE
### Sans package apparent
![ClassDiagrammWithoutPackage.png](img%2Fsrc.png)
### Avec package apparent
![ClassDiagrammWithPackage.png](img%2FIHM.png)
<img src="../img/src.png" width=50% alt="diagramme de classe sans package">
<img src="../img/IHM.png" width=50% alt="diagramme de classe avec package">

## 3. TESTS UNITAIRES

Chaque classe de test, listée ci-dessous, contient plusieurs fonctions de tests qui représentent chacune une étape de l'implémentation de la classe qui lui correspond.

TestBoard :
1) Test l'initialisation d'une instance de Board
2) Test l'ajout de pion sur le plateau, ainsi que de la fonction cleanBoard qui remet le plateau à son état initial
3) Test les fonctions vérifiant si la partie est gagnée et par qui sur des chemins verticaux ou horizontaux sans fusion de chemin
4) Test les fonctions vérifiant si la partie est gagnée et par qui sur des chemins en diagonale sans fusion de chemin
5) Test les fonctions vérifiant si la partie est gagnée et par qui sur des chemins sinueux avec fusion de chemin
6) Test que les fonctions lèvent des erreurs lorsqu'une erreur survient
7) Test lorsque 2 joueurs jouent chacun leur tour sur le même plateau
8) Test la fonction isBoardFull

TestIA (ces tests demandent que l'attribut board de la classe IA soit public) :
1) Test l'initialisation d'une instance de IA
2) Test la fonction addMove
3) Test la fonction generateMove et cleanBoard
4) Test 2 IA jouant sur le même plateau, ainsi que le levé d'exceptions
5) Test la fonction isBoardFull

TestGame :
1) Test l'initialisation d'une instance de IA
2) Test les fonctions isWon et getWinner
3) Test un humain jouant contre une ia
4) Test la levé d'exceptions

La classe PathList est indirectement testée via le test de la classe Board. Les classes restantes sont très simples, il est donc inutile de les tester.

## 4. ORGANISATION
Nous nous sommes retrouvés avec 2 grandes classes à coder assez complexes et nous nous sommes réparti les tâches de la manière suivante :
Louis s'occupe de la class IA et Square
Ilyas s'occupe de la class Board et de l'IHM
Ali s'est occupé de la conception du projet, de l'implémentation de son architecture, ainsi que des classes 'simples'.

Nous avons tous codé chacun de notre côté, mais lorsqu'une personne avait des difficultés, nous l'avons aidé pour permettre au projet d'avancer plus vite et d'être fonctionnel.

## 5.RÉFLEXION

Pour nous, la partie la plus complexe a été de trouver un moyen de savoir si un joueur a gagné.

Notre solution :
Créer une ArrayList de LinkedList pour chacun des joueurs. Chacune des LinkedList représente un chemin crée par le joueur et présent sur le plateau. Ces chemins sont mis à jour dès que le joueurs correspondant joue un nouveau coup. Enfin, il ne reste plus qu'à tester si un chemin traverse de part en part le plateau.

## 6. EXPÉRIENCE GAGNÉE :

MASSON Louis : pour ma part, j'ai appris à utiliser Github et Git. J'ai eu du mal au début, mais je sais maintenant comment me mettre à jour et transmettre mes fichiers.
J'ai aussi pu réfléchir à différentes manières de résoudre les problèmes rencontrés.

JATTIOUI Ilyas : ce projet m'a permis de consolider mes acquis de la première année (respect des principes SOLID et réalisation d'un projet à partir des tests, méthode très utilisée en entreprise), tout en apprenant à rédiger du code lisible que mes collègues peuvent reprendre.

TALHAOUI Ali : je n'ai pas appris de nouvelles choses par ce projet. Cependant, cela a été une occasion de mettre en pratique mes connaissances accumulées  en essayant d'adopter une approche conceptuelle en premier lieu avant de passer à l'implémentation.

## 7. MODIFICATION DE RÈGLES :

La plupart des modifications de règles peuvent se faire dans l'UI directement. Cependant, par soucis d'efficacité, nous pouvons aussi modifier les classes représentant les objets affectés. 
- **Nombre de joueurs :** si nous voulons faire que plus de 2 joueurs jouent en même temps, il suffit de passer le nombre exact de joueurs en paramètre au plateau lorsque nous le créons, et passer ensuite l'instance de chaque joueur en paramètre du constructeur de la classe `Game`.
- **Jouer une case déjà jouée :** si nous voulons que les joueurs puissent jouer une case ayant déjà été joué par l'adversaire, nous devons simplement supprimer le test de case libre dans la classe IA et Board.
- **Changer les extrémités à relier :** si nous voulons faire en sorte de changer quelles extrémités chacun des joueurs doit relier, nous n'avons qu'à changer l'argument `axe` dans l'appel de la fonction `isPathComplete` au sein de la classe `Board`.
- **Règle du gâteau :** inverser l'ordre du tableau `players` dans la classe `Game`.


## 8. AMÉLIORATIONS POSSIBLES :

- Dans notre projet, l'IA joue de manière aléatoire, mais si nous voulons améliorer cela, il faudrait une IA avec un niveau de difficulté (facile, moyen, impossible), et jouant selon une logique.
- Implémenter la règle du changement de couleur.
- Ajouter une interface graphique.
- Permettre à plus de 2 joueurs de jouer.
- Implémenter une solution de jeux en ligne.
- Ajouter une sauvegarde des parties jouées.
