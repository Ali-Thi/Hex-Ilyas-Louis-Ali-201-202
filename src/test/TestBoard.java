package test;

import hex.IBoard;
import hex.board.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {
    private final int side = 8;
    private IBoard board = new Board(side);

    /**
     * Test l'initialisation d'une instance de Board
     */
    @Test
    public void test1() {
        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . .\n" +
                "2  . . . . . . . .\n" +
                "3   . . . . . . . .\n" +
                "4    . . . . . . . .\n" +
                "5     . . . . . . . .\n" +
                "6      . . . . . . . .\n" +
                "7       . . . . . . . .\n" +
                "8        . . . . . . . .\n", board.toString());

        board = new Board(3);


        assertEquals("  A B C\n" +
                "1 . . .\n" +
                "2  . . .\n" +
                "3   . . .\n", board.toString());


        board = new Board(side);
        board.placeAPawn("H1", 0);
        board.placeAPawn("H2", 0);
        board.placeAPawn("H3", 0);
        board.placeAPawn("H4", 0);
        board.placeAPawn("H5", 0);
        board.placeAPawn("H6", 0);
        board.placeAPawn("H7", 0);
        board.placeAPawn("H8", 0);


        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . X\n" +
                "2  . . . . . . . X\n" +
                "3   . . . . . . . X\n" +
                "4    . . . . . . . X\n" +
                "5     . . . . . . . X\n" +
                "6      . . . . . . . X\n" +
                "7       . . . . . . . X\n" +
                "8        . . . . . . . X\n", board.toString());


    }

    /**
     * Test l'ajout de pion sur le plateau, ainsi que de la fonction cleanBoard qui remet le plateau à son état initial
     */
    @Test
    public void test2() {
        for (char c : "ABCDEFGH".toCharArray()) {
            for (char n : "1234567".toCharArray()) {
                board.placeAPawn(c + String.valueOf(n), 0);
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
                "8        . . . . . . . .\n", board.toString());

        board.placeAPawn("E2", 1);
        board.placeAPawn("H3", 1);
        board.placeAPawn("A5", 1);

        assertEquals("  A B C D E F G H\n" +
                "1 X X X X X X X X\n" +
                "2  X X X X X X X X\n" +
                "3   X X X X X X X X\n" +
                "4    X X X X X X X X\n" +
                "5     X X X X X X X X\n" +
                "6      X X X X X X X X\n" +
                "7       X X X X X X X X\n" +
                "8        . . . . . . . .\n", board.toString());

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
     * Test les fonctions vérifiant si la partie est gagnée et par qui sur des chemins verticaux ou horizontaux sans fusion de chemin
     */
    @Test
    public void test3() {
        board = new Board(side);
        for (int i = 1; i <= side; i++) {
            board.placeAPawn('H' + String.valueOf(i), 0);
        }
        /*
          A B C D E F G H
        1 . . . . . . . X
        2  . . . . . . . X
        3   . . . . . . . X
        4    . . . . . . . X
        5     . . . . . . . X
        6      . . . . . . . X
        7       . . . . . . . X
        8        . . . . . . . X
        */

        assertEquals("  A B C D E F G H\n" +
                "1 . . . . . . . X\n" +
                "2  . . . . . . . X\n" +
                "3   . . . . . . . X\n" +
                "4    . . . . . . . X\n" +
                "5     . . . . . . . X\n" +
                "6      . . . . . . . X\n" +
                "7       . . . . . . . X\n" +
                "8        . . . . . . . X\n", board.toString());
        assertTrue(board.isWon());
        assertEquals(0, board.getWinner());

        board.cleanBoard();

        for (char c : "ABCDEFGH".toCharArray()) {
            board.placeAPawn(c + String.valueOf(1), 1);
        }
        /*
          A B C D E F G H
        1 O O O O O O O O
        2  . . . . . . . .
        3   . . . . . . . .
        4    . . . . . . . .
        5     . . . . . . . .
        6      . . . . . . . .
        7       . . . . . . . .
        8        . . . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());
    }

    /**
     * Test les fonctions vérifiant si la partie est gagnée et par qui sur des chemins en diagonale sans fusion de chemin
     */
    @Test
    public void test4() {
        board.placeAPawn("H1", 1);
        board.placeAPawn("G2", 1);
        board.placeAPawn("F3", 1);
        board.placeAPawn("E4", 1);
        board.placeAPawn("D5", 1);
        board.placeAPawn("C6", 1);
        board.placeAPawn("B7", 1);
        board.placeAPawn("A8", 1);
        /*
          A B C D E F G H
        1 . . . . . . . O
        2  . . . . . . O .
        3   . . . . . O . .
        4    . . . . O . . .
        5     . . . O . . . .
        6      . . O . . . . .
        7       . O . . . . . .
        8        O . . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());

        board.cleanBoard();

        board.placeAPawn("H1", 0);
        board.placeAPawn("G2", 0);
        board.placeAPawn("F3", 0);
        board.placeAPawn("E4", 0);
        board.placeAPawn("D5", 0);
        board.placeAPawn("C6", 0);
        board.placeAPawn("B7", 0);
        board.placeAPawn("A8", 0);

        /*
          A B C D E F G H
        1 . . . . . . . X
        2  . . . . . . X .
        3   . . . . . X . .
        4    . . . . X . . .
        5     . . . X . . . .
        6      . . X . . . . .
        7       . X . . . . . .
        8        X . . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(0, board.getWinner());
    }

    /**
     * Test les fonctions vérifiant si la partie est gagnée et par qui sur des chemins sinueux avec fusion de chemin
     */
    @Test
    public void test5() {
        Board board = new Board(side);
        board.placeAPawn("H1", 1);
        board.placeAPawn("F1", 1);
        board.placeAPawn("G2", 1);
        board.placeAPawn("F2", 1);
        board.placeAPawn("E2", 1);
        board.placeAPawn("C3", 1);
        board.placeAPawn("B4", 1);
        board.placeAPawn("E4", 1);
        board.placeAPawn("B5", 1);
        board.placeAPawn("D5", 1);
        board.placeAPawn("B6", 1);
        board.placeAPawn("C6", 1);
        board.placeAPawn("D6", 1);
        board.placeAPawn("B7", 1);
        board.placeAPawn("C7", 1);
        board.placeAPawn("A8", 1);
        board.placeAPawn("B8", 1);
        board.placeAPawn("D2", 1);
        /*
          A B C D E F G H
        1 . . . . . O . O
        2  . . . O O O O .
        3   . . O . . . . .
        4    . O . . O . . .
        5     . O . O . . . .
        6      . O O O . . . .
        7       . O O . . . . .
        8        O O . . . . . .
        */
        assertTrue(board.isWon());
        assertEquals(1, board.getWinner());


        board.cleanBoard();


        board.placeAPawn("F1", 0);
        board.placeAPawn("H1", 0);
        board.placeAPawn("D2", 0);
        board.placeAPawn("E2", 0);
        board.placeAPawn("F2", 0);
        board.placeAPawn("G2", 0);
        board.placeAPawn("C3", 0);
        board.placeAPawn("B4", 0);
        board.placeAPawn("E4", 0);
        board.placeAPawn("B5", 0);
        board.placeAPawn("D5", 0);
        board.placeAPawn("B6", 0);
        board.placeAPawn("C6", 0);
        board.placeAPawn("D6", 0);
        board.placeAPawn("B7", 0);
        board.placeAPawn("C7", 0);
        board.placeAPawn("A8", 0);

        /*
          A B C D E F G H
        1 . . . . . X . X
        2  . . . X X X X .
        3   . . X . . . . .
        4    . X . . X . . .
        5     . X . X . . . .
        6      . X X X . . . .
        7       . X X . . . . .
        8        X . . . . . . .
        */

        assertTrue(board.isWon());
        assertEquals(0, board.getWinner());
    }

    /**
     * Test que les fonctions lèvent des erreurs lorsqu'une erreur survient
     */
    @Test
    public void test6() {
        board.cleanBoard();

        assertFalse(board.isWon());
        assertEquals(board.getWinner(), 0);

        try {
            board.placeAPawn("M0", 0);
            fail();
        } catch (IllegalArgumentException r) {
            assertTrue(true);
        }

        try {
            board.placeAPawn("A1", 4);
        } catch (IllegalArgumentException r) {
            assertTrue(true);
        }
    }

    /**
     * Test lorsque 2 joueurs jouent chacun leur tour sur le même plateau
     */
    @Test
    public void test7() {
        board.cleanBoard();

        board.placeAPawn("G1", 0);
        board.placeAPawn("A1", 1);

        board.placeAPawn("G2", 0);
        board.placeAPawn("B1", 1);

        board.placeAPawn("G3", 0);
        board.placeAPawn("C1", 1);

        board.placeAPawn("G4", 0);
        board.placeAPawn("D1", 1);

        board.placeAPawn("G5", 0);
        board.placeAPawn("E1", 1);

        board.placeAPawn("G6", 0);
        board.placeAPawn("F1", 1);

        board.placeAPawn("G7", 0);
        board.placeAPawn("H1", 1);

        board.placeAPawn("G8", 0);

        if (board.isWon()) {
            assertEquals("  A B C D E F G H\n" +
                    "1 O O O O O O X O\n" +
                    "2  . . . . . . X .\n" +
                    "3   . . . . . . X .\n" +
                    "4    . . . . . . X .\n" +
                    "5     . . . . . . X .\n" +
                    "6      . . . . . . X .\n" +
                    "7       . . . . . . X .\n" +
                    "8        . . . . . . X .\n", board.toString());
            assertEquals(0, board.getWinner());
        }
    }
}