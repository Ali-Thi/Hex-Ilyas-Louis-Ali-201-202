package game.utilities;

import game.IBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Board2P implements IBoard {

	public Board2P(int side){
	}

	public Board2P(int side, String board){}

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
		return false;
	}

	/**
	 * Check if the game is won by someone
	 * @return the winner's number, 0 otherwise
	 */
	@Override
	public boolean isWon() {
		return false;
	}

	/**
	 * Clean the board
	 */
	@Override
	public void cleanBoard() {

	}

	/**
	 * Return the index of the player who won, the index equals to the player's position in th player's order
	 * @return the index
	 */
	@Override
	public Integer getWinner() {
		return 0;
	}

	/**
	 * Returns a string representation of the object
	 * @return the string
	 */
	@Override
	public String toString(){return "";}
}