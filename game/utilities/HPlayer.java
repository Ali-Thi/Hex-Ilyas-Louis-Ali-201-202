package game.utilities;

import game.IPlayer;

public class HPlayer implements IPlayer {
    private String name;
    public HPlayer(String name){
        this.name = name;
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
        return name;
    }
}
