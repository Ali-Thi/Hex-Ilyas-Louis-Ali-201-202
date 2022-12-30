package IHM;

import game.Game;
import game.IGame;
import game.utilities.Board2P;
import game.utilities.HPlayer;

import java.util.Scanner;

public class UI {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("BIENVENUE DANS HEX !\nVeuillez choisir la taille du plateau\n");
		int length = scanner.nextInt();
		System.out.print("Quel est le pseudo du premier joueur ?\n");
		String name1 = scanner.nextLine().toLowerCase();
		System.out.print("Et celui de son adveraire sera ?\n");
		String name2 = scanner.nextLine().toLowerCase();

		IGame game = new Game(new Board2P(length, 2), new HPlayer(name1), new HPlayer(name2));

		String[] players = new String[]{name1, name2};
		boolean isMovePlayable;
		boolean isWon = game.isWon();
		String input;
		int[] indexsPlayer = new int[]{1, 2};
		int indexActualPlayer = indexsPlayer[0];

		while(!isWon) {
			System.out.print(game);
			System.out.print("Au tour de " + players[indexActualPlayer - 1] + " de jouer.\nQuel case choisis-tu ? ");
			input = scanner.nextLine();
			isMovePlayable = false;
			while(!isMovePlayable){
				try {
					isMovePlayable = game.playAMove(input, indexActualPlayer);
					if(!isMovePlayable) {
						System.out.print("Case déjà prise, veuillez en sélectionner une autre : ");
						input = scanner.nextLine();
					}
				} catch(NumberFormatException n){
					System.out.print("Erreur interne.");
					System.exit(1);
				} catch(RuntimeException r){
					System.out.print("Format invalide.\nVeuillez respectez les bornes et le format : (Lettre,Chiffre)\nExemple : H4\nQuel case choisis-tu ? ");
					input = scanner.nextLine();
				}
			}
			isWon = game.isWon();
			indexActualPlayer = indexsPlayer[0] + indexsPlayer[1] - indexActualPlayer;
		}
		System.out.println("\n\n" + players[indexActualPlayer] + " a gagné !");
	}
}


