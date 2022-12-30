package game.utilities;

import game.IPlayer;

public class HPlayer implements IPlayer {
    private String name;
    public HPlayer(String name){
        this.name = name;
    }

    /**
     * Test si l'instance courante est une IA
     * @return true si elle l'est, false sinon
     */
    @Override
    public boolean isIA() {
        return false;
    }

    /**
     * Retourne le nom du joueur
     * @return le nom
     */
    @Override
    public String getName() {
        return name;
    }
}
