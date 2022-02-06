package agh.ics.oop.boards;

import agh.ics.oop.enums.BoardPosition;
import agh.ics.oop.enums.states.BoardState;
import agh.ics.oop.enums.states.FieldState;
import agh.ics.oop.interfaces.IBoardField;
import agh.ics.oop.interfaces.IState;

public class AbstractBoard {
    protected BoardState boardState = BoardState.ONGOING;
    protected IBoardField[][] boardFields;
    protected final static BoardPosition[] positionsForHChecking = {
            BoardPosition.N,
            BoardPosition.C,
            BoardPosition.S
    };
    protected final static BoardPosition[] positionsForVChecking = {
            BoardPosition.W,
            BoardPosition.C,
            BoardPosition.E
    };


    public IState getState() {
        return this.boardState;
    }

    public boolean checkForChangeOfState() {
        int iCenter;
        int jCenter;
        IState centerState;

        for (BoardPosition centerPosition : positionsForHChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.boardFields[iCenter][jCenter].getState();
            if (centerState == FieldState.EMPTY || centerState == BoardState.ONGOING) {
                continue;
            }

            if (this.boardFields[iCenter][jCenter - 1].getState() == centerState
                    && this.boardFields[iCenter][jCenter + 1].getState() == centerState) {
                this.boardState = BoardState.stateToBoardState(centerState);
                return true;
            }
        }

        for (BoardPosition centerPosition : positionsForVChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.boardFields[iCenter][jCenter].getState();
            if (centerState == FieldState.EMPTY || centerState == BoardState.ONGOING) {
                continue;
            }

            if (this.boardFields[iCenter - 1][jCenter].getState() == centerState
                    && this.boardFields[iCenter + 1][jCenter].getState() == centerState) {
                this.boardState = BoardState.stateToBoardState(centerState);
                return true;
            }
        }

        iCenter = 1;
        jCenter = 1;
        centerState = this.boardFields[iCenter][jCenter].getState();

        if (centerState == FieldState.EMPTY || centerState == BoardState.ONGOING) {
            return false;
        }

        if (this.boardFields[iCenter - 1][jCenter - 1].getState() == centerState
                && this.boardFields[iCenter + 1][jCenter + 1].getState() == centerState) {
            this.boardState = BoardState.stateToBoardState(centerState);
            return true;
        }

        if (this.boardFields[iCenter - 1][jCenter + 1].getState() == centerState
                && this.boardFields[iCenter + 1][jCenter - 1].getState() == centerState) {
            this.boardState = BoardState.stateToBoardState(centerState);
            return true;
        }


        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.boardFields[i][j].getState() != FieldState.EMPTY
                        && this.boardFields[i][j].getState() != BoardState.ONGOING) {
                    cnt++;
                }
            }
        }

        if (cnt == 9) {
            this.boardState = BoardState.DRAW;
            return true;
        }

        return false;
    }
}
