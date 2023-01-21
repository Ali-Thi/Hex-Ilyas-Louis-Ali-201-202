package IHM;

import hex.players.IInput;
import java.util.Scanner;

public class InputHPlayer implements IInput {

    /**
     * Permet de lire la saisie de l'utilisateur
     * @return la saisie
     */
    @Override
    public String input() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
