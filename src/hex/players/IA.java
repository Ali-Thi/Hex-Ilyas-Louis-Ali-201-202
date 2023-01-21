package hex.players;

import hex.IBoard;
import hex.IPlayer;

import java.util.Collections;
import java.util.LinkedList;

public class IA implements IPlayer {
    private static int sequence = 1;
    private final String name;
    private final LinkedList<String> squareFree;

    public IA(){
    	this.name="IA" + sequence;
        this.squareFree = new LinkedList<>();
    	sequence++;
    }

    /**
     * Retourne le nom du joueur
     * @return le nom
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Permet au joueur de choisir une case sur laquelle poser son pio
     * @return la chaine correspondante à la case sous la forme [A-Z||a-z][1-MAX_INT]
     */
    @Override
    public String chooseSquare(int side) {
        if(this.squareFree.isEmpty()){
            for (int i = 0 ; i < side ; ++i){
                for(int j = 0 ; j < side ; ++j){
                    String x = String.valueOf((char)('A' + i));
                    String y = String.valueOf(j+1);
                    this.squareFree.push(x+y);
                }
            }
            Collections.shuffle(this.squareFree);
        }
        return this.squareFree.pop();
    }
}
