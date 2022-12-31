package game.utilities;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathList {

    /**
     * Classe représentant des coordonnées, utilisé pour représenter chaque case du plateau
     */
    private static class Coord{
        public final int x;
        public final int y;

        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "[" + x + ", " + y + "]";
        }

        @Override
        public boolean equals(Object o){
            return o instanceof Coord && ((Coord) o).x == x && ((Coord) o).y == y;
        }
    }
    private final ArrayList<LinkedList<Coord>> paths; //les chemins

    public PathList(){
        paths = new ArrayList<>();
    }

    /**
     * Permet de trouver dans quelle liste se trouve une coordonnée
     * @param x la valeur de l'attribut x
     * @param y la valeur de l'attribut y
     * @return l'index de la liste, -1 sinon
     */
    private int findPath(int x, int y){
        for(LinkedList<Coord> list : paths){
            if (list.contains(new Coord(x, y))) {
                return paths.indexOf(list);
            }
        }
        return -1;
    }

    /**
     * Créer un nouveau chemin comportant qu'une case
     * @param x la coordonnée sur l'axe des abscisses de la case
     * @param y la coordonnée sur l'axe des ordonnées de la case
     */
    public void addSquare(int x, int y){
        paths.add(new LinkedList<>());
        paths.get(paths.size() -1).add(new Coord(x, y));
    }

    /**
     * ajoute une case à un chemin
     * @param x1 la coordonnée sur l'axe des abscisses de la nouvelle case
     * @param y1 la coordonnée sur l'axe des ordonnées de la nouvelle case
     * @param x2 la coordonnée sur l'axe des abscisses de la case environnante étant comprise dans un chemin
     * @param y2 la coordonnée sur l'axe des ordonnées de la case environnante étant comprise dans un chemin
     */
    public void addSquareToPath(int x1, int y1, int x2, int y2){
        int indexPath = findPath(x2, y2);
        if(indexPath >= 0)
            paths.get(indexPath).add(new Coord(x1, y1));
        else {
            throw new RuntimeException("Cannot find the path of the square played.");
        }
    }

    /**
     * fusionne deux chemins
     * @param x1 la coordonnée sur l'axe des abscisses de la case étant comprise dans un premier chemin
     * @param y1 la coordonnée sur l'axe des ordonnées de la case étant comprise dans un premier chemin
     * @param x2 la coordonnée sur l'axe des abscisses de la case étant comprise dans un second chemin
     * @param y2 la coordonnée sur l'axe des ordonnées de la case étant comprise dans un second chemin
     * @throws RuntimeException la seconde case n'appartient à aucun chemin connu
     */
    public void mergePaths(int x1, int y1, int x2, int y2){
        int indexFirstPath = findPath(x1, y1);
        int indexSecondPath = findPath(x2, y2);
        if(indexFirstPath >= 0 && indexSecondPath >= 0){
            if(indexFirstPath != indexSecondPath) {
                paths.get(indexFirstPath).addAll(paths.get(indexSecondPath));
                paths.remove(indexSecondPath);
            }
        } else throw new RuntimeException("Cannot find the path of the square played.");
    }

    /**
     * Test si un chemin parcourt le plateau de bout en bout
     * @param coordMin la valeur x ou y impliquant que la case est sur un bord du plateau
     * @param coordMax la valeur x ou y impliquant que la case est sur l'autre bord du plateau
     * @param axe permet de définir si l'on cherche un chemin via l'axe x ou y
     * @return true si un tel chemin existe, false sinon
     * @throws IllegalArgumentException axe ne correspond pas à x ou y
     */
    public boolean isPathComplete(int coordMin, int coordMax, char axe){
        if(!(axe == 'x' || axe =='y'))
            throw new IllegalArgumentException();
        boolean coordMinFind = false;
        boolean coordMaxFind = false;
        for(LinkedList<Coord> list : paths){
            for(Coord coords : list){
                if(axe == 'x') {
                    if (coords.x == coordMin) coordMinFind = true;
                    else if (coords.x == coordMax) coordMaxFind = true;
                } else{
                    if (coords.y == coordMin) coordMinFind = true;
                    else if (coords.y == coordMax) coordMaxFind = true;
                }
            }
        }
        return coordMaxFind && coordMinFind;
    }
}
