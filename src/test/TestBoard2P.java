package test;

import game.IBoard;
import game.utilities.Board2P;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard2P {
    private final int side = 8;
    private final int nbPlayer = 2;
    private IBoard board = new Board2P(side, nbPlayer);

    /**
     * test the initialization of a new instance of Board2P
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
                "8        . . . . . . . .\n", board.toString());

        board = new Board2P(3, nbPlayer);


        assertEquals("  A B C\n" +
                "1 . . .\n" +
                "2  . . .\n" +
                "3   . . .\n", board.toString());


        board = new Board2P(side, nbPlayer);
        board.playAMove("H1",1);
        board.playAMove("H2",1);
        board.playAMove("H3",1);
        board.playAMove("H4",1);
        board.playAMove("H5",1);
        board.playAMove("H6",1);
        board.playAMove("H7",1);
        board.playAMove("H8",1);


        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . 1\n" +
                "2  . . . . . . . 1\n" +
                "3   . . . . . . . 1\n" +
                "4    . . . . . . . 1\n" +
                "5     . . . . . . . 1\n" +
                "6      . . . . . . . 1\n" +
                "7       . . . . . . . 1\n" +
                "8        . . . . . . . 1\n", board.toString());


    }

    /**
     * Test the addition of new pawn on the board, and the function cleanBoard
     */
    @Test
    public void test2(){
        for(char c : "ABCDEFGH".toCharArray()) {
            for(char n : "12345678".toCharArray()) {
                board.playAMove(c+String.valueOf(n), 1);
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
                "8        1 1 1 1 1 1 1 1\n", board.toString());

        board.playAMove("E2", 2);
        board.playAMove("H3", 2);
        board.playAMove("A5", 2);

        assertEquals("  A B C D E F G H\n" +
                "1 1 1 1 1 1 1 1 1\n" +
                "2  1 1 1 1 1 1 1 1\n" +
                "3   1 1 1 1 1 1 1 1\n" +
                "4    1 1 1 1 1 1 1 1\n" +
                "5     1 1 1 1 1 1 1 1\n" +
                "6      1 1 1 1 1 1 1 1\n" +
                "7       1 1 1 1 1 1 1 1\n" +
                "8        1 1 1 1 1 1 1 1\n", board.toString());

        board.cleanBoard();

        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . .\n" +
                "2  . . . . . . . .\n" +
                "3   . . . . . . . .\n" +
                "4    . . . . . . . .\n" +
                "5     . . . . . . . .\n" +
                "6      . . . . . . . .\n" +
                "7       . . . . . . . .\n" +
                "8        . . . . . . . .\n", board.toString());
    }


    /**
     * Test the function isWon and getWinner for straight path
     */
    @Test
    public void test3(){
        board=new Board2P(side, 2);
        for(int i =1;i<=side;i++) {
            board.playAMove('H'+String.valueOf(i), 1);
        }
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

        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . 1\n" +
                "2  . . . . . . . 1\n" +
                "3   . . . . . . . 1\n" +
                "4    . . . . . . . 1\n" +
                "5     . . . . . . . 1\n" +
                "6      . . . . . . . 1\n" +
                "7       . . . . . . . 1\n" +
                "8        . . . . . . . 1\n", board.toString());
        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());

        board.cleanBoard();

        for(char c : "ABCDEFGH".toCharArray()) {
            board.playAMove(c+String.valueOf(1), 2);
        }
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

        assertTrue(board.isWon());
        assertEquals(2, board.getWinner());
    }

    /**
     * Test the function isWon and getWinner for diagonal path
     */
    @Test
    public void test4(){
        board.playAMove("H1",2);
        board.playAMove("G2",2);
        board.playAMove("F3",2);
        board.playAMove("E4",2);
        board.playAMove("D5",2);
        board.playAMove("C6",2);
        board.playAMove("B7",2);
        board.playAMove("A8",2);
        /*
          A B C D E F G H
        1 . . . . . . . 2
        2  . . . . . . 2 .
        3   . . . . . 2 . .
        4    . . . . 2 . . .
        5     . . . 2 . . . .
        6      . . 2 . . . . .
        7       . 2 . . . . . .
        8        2 . . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(2, board.getWinner());

        board.cleanBoard();

        board.playAMove("H1",1);
        board.playAMove("G2",1);
        board.playAMove("F3",1);
        board.playAMove("E4",1);
        board.playAMove("D5",1);
        board.playAMove("C6",1);
        board.playAMove("B7",1);
        board.playAMove("A8",1);

        /*
          A B C D E F G H
        1 . . . . . . . 1
        2  . . . . . . 1 .
        3   . . . . . 1 . .
        4    . . . . 1 . . .
        5     . . . 1 . . . .
        6      . . 1 . . . . .
        7       . 1 . . . . . .
        8        1 . . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());
    }

    /**
     * Test the function isWon and getWinner for curved path
     */
    @Test
    public void test5(){
        Board2P board=new Board2P(side, nbPlayer);
        board.playAMove("H1",2);
        board.playAMove("F1",2);
        board.playAMove("G2",2);
        board.playAMove("F2",2);
        board.playAMove("E2",2);
        board.playAMove("C3",2);
        board.playAMove("B4",2);
        board.playAMove("E4",2);
        board.playAMove("B5",2);
        board.playAMove("D5",2);
        board.playAMove("B6",2);
        board.playAMove("C6",2);
        board.playAMove("D6",2);
        board.playAMove("B7",2);
        board.playAMove("C7",2);
        board.playAMove("A8",2);
        board.playAMove("B8",2);
        board.playAMove("D2",2);
        /*
          A B C D E F G H
        1 . . . . . 2 . 2
        2  . . . 2 2 2 2 .
        3   . . 2 . . . . .
        4    . 2 . . 2 . . .
        5     . 2 . 2 . . . .
        6      . 2 2 2 . . . .
        7       . 2 2 . . . . .
        8        2 2 . . . . . .
        */
        assertTrue(board.isWon());
        assertEquals(2, board.getWinner());


        board.cleanBoard();


        board.playAMove("F1",1);
        board.playAMove("H1",1);
        board.playAMove("D2",1);
        board.playAMove("E2",1);
        board.playAMove("F2",1);
        board.playAMove("G2",1);
        board.playAMove("C3",1);
        board.playAMove("B4",1);
        board.playAMove("E4",1);
        board.playAMove("B5",1);
        board.playAMove("D5",1);
        board.playAMove("B6",1);
        board.playAMove("C6",1);
        board.playAMove("D6",1);
        board.playAMove("B7",1);
        board.playAMove("C7",1);
        board.playAMove("A8",1);
        board.playAMove("B8",1);

        System.out.println(board);
        /*
          A B C D E F G H
        1 . . . . . 1 . 1
        2  . . . 1 1 1 1 .
        3   . . 1 . . . . .
        4    . 1 . . 1 . . .
        5     . 1 . 1 . . . .
        6      . 1 1 1 . . . .
        7       . 1 1 . . . . .
        8        1 1 . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());
    }

    /**
     * Test that there's no winner when isWon() returns false and ???
     */
    @Test
    public void test6(){
        board.cleanBoard();

        assertFalse(board.isWon());
        assertEquals(board.getWinner(),0);

        try{
            board.playAMove("M0", 1);
            fail();
        } catch(IllegalArgumentException r){
            assertTrue(true);
        }

        try{
            board.playAMove("A1", 4);
        } catch(IllegalArgumentException r){
            assertTrue(true);
        }
    }
}