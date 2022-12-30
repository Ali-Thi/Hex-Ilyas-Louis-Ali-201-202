package game;

public interface IGame {
	/**
	 * Permet de jouer un coup
	 * @param square      la coordonnée de la case au format {A-Z}{1-MAX_INT}
	 * @param indexPlayer index du joueur qui a joué le coup
	 * @return true si le coup a bien été enregistré, sinon false
	 * @throws IllegalArgumentException indexPlayer or de la borne ou case invalide
	 */
	boolean playAMove(String square, int indexPlayer);

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

	/**
	 * Fait jouer un coup par une IA
	 * @param indexIA l'index de l'IA dans le tableau des joueurs (similaire à index Player pour les joueurs humain)
	 * @throws RuntimeException lorsque l'IA a joué un coup non valide, preuve d'une erreur dans l'IA
	 * @throws IllegalArgumentException lorsque l'indexIA ne concerne pas une IA, mais un joueur humain
	 */
	void makeIAPlay(int indexIA);
	
}