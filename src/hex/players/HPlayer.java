package hex.players;

import hex.IBoard;
import hex.IPlayer;

/**
 * Classe de joueur humain
 */
public class HPlayer implements IPlayer {
    private String name;
    private IInput sc;
    public HPlayer(String name, IInput scanner){
        this.name = name;
        this.sc = scanner;
    }

    /**
     * Retourne le nom du joueur
     * @return le nom
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Permet au joueur de choisir une case sur laquelle poser son pion
     * @return la chaine correspondante à la case sous la forme [A-Z||a-z][1-MAX_INT]
     */
    @Override
    public String chooseSquare(int side) {
        return sc.input();
    }
}
