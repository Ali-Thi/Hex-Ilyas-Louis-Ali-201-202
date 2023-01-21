package test;

import hex.board.Board;
import hex.players.IA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIA {

    private final int side = 8;
    private IA ia1 = new IA();
    private IA ia2 = new IA();
    private Board board = new Board(side);


    /**
     * Test l'initialisation d'une instance de IA
     */
    @Test
    public void test1() {
        assertEquals("IA1", ia1.getName());
        assertEquals("IA2", ia2.getName());
    }

    /**
     * Test la fonction generateMove et cleanBoard
     */
    @Test
    public void test2() {
        board.cleanBoard();
        for (int i = 0 ; i < side ; ++i) {
            for (int j = 0 ; j < side ; ++j) {
                board.placeAPawn(ia1.chooseSquare(board.getSide()), 0);
            }
        }
        assertEquals("  A B C D E F G H\n" +
                "1 X X X X X X X X\n" +
                "2  X X X X X X X X\n" +
                "3   X X X X X X X X\n" +
                "4    X X X X X X X X\n" +
                "5     X X X X X X X X\n" +
                "6      X X X X X X X X\n" +
                "7       X X X X X X X X\n" +
                "8        X X X X X X X X\n", board.toString());
    }
}
