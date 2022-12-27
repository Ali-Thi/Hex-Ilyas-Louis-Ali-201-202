package game.utilities;

import game.IIA;
import game.IPlayer;
 	
public class IA implements IIA, IPlayer {
    private static int sequence;
    public int[][] board;
    private int number;

    public IA(int side){
    	this.number=sequence;
    	this.sequence++;
    	for(int x=0;x<side;x++) {
    		for(int y=0;y<side;y++) {
    			board[x][y]=0;
    		}
    	}
    }

    /**
     * Generate a move
     *
     * @return the move
     */
    @Override
    public String generateMove() {
    	String string="";
    	int x = (int)Math.random() * ( board.length-1 - 0 );
    	int y = (int)Math.random() * ( board.length-1 - 0 );
    	string+=(char)('A'+x)+"-"+y;
        return string;
    }

    /**
     * Actualize the IA's integer matrice which represent the board;
     *
     * @param square the square been played
     * @param indexPlayer the player's index who played
     */
    @Override
    public boolean addMove(String square, int indexPlayer) {
    	if (square.length() != 2 ||
                square.charAt(0) < 'A' ||
                square.charAt(0) > (char) (board.length-1 + (int) 'A') ||
                square.charAt(1) < '0' ||
                square.charAt(1) > (char) (board.length-1 + (int) '0')
                )
            throw new NumberFormatException("Invalid square");
        else if (indexPlayer != 1 && indexPlayer != 2)
            throw new RuntimeException("Unknown player");
    	
    	int y = (int) square.charAt(0) - (int) 'A';
        int x = (int) square.charAt(1) - (int) '1';
    	if(board[x][y]==0) {
    		board[x][y]=indexPlayer;
    		return true;
    	}
        return false;
    }

    /**
     * Clean the board
     */
    @Override
    public void cleanBoard() {
    	for(int x=0;x<board.length;x++) {
    		for(int y=0;y<board.length;y++) {
    			board[x][y]=0;
    		}
    	}
    }

    /**
     * Check if the instance is an IA
     *
     * @return true if it is, false otherwise
     */
    @Override
    public boolean isIA() {
        return true;
    }

    /**
     * Return the name of the player
     *
     * @return the name
     */
    @Override
    public String getName() {
        return "IA"+number;
    }
}
