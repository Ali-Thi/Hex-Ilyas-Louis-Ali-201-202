package game.utilities;

import java.util.Arrays;

/**
 * Represent a square of the board
 */
public class Square {
    public enum PossbileValue {Vide, Player1, Player2}; //valeurs possibles de la case
    private PossbileValue value; //valeur de la case

    public Square(){
    	value = PossbileValue.Vide;
    }

    /**
     * Retourne la valeur de la case
     * @return la valeur de la case
     */
    public PossbileValue getValue() {
        return this.value;
    }

    /**
     * Modifie la valeur de la case uniquement si sa valeur avant modification est Vide
     * @param indexPlayer le joueur ayant posé son pion sur cette case
     * @return true si la valeur a été modifiée, false sinon
     */
    public boolean setValue(int indexPlayer) {
        if(indexPlayer <= 0) throw new IllegalArgumentException();
        if (value == PossbileValue.Vide) {
            this.value = PossbileValue.values()[indexPlayer];
            return true;
        }
        return false;
    }


    @Override
    public String toString(){
        if(this.value != PossbileValue.Vide)
            return String.valueOf(Arrays.stream(PossbileValue.values()).toList().indexOf(this.value));
        return ".";
    }
}
