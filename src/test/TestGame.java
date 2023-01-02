package test;

import game.IGame;
import game.Game;
import game.utilities.Board;
import game.utilities.HPlayer;
import game.utilities.IA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGame {
    private int side = 8;
    private final int nbPlayer = 2;

    /**
     * Test l'initialisation d'une instance de IA
     */
    @Test
    public void test1(){
        IGame game = new Game(new Board(side, nbPlayer), new HPlayer("John"), new HPlayer("Alex"));
        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . .\n" +
                "2  . . . . . . . .\n" +
                "3   . . . . . . . .\n" +
                "4    . . . . . . . .\n" +
                "5     . . . . . . . .\n" +
                "6      . . . . . . . .\n" +
                "7       . . . . . . . .\n" +
                "8        . . . . . . . .\n", game.toString());
    }

    /**
     * Test les fonctions isWon et getWinner
     */
    @Test
    public void test2(){
        Board board = new Board(side, nbPlayer);
        IGame game = new Game(board, new HPlayer("John"), new HPlayer("Alex"));

        for(int i =1;i<=side;i++) {
            board.playAMove('H'+String.valueOf(i), 1);
        }
        System.out.println(board);
        /*
          A B C D E F G H
        1 . . . . . . . 1
        2  . . . . . . . 1
        3   . . . . . . . 1
        4    . . . . . . . 1
        5     . . . . . . . 1
        6      . . . . . . . 1
        7       . . . . . . . 1
        8        . . . . . . . 1
        */

        assertTrue(game.isWon());
        assertEquals("John", game.getWinner());

        game.cleanBoard();

        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . .\n" +
                "2  . . . . . . . .\n" +
                "3   . . . . . . . .\n" +
                "4    . . . . . . . .\n" +
                "5     . . . . . . . .\n" +
                "6      . . . . . . . .\n" +
                "7       . . . . . . . .\n" +
                "8        . . . . . . . .\n", game.toString());

        for(char c : "ABCDEFGH".toCharArray()) {
            board.playAMove(c+String.valueOf(1), 2);
        }

        System.out.println(board);
        /*
          A B C D E F G H
        1 2 2 2 2 2 2 2 2
        2  . . . . . . . .
        3   . . . . . . . .
        4    . . . . . . . .
        5     . . . . . . . .
        6      . . . . . . . .
        7       . . . . . . . .
        8        . . . . . . . .
        */

        assertTrue(game.isWon());
        assertEquals("Alex", game.getWinner());
    }

    /**
     * Test un humain jouant contre une ia
     */
    @Test
    public void test3(){
        Board board = new Board(side, nbPlayer);
        IGame game = new Game(board, new HPlayer("John"), new IA(side));
        game.playAMove("C3", 1);
        try {
            for (int i = 0; i < side * side - 1; ++i) {
                game.makeIAPlay(2);
            }
        } catch(RuntimeException e){
            if(e.getMessage() != "Partie finie"){
                fail(e.getMessage());
            }
        }

        assertTrue(game.isWon());
        assertEquals("IA1", game.getWinner());
    }

    /**
     * Test la levée d'exceptions
     */
    @Test
    public void test4(){
        Board board = new Board(side, nbPlayer);
        IGame game = new Game(board, new HPlayer("John"), new IA(side));

        assertEquals("", game.getWinner());
        assertTrue(game.playAMove("C1", 1));
        assertFalse(game.playAMove("C1", 1));

        //Mauvaise case
        try{
            game.playAMove("Z0", 1);
            fail();
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }

        //Mauvais indexPlayer
        try{
            game.playAMove("A2", 4);
            fail();
        }catch(IllegalArgumentException e){
            assertTrue(true);
        }

        //Mauvaise indexIA
        try{
            game.makeIAPlay(4);
            fail();
        }catch(ArrayIndexOutOfBoundsException e){
            assertTrue(true);
        }

    }

    /**
     * Test la fonction isBoardFull
     */
    @Test
    public void test5(){
        Board board = new Board(side, nbPlayer);
        IGame game = new Game(board, new HPlayer("John"), new HPlayer("Patrick"));

        assertFalse(game.isBoardFull());
        for (char c : "ABCDEFGH".toCharArray()) {
            for (char n : "12345678".toCharArray()) {
                if(n == '8'){
                    game.playAMove(c + String.valueOf(n), 2);
                } else {
                    game.playAMove(c + String.valueOf(n), 1);
                }
            }
        }
        assertEquals("  A B C D E F G H\n" +
                "1 1 1 1 1 1 1 1 1\n" +
                "2  1 1 1 1 1 1 1 1\n" +
                "3   1 1 1 1 1 1 1 1\n" +
                "4    1 1 1 1 1 1 1 1\n" +
                "5     1 1 1 1 1 1 1 1\n" +
                "6      1 1 1 1 1 1 1 1\n" +
                "7       1 1 1 1 1 1 1 1\n" +
                "8        2 2 2 2 2 2 2 2\n", game.toString());

        assertTrue(game.isBoardFull());
    }

}