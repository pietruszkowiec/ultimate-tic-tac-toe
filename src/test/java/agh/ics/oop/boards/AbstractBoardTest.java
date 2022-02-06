package agh.ics.oop.boards;

import agh.ics.oop.Tuple;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.states.BoardState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractBoardTest {
    @Test
    public void stateChangeTest() {
        int[] results = {
                1, // 1 X wins
                1, // 2 X wins
                2, // 3 O wins
                1, // 4 X wins
                1, // 5 X wins
                2, // 6 O wins
                1, // 7 X wins
                1, // 8 X wins
                0, // 9 Draw
                0, // 10 Draw
                0, // 11 Draw
                0, // 12 Draw
                -1, // 13 Ongoing
                -1, // 14 Ongoing
        };

        int[][][] testSets = {
                { // 1 X wins
                        {1, 1, 1},
                        {0, 2, 0},
                        {2, 1, 2}
                },
                { // 2 X wins
                        {1, 2, 0},
                        {1, 1, 1},
                        {0, 2, 0}
                },
                { // 3 O wins
                        {0, 0, 2},
                        {1, 1, 0},
                        {2, 2, 2}
                },
                { // 4 X wins
                        {1, 0, 2},
                        {1, 1, 0},
                        {1, 2, 2}
                },
                { // 5 X wins
                        {0, 1, 2},
                        {1, 1, 0},
                        {2, 1, 2}
                },
                { // 6 O wins
                        {0, 0, 2},
                        {1, 1, 2},
                        {2, 1, 2}
                },
                { // 7 X wins
                        {1, 0, 2},
                        {1, 1, 0},
                        {2, 2, 1}
                },
                { // 8 X wins
                        {0, 0, 1},
                        {1, 1, 0},
                        {1, 2, 2}
                },
                { // 9 Draw
                        {1, 2, 2},
                        {2, 1, 1},
                        {2, 1, 2}
                },
                { // 10 Draw
                        {1, 1, 2},
                        {2, 1, 1},
                        {1, 2, 2}
                },
                { // 11 Draw
                        {1, 1, 2},
                        {2, 2, 1},
                        {1, 2, 1}
                },
                { // 12 Draw
                        {2, 2, 1},
                        {1, 1, 2},
                        {2, 2, 1}
                },
                { // 13 Ongoing
                        {0, 0, 1},
                        {1, 0, 2},
                        {0, 2, 1}
                },
                { // 14 Ongoing
                        {1, 2, 1},
                        {1, 0, 2},
                        {2, 2, 1}
                },
        };

        AbstractBoard board;

        for (int i = 0; i < results.length; i++) {
            board = createSmallBoard(testSets[i]);

            switch (results[i]) {
                case 0 -> {
                    assertTrue(board.checkForChangeOfState());
                    assertEquals(board.getState(), BoardState.DRAW);
                }
                case 1 -> {
                    assertTrue(board.checkForChangeOfState());
                    assertEquals(board.getState(), BoardState.X_WIN);
                }
                case 2 -> {
                    assertTrue(board.checkForChangeOfState());
                    assertEquals(board.getState(), BoardState.O_WIN);
                }
                case -1 -> {
                    assertFalse(board.checkForChangeOfState());
                    assertEquals(board.getState(), BoardState.ONGOING);
                }
            }
        }
    }

    private SmallBoard createSmallBoard(int[][] testSet) {
        SmallBoard smallBoard = new SmallBoard(new Tuple(0, 0));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (testSet[i][j]) {
                    case 1 -> smallBoard.makeMove(i, j, Player.X);
                    case 2 -> smallBoard.makeMove(i, j, Player.O);
                }
            }
        }

        return smallBoard;
    }
}
