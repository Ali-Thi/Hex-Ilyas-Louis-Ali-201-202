package game.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Board2P implements game.IBoard {
	private Square[][] board;
	private ArrayList<LinkedList<Integer>> pathsPlayer1;
	private ArrayList<LinkedList<Integer>> pathsPlayer2;
	private int side;

	private int winnerIndex;

	public Board2P(int side){
		this.side = side;
		this.board = new Square[this.side][this.side];
		for(int x=0; x<side; x++) {
    		for(int y=0; y<side; y++) {
    			this.board[x][y]=new Square();
    		}
    	}
		//Creation des deux ArrayList des deux joueurs 
		pathsPlayer1=new ArrayList<LinkedList<Integer>>();
		pathsPlayer2=new ArrayList<LinkedList<Integer>>();
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
		//separation de la chaine de caractères en deux parties
		//entrée ressemble a B-1
		
		//A FAIRE : verif entrée pour ne pas dépasser taille max du tableau !!
		String[] separation = square.split("-");
		if(square.charAt(0)<'A' || 
				square.charAt(0)> (char) (this.side-1 + (int) 'A') ||
				square.charAt(2) < '0' ||
				square.charAt(2) > (char) (this.side + (int) '0')
				)
		{
			throw new NumberFormatException("Invalid square");
		}
		
		//convertion du String en entier A=65 donc 65-65=0
    	int x = Integer.parseInt(separation[0])-(int) 'A';
    	//convertion du String numéro en int
    	int y = Integer.parseInt(separation[1]) -(int) '1';
    	
    	//verif qu'il n'y a pas déjà de pion sur la case
    	if(board[x][y].getPathIndex()==0) {
    		//ajout dans le board l'index du joueur qui vient de poser son pion
    		board[x][y].setPathIndex(indexPlayer);
    		
    		
    		//verif si il a pas déjà des pions autour du pion posé
    		
    		/* case alentour d'une case sachant x,y case de base
    		 * x-1,y
    		 * x+1,y
    		 * x-1 y+1
    		 * x,y+1
    		 * x+1,y-1
    		 * x,y-1
    		 */
    		//fonction
    		
    		//comment savoir quel joueur joue?
    		if(verif(x,y,indexPlayer)) {
    			if(indexPlayer ==1) {
	    			pathsPlayer1.get(pathsPlayer1.lastIndexOf(pathsPlayer1)).add(x);
	    			//iswon activation
	    			return true;
    			}
    			else {
    				pathsPlayer2.get(pathsPlayer2.lastIndexOf(pathsPlayer2)).add(x);
        			//iswon activation
        			return true;
    			}
    		}
    		else {
    			if(indexPlayer==1) {
    				//creation linked list
            		pathsPlayer1.add(new LinkedList<Integer>());
            		//ajout int dans la dernière linked list crée
            		pathsPlayer1.get(pathsPlayer1.lastIndexOf(pathsPlayer1)).add(x);
            		return true;
    			}
    			else {
    				//creation linked list
            		pathsPlayer2.add(new LinkedList<Integer>());
            		//ajout int dans la dernière linked list crée
            		pathsPlayer2.get(pathsPlayer2.lastIndexOf(pathsPlayer2)).add(x);
            		return true;
    			}
    		}
    	}
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
	
	public boolean verif(int x,int y,int indexPlayer) {
		if(board[x-1][y].getValue()==indexPlayer ||
				board[x+1][y].getValue()==indexPlayer ||
				board[x-1][y+1].getValue()==indexPlayer ||
				board[x][y+1].getValue()==indexPlayer ||
				board[x+1][y-1].getValue()==indexPlayer ||
				board[x][y-1].getValue()==indexPlayer) {
			return true;
		}
		return false;
	}

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
