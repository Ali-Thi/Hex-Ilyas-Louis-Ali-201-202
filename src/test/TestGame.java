package test;

import game.IGame;
import game.Game;
import game.utilities.Board2P;
import game.utilities.HPlayer;
import game.utilities.IA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGame {
    private int side = 8;
    private final int nbPlayer = 2;

    /**
     * test the initialization of a new instance of Game
     */
    @Test
    public void test1(){
        IGame game = new Game(new Board2P(side, nbPlayer), new HPlayer("John"), new HPlayer("Alex"));
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
     * test function isWon and getWinner
     */
    @Test
    public void test2(){
        Board2P board = new Board2P(side, nbPlayer);
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
     * test humain vs ia
     */
    @Test
    public void test3(){
        Board2P board = new Board2P(side, nbPlayer);
        IGame game = new Game(board, new HPlayer("John"), new IA(side));
        game.playAMove("C3", 1);
        for(int i = 0 ; i < side*side-1 ; ++i){
            game.makeIAPlay(2);
        }

        assertTrue(game.isWon());
        assertEquals("IA1", game.getWinner());
    }

    /**
     * test erreurs
     */
    @Test
    public void test4(){
        Board2P board = new Board2P(side, nbPlayer);
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
}