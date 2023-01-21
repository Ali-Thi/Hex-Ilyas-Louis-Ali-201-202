package hex.board;

public class Board implements hex.IBoard {
    public enum Square {Vide("."), Player1("X"), Player2("O");
        private final String valeur;
        Square(String o) {
            valeur = o;
        }

        @Override
        public String toString(){
            return valeur;
        }

    }
    private final Square[][] board; //plateau
    private final PathList[] paths; //tableau des chemins, chaque element representant un joueur dans l'ordre de jeux
    private final int side; //taille du tableau
    private final int nbPlayer = 2;
    private int winnerIndex; //l'index du joueur, dans l'ordre de jeu, gagnant

    /**
     * Constructeur
     *
     * @param side taille du tableur (largeur ou hauteur)
     */
    public Board(int side) {
        this.side = side;
        this.board = new Square[this.side][this.side];
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                this.board[x][y] = Square.Vide;
            }
        }
        this.winnerIndex = 0;
        //Creation des deux ArrayList des deux joueurs
        paths = new PathList[nbPlayer];
        for (int i = 0; i < nbPlayer; ++i)
            paths[i] = new PathList();
    }

    /**
     * Vérifie si la chaine représente bien une case possible, sinon lève une excpetion
     * @param square la case
     * @throws IllegalArgumentException case invalide
     */
    private void verifySquare(String square) {
        if (square.toUpperCase().charAt(0) < 'A' ||
                square.toUpperCase().charAt(0) > (char) (side - 1 + (int) 'A')) {
            throw new IllegalArgumentException("Le premier caractère n'est pas compris dans les bornes ou présente le mauvais format.");
        }
        try {
            if(Integer.parseInt(square.substring(1)) > side || Integer.parseInt(square.substring(1)) < 1)
                throw new IllegalArgumentException("Le nombre n'est pas compris dans les bornes.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Le nombre présente le mauvais format.");
        }
    }

    /**
     * Traduit une chaine représentant une case en coordonnées
     * @param square la chaine représentant une case
     * @return les coordonnées (x, y) sous forme de tableau
     */
    private int[] getCoord(String square) {
        verifySquare(square);
        int x = square.toUpperCase().charAt(0) - 'A';
        int y = Integer.parseInt(square.substring(1)) - 1;
        return new int[]{x, y};
    }


    /**
     * Permet de jouer un coup
     * @param square      la coordonnée de la case au format {A-Z}{1-MAX_INT}
     * @param indexPlayer index du joueur qui a joué le coup
     * @return true si le coup a bien été enregistré, sinon false
     * @throws IllegalArgumentException indexPlayer or de la borne
     * @throws RuntimeException         case invalide
     */
    @Override
    public boolean placeAPawn(String square, int indexPlayer) {
        if (indexPlayer >= nbPlayer || indexPlayer < 0) {
            throw new IllegalArgumentException("L'index du joueur est en dehors des limites.");
        }

        int[] coord = getCoord(square);
        int x = coord[0];
        int y = coord[1];

        if (isSquareEmpty(square)) {
            board[y][x] = Square.values()[indexPlayer + 1];

            int[][] indexAdjacentSquare = new int[][]{
                    new int[]{y - 1, x},
                    new int[]{y - 1, x + 1},
                    new int[]{y, x - 1},
                    new int[]{y, x + 1},
                    new int[]{y + 1, x - 1},
                    new int[]{y + 1, x}
            };

            boolean pathFind = false;

            for (int[] coords : indexAdjacentSquare) {
                try {
                    if (board[coords[0]][coords[1]] == Square.values()[indexPlayer + 1] && !pathFind) { //si la case est déjà occupée
                        paths[indexPlayer].addSquareToPath(x, y, coords[1], coords[0]);
                        pathFind = true;
                    } else if (board[coords[0]][coords[1]] == Square.values()[indexPlayer + 1] && pathFind) {
                        paths[indexPlayer].mergePaths(x, y, coords[1], coords[0]);
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            }

            if (!pathFind) {
                paths[indexPlayer].addSquare(x, y);
            }
            return true;
        } else
            return false;

    }

    /**
     * Test si un joueur a gagné
     *
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

            if (paths[i].isPathComplete(0, side - 1, axe)) {
                winnerIndex = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la largeur du plateau
     *
     * @return la largeur
     */
    @Override
    public int getSide() {
        return side;
    }

    /**
     * Test si la case est vide
     *
     * @param square la case sous la forme [A-Z||a-z][1-MAX_INT]
     * @return true si elle est vide, false sinon
     */
    @Override
    public boolean isSquareEmpty(String square) {
        verifySquare(square);
        int[] coord = getCoord(square);
        int x = coord[0];
        int y = coord[1];
        return board[y][x] == Square.Vide;
    }

    /**
     * Remet le plateau à zéro
     */
    @Override
    public void cleanBoard() {
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                board[x][y] = Square.Vide;
            }
        }
        for (int i = 0; i < nbPlayer; ++i) {
            paths[i] = new PathList();
        }
    }

    /**
     * Retourne l'index du joueur ayant gagné
     *
     * @return l'index du joueur
     */
    @Override
    public int getWinner() {
        return winnerIndex;
    }

    /**
     * Retourne une chaine représentant l'instance courante
     *
     * @return une chaine de caractère
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(" ");
        for (int i = 0; i <= side; ++i) {
            if (i > 0) {
                s.append('\n');
                s.append(i);
                for (int k = 0; k < i - String.valueOf(i).length(); ++k)
                    s.append(" ");
            }
            for (int j = 0; j < side; ++j) {
                if (i == 0) s.append(" ").append((char) (j + (int) 'A'));
                else {
                    s.append(" ").append(board[i - 1][j].toString());
                }
            }
        }
        s.append('\n');
        return s.toString();
    }
}
