package IHM;

import game.Game;
import game.IGame;
import game.IPlayer;
import game.utilities.Board;
import game.utilities.HPlayer;
import game.utilities.IA;

import java.util.Scanner;

public class UI {
	private static IGame game;
	private static String[] playersName;
	private static boolean isMovePlayable;
	private static boolean isWon;
	private static boolean isBoardFull;
	private static String input;
	private static int[] indexPlayer;
	private static int indexActualPlayer;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int length;
		int modeDeJeu;
		String name1 = "";
		String name2 = "";

		System.out.print("BIENVENUE DANS HEX !\nVeuillez choisir la taille du plateau :\n");
		System.out.println("	- 0 = 11x11\n	- 1 = 14x14(recommandé)\n	- 2 = 19x19");
		length = scanner.nextInt();
		while (length < 0 || length > 2) {
			System.out.println("Erreur d'entrée : veuillez entrez une des 3 valeurs possibles (0, 1 ou 2) ");
			length = scanner.nextInt();
		}
		switch (length) {
			case 0 -> {
				length = 11;
			}
			case 1 -> {
				length = 14;
			}
			case 2 -> {
				length = 19;
			}
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
			case 0 -> {
				initParam(length, new HPlayer(name1), new HPlayer(name2));
				while (!isWon && !isBoardFull) {
					tourDunJoueur();
					isWon = game.isWon();
					isBoardFull = game.isBoardFull();
					if (!isWon)
						indexActualPlayer = indexPlayer[0] + indexPlayer[1] - indexActualPlayer;
				}
				System.out.println(game);
				if (isBoardFull) {
					System.out.println("Match nul.");
				} else if (isWon) {
					System.out.println("\n\n" + playersName[indexActualPlayer - 1] + " a gagné !");
				}
			}

			case 1 -> {
				initParam(length, new HPlayer(name1), new IA(length));
				while (!isWon && !isBoardFull) {
					if (indexActualPlayer == indexPlayer[0])
						tourDunJoueur();
					else
						tourDuProgramme();
					isWon = game.isWon();
					isBoardFull = game.isBoardFull();
					if (!isWon)
						indexActualPlayer = indexPlayer[0] + indexPlayer[1] - indexActualPlayer;
				}
				System.out.println(game);
				if (isBoardFull) {
					System.out.println("Match nul.");
				} else if (isWon) {
					System.out.println("\n\n" + playersName[indexActualPlayer - 1] + " a gagné !");
				}
			}

			case 2 -> {
				initParam(length, new IA(length), new IA(length));
				while (!isWon && !isBoardFull) {
					tourDuProgramme();
					isWon = game.isWon();
					isBoardFull = game.isBoardFull();
					if (!isWon)
						indexActualPlayer = indexPlayer[0] + indexPlayer[1] - indexActualPlayer;
				}
				System.out.println(game);
				if (isBoardFull) {
					System.out.println("Match nul.");
				} else if (isWon) {
					System.out.println("\n\n" + playersName[indexActualPlayer - 1] + " a gagné !");
				}
			}
		}
	}

	private static void initParam(int length, IPlayer p1, IPlayer p2){
		game = new Game(new Board(length, 2), p1, p2);
		playersName = new String[]{p1.getName(), p2.getName()};
		isWon = false;
		isBoardFull = false;
		indexPlayer = new int[]{1, 2};
		indexActualPlayer = indexPlayer[0];
	}

	private static void tourDunJoueur(){
		System.out.print(game);
		System.out.print("Au tour de " + playersName[indexActualPlayer - 1] + " de jouer.\nQuel case choisis-tu ? ");
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
				System.out.print(n);
				System.exit(1);
			} catch(RuntimeException r){
				System.out.print("Format invalide.\nVeuillez respectez les bornes et le format : (Lettre,Chiffre)\nExemple : H4\nQuel case choisis-tu ? ");
				input = scanner.nextLine();
			}
		}
	}

	private static void tourDuProgramme(){
		game.makeIAPlay(indexActualPlayer);
		System.out.println(game);
	}
}


