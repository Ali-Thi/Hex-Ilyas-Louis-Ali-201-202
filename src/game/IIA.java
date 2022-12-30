package game;

public interface IIA {

    /**
     * Fait générer un coup par une IA
     * @return le coup généré
     * @throws RuntimeException tableau plein
     */
    String generateMove();

    /**
     * Actualise la matrice de l'IA représentant le plateau
     * @param square la case joué
     * @param indexPlayer le joueur ayant joué
     */
    boolean addMove(String square, int indexPlayer);

    /**
     * Efface le contenu de la matrice
     */
    void cleanBoard();
}
