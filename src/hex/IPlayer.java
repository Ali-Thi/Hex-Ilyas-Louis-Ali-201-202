package hex;

public interface IPlayer {

    /**
     * Retourne le nom du joueur
     * @return le nom
     */
    String getName();

    /**
     * Permet au joueur de choisir une case sur laquelle poser son pion
     * @return la chaine correspondante à la case sous la forme [A-Z||a-z][1-MAX_INT]
     */
    String chooseSquare(int side);
}
