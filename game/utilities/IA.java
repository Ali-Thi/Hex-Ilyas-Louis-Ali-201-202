package game.utilities;

import game.IIA;
import game.IPlayer;
 	
public class IA implements IIA, IPlayer {

    public IA(int side){}

    /**
     * Generate a move
     * @return the move
     * @throws RuntimeException board full
     */
    @Override
    public String generateMove() {
        return null;
    }

    /**
     * Actualize the IA's integer matrice which represent the board;
     * @param square      the square been played
     * @param indexPlayer the player's index who played
     */
    @Override
    public boolean addMove(String square, int indexPlayer) {
        return false;
    }

    /**
     * Clean the board
     */
    @Override
    public void cleanBoard() {

    }

    /**
     * Check if the instance is an IA
     * @return true if it is, false otherwise
     */
    @Override
    public boolean isIA() {
        return false;
    }

    /**
     * Return the name of the player
     * @return the name
     */
    @Override
    public String getName() {
        return null;
    }
}
