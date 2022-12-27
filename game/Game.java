package game;

public class Game implements IGame {
	private IBoard board;
	private IPlayer[] players;

	public Game(IBoard board, IPlayer player1, IPlayer player2){
		this.board = board;
		this.players = new IPlayer[]{player1, player2};
	}

	/**
	 * Allow to play a move on the board
	 * @param square      the square being played
	 * @param indexPlayer index of the player who's playing
	 * @return true if the move is legal, false otherwise
	 * @throws RuntimeException      unknown player
	 * @throws NumberFormatException invalid square
	 */
	@Override
	public boolean playAMove(String square, int indexPlayer) {
		return board.playAMove(square, indexPlayer);
	}

	/**
	 * Check if the game is won by someone
	 * @return the winner's number, 0 otherwise
	 */
	@Override
	public boolean isWon() {
		return board.isWon();
	}

	/**
	 * Clean the board
	 */
	@Override
	public void cleanBoard() {
		board.cleanBoard();
	}

	/**
	 * Return the index of the player who won, the index equals to the player's position in th player's order
	 * @return the index
	 */
	@Override
	public String getWinner() {
		return players[board.getWinner()].getName();
	}

	/**
	 * Make an IA play
	 * @param indexIA the index of the IA which have to play
	 * @return the move played by the IA
	 */
	@Override
	public String makeIAPlay(int indexIA) {
		if(players[indexIA] instanceof IIA)
			return ((IIA) players[indexIA]).generateMove();
		throw new RuntimeException("Isn't an IA");
	}

	@Override
	public String toString(){
		return this.board.toString();
	}
}
