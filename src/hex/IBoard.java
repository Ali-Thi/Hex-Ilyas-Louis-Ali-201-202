package hex;

public interface IBoard {
	/**
	 * Permet de jouer un coup
	 * @param square      la coordonnée de la case au format {A-Z}{1-MAX_INT}
	 * @param indexPlayer index du joueur qui a joué le coup
	 * @return true si le coup a bien été enregistré, sinon false
	 * @throws IllegalArgumentException indexPlayer or de la borne ou case invalide
	 * @throws RuntimeException si la partie est déjà finie
	 */
	boolean placeAPawn(String square, int indexPlayer);

	/**
	 * Test si un joueur a gagné
	 * @return true si un joueur a gagné, sinon false
	 */
	boolean isWon();

	/**
	 * Retourne la largeur du plateau
	 * @return la largeur
	 */
	int getSide();

	/**
	 * Test si la case est vide
	 * @param square la case sous la forme [A-Z||a-z][1-MAX_INT]
	 * @return true si elle est vide, false sinon
	 */
	boolean isSquareEmpty(String square);

	/**
	 * Remet le plateau à zéro
	 */
	void cleanBoard();

	/**
	 * Retourne l'index du joueur ayant gagné
	 * @return l'index du joueur
	 */
	int getWinner();
}