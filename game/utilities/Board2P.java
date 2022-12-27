package game.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Board2P implements game.IBoard {

	public Square[][] board;
	private ArrayList<LinkedList<Integer>> pathsPlayer1;
	private ArrayList<LinkedList<Integer>> pathsPlayer2;
	private int side;
	private int winnerIndex;

	/**
	 *
	 * @param side
	 */
	public Board2P(int side){
		this.side = side;
		this.board = new Square[this.side][this.side];
		for(int x=0; x<side; x++) {
    		for(int y=0; y<side; y++) {
    			this.board[x][y]=new Square();
    		}
    	}
		//Creation des deux ArrayList des deux joueurs 
		pathsPlayer1=new ArrayList<>();
		pathsPlayer2=new ArrayList<>();
	}


	public Board2P(int side, String str) {
		this(side);
		String[] lines = cut(str);
		for(int x=0; x<side; x++) {
			for(int y=0; y<side; y++) {
				this.board[x][y].setValue(lines[x].charAt(y)-'0'); // transforme la valeur entière d'un string en int
			}
		}
		pathsPlayer1=new ArrayList<>();
		pathsPlayer2=new ArrayList<>();
	}

	public boolean checkBorders(String square){
		if(square.charAt(0)<'A' ||
				square.charAt(0)> (char) (this.side-1 + (int) 'A') ||
				square.charAt(1) <= '0' ||
				square.charAt(1) > (char) (this.side + (int) '0')
		) return false;
		return true;
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
		//conversion du String en entier A=65 donc 65-65=0
		int y = square.charAt(0)- 'A';
		//conversion du String numéro en int
		int x = square.charAt(1) - '1';

		// checke les bordures
		if(!checkBorders(square)) {
			throw new NumberFormatException("Invalid square");
		}

    	if(board[x][y].getValue()==0) {
			this.board[x][y].setValue(indexPlayer);

			int[][] indexAdjacentSquare = new int[][]{
					new int[]{x - 1, y},
					new int[]{x + 1, y},
					new int[]{x, y - 1},
					new int[]{x + 1, y - 1},
					new int[]{x - 1, y + 1},
					new int[]{x, y + 1}
			};
			int indexPath = -1;

			for (int[] j : indexAdjacentSquare) {
				try {
					if (this.board[j[0]][j[1]].getValue() != 0) { //si la case est déjà occupée
						if (indexPath == -1) { //
							indexPath = this.board[j[0]][j[1]].getPathIndex();
							if (indexPlayer == 1) {
								this.pathsPlayer1.get(indexPath).add(x);
							}
							else
								this.pathsPlayer2.get(indexPath).add(y);
							this.board[x][y].setPathIndex(indexPath);
						} else {
							if (indexPlayer == 1) {
								this.pathsPlayer1.get(indexPath).addAll(this.pathsPlayer1.get(this.board[j[0]][j[1]].getPathIndex()));
								this.pathsPlayer1.remove(this.board[j[0]][j[1]].getPathIndex());
							} else {
								this.pathsPlayer2.get(indexPath).addAll(this.pathsPlayer2.get(this.board[j[0]][j[1]].getPathIndex()));
								this.pathsPlayer2.remove(this.board[j[0]][j[1]].getPathIndex());
							}
						}
					}
				} catch (ArrayIndexOutOfBoundsException ignored) {}
			}

			if (indexPath == -1) {
				if (indexPlayer == 1) {
					this.pathsPlayer1.add(new LinkedList<>(Collections.singleton(x)));
					this.board[x][y].setPathIndex(this.pathsPlayer1.size() - 1);
				} else {
					this.pathsPlayer2.add(new LinkedList<>(Collections.singleton(y)));
					this.board[x][y].setPathIndex(this.pathsPlayer2.size() - 1);
				}
			}
			return true;
		}else
			return false;

	}

	/**
	 * Check if the game is won by someone
	 *
	 * @return the winner's number, 0 otherwise
	 */
	@Override
	public boolean isWon() {
		for (LinkedList<Integer> list : pathsPlayer1) {
			//triage de la liste en ordre croissant
			Collections.sort(list);
			//si le premier est à 0 et que le dernier correspond a la taille du tableau -1
			//alors cela veut dire que son chemin est valable et qu'il a gagné
			if(list.getFirst()==0 && list.getLast()==board.length-1) {
				this.winnerIndex = 1;
				return true;
			}
		}
		for (LinkedList<Integer> list : pathsPlayer2) {
			Collections.sort(list);
			if(list.getFirst()==0 && list.getLast()==board.length-1) {
				this.winnerIndex = 2;
				return true;
			}
		}
		return false;
	}

	/**
	 * Clean the board
	 */
	@Override
	public void cleanBoard() {
		for(int x=0;x<this.side;x++) {
    		for(int y=0;y<this.side;y++) {
    			this.board[x][y]=new Square();
    		}
    	}
		pathsPlayer1.clear();
		pathsPlayer2.clear();
	}

	/**
	 * Return the index of the player who won, the index equals to the player's position in th player's order
	 *
	 * @return the index
	 */
	@Override
	public Integer getWinner() {
		return this.winnerIndex;
	}

	/*
	public boolean verif(int x,int y,int indexPlayer) {
		if(board[x-1][y].getPathIndex()==indexPlayer ||
				board[x+1][y].getPathIndex()==indexPlayer ||
				board[x-1][y+1].getPathIndex()==indexPlayer ||
			board[x][y+1].getPathIndex()==indexPlayer ||
				board[x+1][y-1].getPathIndex()==indexPlayer ||
				board[x][y-1].getPathIndex()==indexPlayer) {
		return true;
	}
		return false;
	}
	*/
	public String[] cut(String str) {
		String[] lignes = new String[side];
		for (int i = 0; i < side; ++i)
			lignes[i] = str.substring(i * side,
					(i + 1) * side);
		return lignes;
	}

	/**
	 * Return a string object representing the current object
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(" ");
		for (int i = 0; i <= this.side; ++i) {
			if (i > 0) {
				s.append('\n');
				s.append(i);
				for(int k = 0 ; k < i-1 ; ++k)
					s.append(" ");
			}
			for (int j = 0; j < this.side; ++j) {
				if (i == 0) s.append(" ").append((char) (j + (int) 'A'));
				else s.append(" " + this.board[i-1][j].toString());
			}
		}
		s.append('\n');
		return s.toString();
	}
}
