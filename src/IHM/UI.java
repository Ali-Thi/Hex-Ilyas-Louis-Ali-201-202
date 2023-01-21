package IHM;

import hex.Hex;
import hex.IHex;
import hex.IPlayer;
import hex.board.Board;
import hex.players.HPlayer;
import hex.players.IA;

import java.util.Scanner;

public class UI {
    private static IHex game;
    private static String[] playersName;
    private static boolean isWon;
    private static boolean isMovePlayable;
    private static int[] indexPlayer;
    private static int indexActualPlayer;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int length;
        int modeDeJeu;
        String name1 = "";
        String name2 = "";

        System.out.print("BIENVENUE DANS HEX !\nVeuillez choisir la taille du plateau : ");
        length = scanner.nextInt();
        while(length <= 0 || length >= Integer.MAX_VALUE){
            System.out.print("La valeur entré n'est pas légal, veuillez choisir une taille entre 1 inclus et " + Integer.MAX_VALUE + " exclus : ");
            length = scanner.nextInt();
        }

        System.out.println("\nChoisissez votre mode de jeu :\n" +
                "	- 0 = Joueur vs Joueur\n" +
                "	- 1 = Joueur vs Programme\n" +
                "	- 2 = Programme vs Programme");
        modeDeJeu = scanner.nextInt();
        while (modeDeJeu < 0 || modeDeJeu > 2) {
            System.out.println("Erreur d'entrée : veuillez entrez une des 3 valeurs possibles (0, 1 ou 2) ");
            modeDeJeu = scanner.nextInt();
        }
        if (modeDeJeu != 2) {
            System.out.print("Quel est le pseudo du premier joueur ? ");
            name1 = scanner.nextLine().toLowerCase();
            while (name1 == "") {
                System.out.println("Le pseudo ne peut être vide, veuillez entrer un pseudo valide : ");
                name1 = scanner.nextLine().toLowerCase();
            }
            name1 = name1.substring(0, 1).toUpperCase() + name1.substring(1);

            if (modeDeJeu == 0) {
                System.out.print("Et celui de son adveraire sera ?\n");
                name2 = scanner.nextLine().toLowerCase();
                while (name2 == "") {
                    System.out.println("Le pseudo ne peut être vide, veuillez entrer un pseudo valide : ");
                    name2 = scanner.nextLine().toLowerCase();
                }
                name2 = name2.substring(0, 1).toUpperCase() + name2.substring(1);
            }
        }

        switch (modeDeJeu) {
            case 0 -> initParam(length, new HPlayer(name1, new InputHPlayer()), new HPlayer(name2, new InputHPlayer()));
            case 1 -> initParam(length, new HPlayer(name1, new InputHPlayer()), new IA());
            case 2 -> initParam(length, new IA(), new IA());
        }

        while (!isWon) {
            System.out.println(game);
            System.out.println("Au tour de " + playersName[indexActualPlayer] + " de jouer.");
            if(modeDeJeu == 0 || (modeDeJeu == 1 && indexActualPlayer == indexPlayer[0])){
                System.out.print("Quel case choisis-tu ? ");
            }

            while (!isMovePlayable) {
                try {
                    isMovePlayable = game.placeAPawn(indexActualPlayer);
                    if (!isMovePlayable) {
                        System.out.print("Case déjà prise, veuillez en sélectionner une autre : ");
                    }
                } catch (IllegalArgumentException i) {
                    System.out.print(i.getMessage() + "\nVeuillez respectez les bornes et le format : (Lettre,Chiffre)\nExemple : H4\nQuel case choisis-tu ? ");
                }
            }

            isMovePlayable = false;
            isWon = game.isWon();
            if (!isWon)
                indexActualPlayer = indexPlayer[0] + indexPlayer[1] - indexActualPlayer;
        }
        System.out.println(game);
        System.out.println("\n\n" + playersName[indexActualPlayer] + " a gagné !");
    }

    private static void initParam(int length, IPlayer p1, IPlayer p2) {
        game = new Hex(new Board(length), new IPlayer[]{p1, p2});
        playersName = new String[]{p1.getName(), p2.getName()};
        isWon = false;
        isMovePlayable = false;
        indexPlayer = new int[]{0, 1};
        indexActualPlayer = indexPlayer[0];
    }
}


