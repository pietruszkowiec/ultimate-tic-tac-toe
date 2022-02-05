package agh.ics.oop.boards;

import agh.ics.oop.Tuple;
import agh.ics.oop.enums.BoardPosition;

public class BigBoard {
    private final SmallBoard[][] smallBoards = new SmallBoard[3][3];

    public BigBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                smallBoards[i][j] = new SmallBoard(BoardPosition.tupleToBoardPosition(new Tuple(i, j)));
            }
        }
    }

    public SmallBoard[][] getSmallBoards() {
        return smallBoards;
    }
}
