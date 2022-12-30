package game;

public class Game implements IGame {
	private final IBoard board;
	private final IPlayer[] players;

	public Game(IBoard board, IPlayer player1, IPlayer player2){
		this.board = board;
		this.players = new IPlayer[]{player1, player2};
	}

	/**
	 * Permet de jouer un coup
	 * @param square      la coordonnée de la case au format {A-Z}{1-MAX_INT}
	 * @param indexPlayer index du joueur qui a joué le coup
	 * @return true si le coup a bien été enregistré, sinon false
	 * @throws IllegalArgumentException indexPlayer or de la borne ou case invalide
	 */
	@Override
	public boolean playAMove(String square, int indexPlayer) {
		boolean coup = board.playAMove(square, indexPlayer);
		if(coup){
			for (IPlayer player : players) {
				if (player instanceof IIA)
					((IIA) player).addMove(square, indexPlayer);
			}
			return true;
		}
		return false;
	}

	/**
	 * Test si un joueur a gagné
	 * @return true si un joueur a gagné, sinon false
	 */
	@Override
	public boolean isWon() {
		return board.isWon();
	}

	/**
	 * Remet le plateau à zéro
	 */
	@Override
	public void cleanBoard() {
		board.cleanBoard();
	}

	/**
	 * Retourne l'index du joueur ayant gagné
	 * @return l'index du joueur
	 */
	@Override
	public String getWinner() {
		if(isWon())
			return players[board.getWinner()-1].getName();
		return "";
	}

	/**
	 * Fait jouer un coup par une IA
	 * @param indexIA l'index de l'IA dans le tableau des joueurs (similaire à index Player pour les joueurs humain)
	 * @throws RuntimeException lorsque l'IA a joué un coup non valide, preuve d'une erreur dans l'IA
	 * @throws IllegalArgumentException lorsque l'indexIA ne concerne pas une IA, mais un joueur humain
	 * @throws ArrayIndexOutOfBoundsException lorsque l'indexIA dépasse les limites du tableau de joueurs
	 */
	@Override
	public void makeIAPlay(int indexIA) {
		if(indexIA <= 0 || indexIA > players.length)
			throw new ArrayIndexOutOfBoundsException();
		if(players[indexIA-1] instanceof IIA) {
			if(!playAMove(((IIA) players[indexIA-1]).generateMove(), indexIA))
				throw new RuntimeException("Problem with the AI.");
		}
		else {
			throw new IllegalArgumentException("Isn't an IA");
		}
	}

	@Override
	public String toString(){
		return this.board.toString();
	}
}
