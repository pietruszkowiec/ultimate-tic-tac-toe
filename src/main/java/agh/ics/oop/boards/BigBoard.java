package agh.ics.oop.boards;

import agh.ics.oop.Tuple;
import agh.ics.oop.enums.BoardPosition;
import agh.ics.oop.enums.states.BoardState;

public class BigBoard {
    private final SmallBoard[][] smallBoards = new SmallBoard[3][3];
    private final static BoardPosition[] positionsForHChecking = {
            BoardPosition.N,
            BoardPosition.C,
            BoardPosition.S
    };
    private final static BoardPosition[] positionsForVChecking = {
            BoardPosition.W,
            BoardPosition.C,
            BoardPosition.E
    };


    public BigBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.smallBoards[i][j] = new SmallBoard(new Tuple(i, j));
            }
        }
    }

    public SmallBoard[][] getSmallBoards() {
        return this.smallBoards;
    }

    public BoardState getSmallBoardState(Tuple tuple) {
        int i = tuple.i;
        int j = tuple.j;
        return this.smallBoards[i][j].getBoardState();
    }

    public boolean checkForChangeOfState() {
        int iCenter;
        int jCenter;
        BoardState centerState;

        for (BoardPosition centerPosition : positionsForHChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.smallBoards[iCenter][jCenter].getBoardState();
            if (centerState == BoardState.ONGOING) {
                continue;
            }

            if (this.smallBoards[iCenter][jCenter - 1].getBoardState() == centerState
                    && this.smallBoards[iCenter][jCenter + 1].getBoardState() == centerState) {
                return true;
            }
        }

        for (BoardPosition centerPosition : positionsForVChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.smallBoards[iCenter][jCenter].getBoardState();
            if (centerState == BoardState.ONGOING) {
                continue;
            }

            if (this.smallBoards[iCenter - 1][jCenter].getBoardState() == centerState
                    && this.smallBoards[iCenter + 1][jCenter].getBoardState() == centerState) {
                return true;
            }
        }

        iCenter = 1;
        jCenter = 1;
        centerState = this.smallBoards[iCenter][jCenter].getBoardState();

        if (centerState == BoardState.ONGOING) {
            return false;
        }

        if (this.smallBoards[iCenter - 1][jCenter - 1].getBoardState() == centerState
                && this.smallBoards[iCenter + 1][jCenter + 1].getBoardState() == centerState) {
            return true;
        }

        if (this.smallBoards[iCenter - 1][jCenter + 1].getBoardState() == centerState
                && this.smallBoards[iCenter + 1][jCenter - 1].getBoardState() == centerState) {
            return true;
        }


        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.smallBoards[i][j].getBoardState() != BoardState.ONGOING) {
                    cnt++;
                }
            }
        }

        if (cnt == 9) {
            return true;
        }

        return false;
    }

}
