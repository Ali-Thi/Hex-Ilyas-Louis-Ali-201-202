package test;

import game.IBoard;
import game.utilities.Board2P;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard2P {
    private final int side = 8;
    private IBoard board = new Board2P(side);

    @Test
    public void test(){
        //test1();
        test2();
        //test3();
        //test4();
        //test5();
    }

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

        board = new Board2P(3);


        assertEquals("  A B C\n" +
                "1 . . .\n" +
                "2  . . .\n" +
                "3   . . .\n", board.toString());


        board = new Board2P(8);
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
        String str = "";
        for (int i = 0 ; i < side ; ++i)
            str += "00000001";
        board = new Board2P(side,str);
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

        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());

        board.cleanBoard();

        str = "22222222";
        for (int i = 0 ; i < side-1 ; ++i)
            str += "00000000";
        board = new Board2P(side, str);

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
        String str = "";
        StringBuilder line = new StringBuilder("00000000");
        for (int i = 0 ; i < side ; ++i)
            str += line.replace(side-1-i, side-i, "2");
        board = new Board2P(side, str);

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

        str.replaceAll("2", "1");
        board = new Board2P(side, str);

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
        String str = "0000020200022220002000000200200002020000022200000220000022000000";
        board = new Board2P(side, str);

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

        str = "0000010100011110001000000100100001010000011100000110000011000000";
        board = new Board2P(side, str);

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
     * Test the function isWon and getWinner for curved path
     */
    @Test
    public void test6(){
        board.cleanBoard();

        assertFalse(board.isWon());
        assertNull(board.getWinner());

        try{
            board.playAMove("M0", 1);
            fail();
        } catch(RuntimeException r){
            assertTrue(true);
        }

        try{
            board.playAMove("A1", 4);
            fail();
        } catch(NumberFormatException r){
            assertTrue(true);
        }
    }
}