package game;

public interface IPlayer {

    /**
     * Test si l'instance courante est une IA
     * @return true si elle l'est, false sinon
     */
    boolean isIA();

    /**
     * Retourne le nom du joueur
     * @return le nom
     */
    String getName();
}
