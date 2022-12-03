package game;

public interface IIA {

    /**
     * Generate a move
     * @return the move
     * @throws RuntimeException board full
     */
    String generateMove();

    /**
     * Actualize the IA's integer matrice which represent the board;
     * @param square the square been played
     * @param indexPlayer the player's index who played
     */
    boolean addMove(String square, int indexPlayer);

    /**
     * Clean the board
     */
    void cleanBoard();
}
