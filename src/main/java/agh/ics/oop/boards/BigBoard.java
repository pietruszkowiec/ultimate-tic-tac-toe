package agh.ics.oop.boards;

import agh.ics.oop.Tuple;
import agh.ics.oop.enums.states.BoardState;

public class BigBoard extends AbstractBoard {
    public BigBoard() {
        this.boardFields = new SmallBoard[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.boardFields[i][j] = new SmallBoard(new Tuple(i, j));
            }
        }
    }

    public SmallBoard[][] getSmallBoards() {
        return (SmallBoard[][]) this.boardFields;
    }

    public BoardState getSmallBoardState(Tuple tuple) {
        int i = tuple.i;
        int j = tuple.j;
        return (BoardState) this.boardFields[i][j].getState();
    }
}
