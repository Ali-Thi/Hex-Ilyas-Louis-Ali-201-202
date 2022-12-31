package game.utilities;

public class Board implements game.IBoard {
    private final Square[][] board; //plateau
    private final PathList[] paths; //tableau des chemins, chaque element representant un joueur dans l'ordre de jeux
    private int side; //taille du tableau
    private int nbPlayer; //nombre de joueurs
    private int winnerIndex; //l'index du joueur, dans l'ordre de jeu, gagnant

    /**
     * Constructeur
     * @param side     taille du tableur (largeur ou hauteur)
     * @param nbPlayer nombre de joueurs
     */
    public Board(int side, int nbPlayer) {
        this.side = side;
        this.nbPlayer = nbPlayer;
        this.board = new Square[this.side][this.side];
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                this.board[x][y] = new Square();
            }
        }
        this.winnerIndex = 0;
        //Creation des deux ArrayList des deux joueurs
        paths = new PathList[nbPlayer];
        for (int i = 0; i < nbPlayer; ++i)
            paths[i] = new PathList();
    }


    /**
     * Permet de jouer un coup
     * @param square      la coordonnée de la case au format {A-Z}{1-MAX_INT}
     * @param indexPlayer index du joueur qui a joué le coup
     * @return true si le coup a bien été enregistré, sinon false
     * @throws IllegalArgumentException indexPlayer or de la borne ou case invalide
     */
    @Override
    public boolean playAMove(String square, int indexPlayer) {
        // check les bordures
        if (square.charAt(0) < 'A' ||
                square.charAt(0) > (char) (this.side - 1 + (int) 'A') ) {
            throw new IllegalArgumentException("Premier caractère en dehors des limites.");
        }
        if (indexPlayer > nbPlayer || indexPlayer <= 0) {
            throw new IllegalArgumentException("L'index du joueur est en dehors des limites.");
        }
        try{
            Integer.parseInt(square.substring(1));
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("L'index de la case est en dehors des limites.");
        }

        //conversion du String en entier A=65 donc 65-65=0
        int y = square.charAt(0) - 'A';
        //conversion du String numéro en int
        int x = Integer.parseInt(square.substring(1)) - 1;
        if (board[x][y].getValue() == Square.PossbileValue.Vide) {
            this.board[x][y].setValue(indexPlayer);

            int[][] indexAdjacentSquare = new int[][]{
                    new int[]{x - 1, y},
                    new int[]{x - 1, y + 1},
                    new int[]{x, y - 1},
                    new int[]{x, y + 1},
                    new int[]{x + 1, y - 1},
                    new int[]{x + 1, y}
            };

            boolean pathFind = false;

            for (int[] coords : indexAdjacentSquare) {
                try {
                    if (this.board[coords[0]][coords[1]].getValue() == Square.PossbileValue.values()[indexPlayer] && !pathFind) { //si la case est déjà occupée
                        paths[indexPlayer - 1].addSquareToPath(y, x, coords[1], coords[0]);
                        pathFind = true;
                    } else if (this.board[coords[0]][coords[1]].getValue() == Square.PossbileValue.values()[indexPlayer] && pathFind) {
                        paths[indexPlayer - 1].mergePaths(y, x, coords[1], coords[0]);
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            }

            if (!pathFind) {
                paths[indexPlayer - 1].addSquare(y, x);
            }
            return true;
        } else
            return false;

    }

    /**
     * Test si un joueur a gagné
     * @return true si un joueur a gagné, sinon false
     */
    @Override
    public boolean isWon() {
        char axe;
        for (int i = 0; i < nbPlayer; ++i) {
            if (i == 0) {
                axe = 'y';
            } else {
                axe = 'x';
            }

            if (paths[i].isPathComplete(0, this.side - 1, axe)) {
                winnerIndex = i + 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Remet le plateau à zéro
     */
    @Override
    public void cleanBoard() {
        for (int x = 0; x < this.side; x++) {
            for (int y = 0; y < this.side; y++) {
                this.board[x][y] = new Square();
            }
        }
        for (int i = 0; i < nbPlayer; ++i) {
            paths[i] = new PathList();
        }

    }

    /**
     * Retourne l'index du joueur ayant gagné
     * @return l'index du joueur
     */
    @Override
    public int getWinner() {
        return this.winnerIndex;
    }


    /**
     * Retourne une chaine représentant l'instance courante
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(" ");
        for (int i = 0; i <= this.side; ++i) {
            if (i > 0) {
                s.append('\n');
                s.append(i);
                for (int k = 0; k < i - 1; ++k)
                    s.append(" ");
            }
            for (int j = 0; j < this.side; ++j) {
                if (i == 0) s.append(" ").append((char) (j + (int) 'A'));
                else s.append(" " + this.board[i - 1][j].toString());
            }
        }
        s.append('\n');
        return s.toString();
    }
}
