package game;

public interface IPlayer {

    /**
     * Check if the instance is an IA
     * @return true if it is, false otherwise
     */
    boolean isIA();

    /**
     * Return the name of the player
     * @return the name
     */
    String getName();
}
