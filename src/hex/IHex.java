package hex;

public interface IHex {
	/**
	 * Permet de jouer un coup
	 * @param indexPlayer index du joueur qui a joué le coup
	 * @throws IllegalArgumentException indexPlayer or de la borne
	 * @throws RuntimeException         case invalide
	 */
	boolean placeAPawn(int indexPlayer);

	/**
	 * Test si un joueur a gagné
	 * @return true si un joueur a gagné, sinon false
	 */
	boolean isWon();

	/**
	 * Remet le plateau à zéro
	 */
	void cleanBoard();

	/**
	 * Retourne l'index du joueur ayant gagné
	 * @return l'index du joueur
	 */
	String getWinner();
}