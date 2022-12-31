package test;

import game.utilities.Board;
import game.utilities.IA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIA {

    private final int side = 8;
    private IA ia1 = new IA(side);
    private IA ia2 = new IA(side);
    private Board board = new Board(side, 2);


    /**
     * test the initialization
     */
    @Test
    public void test1(){
        assertTrue(ia1.isIA());
        assertEquals("IA1", ia1.getName());
        assertEquals("IA2", ia2.getName());
    }

    /**
     * test the function addMove
     */
    @Test
    public void test2(){
        for(char c : "ABCDEFGH".toCharArray())
            for(char n : "12345678".toCharArray())
                assertTrue(ia1.addMove(c + String.valueOf(n), 1));

        int[][] expected = new int[side][side];
        for(int i = 0 ; i < side ; ++i)
            for(int j = 0 ; j < side ; ++j)
                expected[i][j] = 1;

        //l'attribut board n'est uniquement public que pendant les tests
        for(int i = 0 ; i < ia1.board.length ; ++i){
            for(int j = 0 ; j < ia1.board.length ; ++j){
                assertEquals(expected[i][j], ia1.board[i][j]);
            }
        }
        assertFalse(ia1.addMove("A1", 1));

        ia1.cleanBoard();
    }


    @Test
    public void test3(){
        String move = ia1.generateMove();
        assertTrue(board.playAMove(move, 1));
        board.cleanBoard();
        ia1.cleanBoard();
    }

    @Test
    public void test4(){
        String move1;
        String move2;
        board.cleanBoard();
        for(int i = 0 ; i < (side*side)/2 ; ++i){
            move1 = ia1.generateMove();

            assertTrue(board.playAMove(move1, 1));
            assertTrue(ia1.addMove(move1, 1));
            assertTrue(ia2.addMove(move1, 1));

            move2 = ia2.generateMove();

            assertTrue(board.playAMove(move2, 2));
            assertTrue(ia1.addMove(move2, 2));
            assertTrue(ia2.addMove(move2, 2));

            //l'attribut board n'est uniquement public que pendant les tests
            for(int k = 0 ; k< ia1.board.length ; ++k){
                for(int j = 0 ; j < ia1.board.length ; ++j){
                    assertEquals(ia2.board[k][j], ia1.board[k][j]);
                }
            }
        }
        board.cleanBoard();

        try{
            ia1.generateMove();
            assertFalse(false);
        }catch(RuntimeException r){
            assertTrue(true);
        }

    }
}
