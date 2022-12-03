package game;

public interface IBoard {
	/**
	 * Allow to play a move on the board
	 * @param square the square being played
	 * @param indexPlayer index of the player who's playing
	 * @return true if the move is legal, false otherwise
	 * @throws RuntimeException unknown player
	 * @throws NumberFormatException invalid square
	 */
	boolean playAMove(String square, int indexPlayer);

	/**
	 * Check if the game is won by someone
	 * @return the winner's number, 0 otherwise
	 */
	boolean isWon();

	/**
	 * Clean the board
	 */
	void cleanBoard();

	/**
	 * Return the index of the player who won, the index equals to the player's position in th player's order, null if nobody won
	 * @return the index
	 */
	Integer getWinner();
}