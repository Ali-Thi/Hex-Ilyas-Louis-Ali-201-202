package test;

import game.IGame;
import game.Game;
import game.utilities.Board2P;
import game.utilities.HPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGame {
    private int side = 8;
    private IGame game = new Game(new Board2P(side), new HPlayer("John"), new HPlayer("Alex"));

    @Test
    public void test(){
        test1();
        test2();
    }

    /**
     * test the initialization of a new instance of Game
     */
    @Test
    public void test1(){
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
        String str = "";
        for (int i = 0 ; i < side ; ++i)
            str += "00000001";

        IGame game = new Game(new Board2P(8,str),
                new HPlayer("John"),
                new HPlayer("Alex"));

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
    }

    /**
     * test function isWon and getWinner
     */
    @Test
    public void test3(){
        IGame game = new Game(new Board2P(8),
                new HPlayer("John"),
                new HPlayer("Alex"));

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
    }
}