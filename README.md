# PROJET HEX

## Membres

  - JATTIOUI Ilyas (202) 
  - MASSON Louis (202)
  - TALHAOUI Ali (201)

## TABLE DES MATIERES

1. INTRODUCTION
2. DIAGRAMME D'ARCHITECTURE
3. LISTE SYNTHETIQUE

## 1. INTRODUCTION

Nôtre projet Hex est fonctionnel à 100%, il contient 3 possibilités de jeux:
1) Jouer en joueur contre joueur
2) Jouer en joueur contre IA
3) Jouer en IA contre IA

## 2. DIAGRAMME D'ARCHITECTURE

Mettre le nouveau
![class_diagramm](https://user-images.githubusercontent.com/59169832/210149516-2c85a861-e255-4f09-a790-f14e509caa3e.png)

## 3. LISTE SYNTHETIQUE



## 3. ORGANISATION
Nous nous sommes retrouvé avec 2 grandes classes à coder assez complexes et nous nous sommes réparti les tâches de la manière suivante :
Louis s'occupe de la class IA et Square
Ilyas s'occupe de la class Board et de l'IHM
Ali s'est occupé de la conception du projet, de l'implémentation de son architecture, ainsi que des classes 'simples'

Nous avons tous codé chacun de notre côté, mais lorsqu'une personne avait des difficultés, nous l'avons aidé pour permettre au projet d'avancer plus vite et d'être fonctionnel.

## REFLEXION

Pour nous, la partie la plus complexe a été de trouver un moyen de savoir si un joueur a gagné.

Notre solution :
Créer une ArrayList de LinkedList pour chacun des joueurs. Chacune des LinkedList représente un chemin crée par le joueur et présent sur le plateau. Ces chemins sont mis à jour dès que le joueurs correspondant joue un nouveau coup. Enfin, il ne reste plus qu'à tester si un chemin traverse de part en part le plateau.

## EXPÉRIENCE GAGNÉE :

MASSON Louis : pour ma part, j'ai appris à utiliser github et git. J'ai eu du mal au début, mais je sais maintenant comment me mettre à jour et transmettre mes fichiers.
J'ai aussi pu réfléchir à différentes manières de résoudre les problèmes rencontrés.

JATTIOUII lyas :

TALHAOUI Ali : je n'ai pas appris de nouvelles choses par ce projet. Cependant, cela a été une occasion de mettre en pratique mes connaissances accumulées  en essayant d'adopter une approche conceptuelle en premier lieu avant de passer à l'implémentation.

## AMÉLIORATION POSSILE :

Dans notre projet, l'IA joue de manière aléatoire, mais si nous voulons améliorer cela, il faudrait une IA avec un niveau de difficulté (facile, moyen, impossible).
