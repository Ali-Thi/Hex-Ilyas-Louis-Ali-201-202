package hex;

public class Hex implements IHex {
	private final IBoard board;
	private final IPlayer[] players;

	public Hex(IBoard board, IPlayer[] players){
		this.board = board;
		this.players = players;
	}

	/**
	 * Permet de jouer un coup
	 * @param indexPlayer index du joueur qui a joué le coup
	 * @throws IllegalArgumentException indexPlayer or de la borne
	 * @throws RuntimeException         case invalide
	 */
	@Override
	public boolean placeAPawn(int indexPlayer) {
		if(indexPlayer >= players.length || indexPlayer < 0)
			throw new IllegalArgumentException("indexPlayer or de la borne");
		return board.placeAPawn(players[indexPlayer].chooseSquare(board.getSide()), indexPlayer);
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
			return players[board.getWinner()].getName();
		return "";
	}

	@Override
	public String toString(){
		return this.board.toString();
	}
}
