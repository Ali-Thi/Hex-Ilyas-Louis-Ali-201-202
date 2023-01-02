package game.utilities;

import game.IIA;
import game.IPlayer;

import java.util.Arrays;

public class IA implements IIA, IPlayer {
    private static int sequence = 1;
    private final int[][] board;
    private int number;

    public IA(int side){
    	this.number=sequence;
    	sequence++;
        this.board = new int[side][side];
    	for(int x=0;x<side;x++) {
    		for(int y=0;y<side;y++) {
    			board[x][y]=0;
    		}
    	}
    }

    /**
     * Fait générer un coup par une IA
     * @return le coup généré
     * @throws RuntimeException tableau plein
     */
    @Override
    public String generateMove() {
        for(int x = 0 ; x < board.length ; ++x){
            for(int y = 0 ; y < board.length ; ++y){
                if(board[x][y] == 0){
                    return ((char) (y + (int) 'A')) + String.valueOf(x+1);
                }
            }
        }
        throw new RuntimeException("Plateau plein.");
    }

    /**
     * Actualise la matrice de l'IA représentant le plateau
     * @param square la case joué
     * @param indexPlayer le joueur ayant joué
     * @throws IllegalArgumentException indexPlayer or de la borne ou case invalide
     */
    @Override
    public boolean addMove(String square, int indexPlayer) {
        if (square.charAt(0) < 'A' || square.charAt(0) > (char) (this.board.length - 1 + (int) 'A') ) {
            throw new IllegalArgumentException("Premier caractère en dehors des limites.");
        }
        try{
            Integer.parseInt(square.substring(1));
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("L'index de la case est en dehors des limites.");
        }
    	
    	int y = (int) square.charAt(0) - (int) 'A';
        int x = Integer.parseInt(square.substring(1)) - 1;
    	if(board[x][y]==0) {
    		board[x][y]=indexPlayer;
    		return true;
    	}
        return false;
    }

    /**
     * Efface le contenu de la matrice
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
     * Test si l'instance courante est une IA
     * @return true si elle l'est, false sinon
     */
    @Override
    public boolean isIA() {
        return true;
    }

    /**
     * Retourne le nom du joueur
     * @return le nom
     */
    @Override
    public String getName() {
        return "IA"+number;
    }
}
