# PROJET HEX

## Membres

  - JATTIOUI Ilyas (202) 
  - MASSON Louis (202)
  - TALHAOUI Ali (201)

## TABLE DES MATIERES

1. INTRODUCTION
2. DIAGRAMME D'ARCHITECTURE
3. LISTE SYNTHETIQUE
4. ORGANISATION
5. REFLEXION
6. EXPERIENCE GAGNEE
7. AMELIORATION POSSIBLE

## 1. INTRODUCTION

Nôtre projet Hex est fonctionnel à 100%, il contient 3 possibilités de jeux:
1) Jouer en joueur contre joueur
2) Jouer en joueur contre IA
3) Jouer en IA contre IA

## 2. DIAGRAMME D'ARCHITECTURE

![hexDiagram](https://user-images.githubusercontent.com/59169832/210251104-f40e6f5c-c37c-4a61-bd58-3df8ef82e6f9.png)

## 3. TESTS UNITAIRES

1) Une classe "TestBoard", qui teste le bon fonctionnement des méthodes de la classe Board avec notamment : <br>
 - Des tests qui vérifient l'affichage (toString()) du plateau, l'attribution d'un pion avec la méthode playAMove() ainsi que la méthode cleanBoard() qui vide le plateau (pour faciliter les tests et facilement recommencer une partie si les joueurs le veulent) <br>
 - Des tests qui vérifient le bon fonctionnement des règles du jeu, caractérisé par les méthodes isWon() et getWinner()


2) Une classe "TestIA", qui teste l'initialisation de l'IA et sa généreration de coups (méthodes isIA() et addMove()).
 
3) Une classe "TestGame", qui teste le bon fonctionnement d'une partie : initialisation des joueurs (instance de la classe Game), vérification du gagnant de la partie, gestion des éventuelles erreurs (Mauvaise case ou index du joueur) ainsi qu'un test du bon déroulement d'une partie contre l'IA.



## 4. ORGANISATION
Nous nous sommes retrouvé avec 2 grandes classes à coder assez complexes et nous nous sommes réparti les tâches de la manière suivante :
Louis s'occupe de la class IA et Square
Ilyas s'occupe de la class Board et de l'IHM
Ali s'est occupé de la conception du projet, de l'implémentation de son architecture, ainsi que des classes 'simples'

Nous avons tous codé chacun de notre côté, mais lorsqu'une personne avait des difficultés, nous l'avons aidé pour permettre au projet d'avancer plus vite et d'être fonctionnel.

## 5.REFLEXION

Pour nous, la partie la plus complexe a été de trouver un moyen de savoir si un joueur a gagné.

Notre solution :
Créer une ArrayList de LinkedList pour chacun des joueurs. Chacune des LinkedList représente un chemin crée par le joueur et présent sur le plateau. Ces chemins sont mis à jour dès que le joueurs correspondant joue un nouveau coup. Enfin, il ne reste plus qu'à tester si un chemin traverse de part en part le plateau.

## 6. EXPÉRIENCE GAGNÉE :

MASSON Louis : pour ma part, j'ai appris à utiliser github et git. J'ai eu du mal au début, mais je sais maintenant comment me mettre à jour et transmettre mes fichiers.
J'ai aussi pu réfléchir à différentes manières de résoudre les problèmes rencontrés.

JATTIOUI Ilyas : ce projet m'a permis de consolider mes acquis de la première année (respect des principes SOLID et réalisation d'un projet à partir des tests, méthode très utilisée en entreprise), tout en apprenant à rédiger du code lisible que mes collègues peuvent reprendre.

TALHAOUI Ali : je n'ai pas appris de nouvelles choses par ce projet. Cependant, cela a été une occasion de mettre en pratique mes connaissances accumulées  en essayant d'adopter une approche conceptuelle en premier lieu avant de passer à l'implémentation.

## 7. AMÉLIORATION POSSILE :

Dans notre projet, l'IA joue de manière aléatoire, mais si nous voulons améliorer cela, il faudrait une IA avec un niveau de difficulté (facile, moyen, impossible).
