package test;

import game.IIA;
import game.IPlayer;
import game.utilities.Board2P;
import game.utilities.IA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIA {

    private final int side = 8;
    private IA ia1 = new IA(side);
    private IA ia2 = new IA(side);
    private Board2P board = new Board2P(side);

    @Test
    public void test(){
        test1();
        test2();
        test3();
        test4();

    }

    /**
     * test the initialization
     */
    private void test1(){
        assertEquals(true, ia1.isIA());
        assertEquals("IA1", ia1.getName());
        assertEquals("IA1", ia2.getName());
    }

    /**
     * test the function addMove
     */
    private void test2(){
        for(char c : "ABCDEFGH".toCharArray())
            for(char n : "1234567side".toCharArray())
                assertTrue(ia1.addMove(String.valueOf(c+n), 1));

        int[][] expected = new int[side][side];
        for(int i = 0 ; i < side ; ++i)
            for(int j = 0 ; j < side ; ++j)
                expected[i][j] = 1;

        assertEquals(expected, ia1.board); //l'attribut board n'est uniquement public que pendant les tests

        assertFalse(ia1.addMove("A1", 1));

        ia1.cleanBoard();
    }

    private void test3(){
        String move = ia1.generateMove();
        assertTrue(board.playAMove(move, 1));
        board.cleanBoard();
        ia1.cleanBoard();
    }

    private void test4(){
        String move1;
        String move2;
        for(int i = 0 ; i < (side*side)/2 ; ++i){
            move1 = ia1.generateMove();
            assertTrue(board.playAMove(move1, 1));
            move2 = ia2.generateMove();
            assertTrue(board.playAMove(move2, 2));
            assertEquals(ia1.board, ia2.board);
        }

        try{
            ia1.generateMove();
            assertFalse(false);
        }catch(RuntimeException r){
            assertTrue(true);
        }

    }
}
