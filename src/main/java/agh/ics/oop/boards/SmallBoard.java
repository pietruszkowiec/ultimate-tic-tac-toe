package agh.ics.oop.boards;

import agh.ics.oop.Field;
import agh.ics.oop.Tuple;
import agh.ics.oop.enums.BoardPosition;
import agh.ics.oop.enums.states.FieldState;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.states.BoardState;

public class SmallBoard {
    public final Tuple bigBoardPositionTuple;
    private BoardState boardState = BoardState.ONGOING;
    private final Field[][] fields = new Field[3][3];
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


    public SmallBoard(Tuple bigBoardPositionTuple) {
        this.bigBoardPositionTuple = bigBoardPositionTuple;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.fields[i][j] = new Field();
            }
        }
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public Tuple getBigBoardPositionTuple() {
        return bigBoardPositionTuple;
    }

    public boolean makeMove(int i, int j, Player player) {
        if (!(this.fields[i][j].isEmpty())) {
            return false;
        }

        this.fields[i][j].setFieldState(FieldState.playerToFieldState(player));

        return true;
    }

    public boolean checkForChangeOfState() {
        int iCenter;
        int jCenter;
        FieldState centerState;

        for (BoardPosition centerPosition : positionsForHChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.fields[iCenter][jCenter].getFieldState();
            if (centerState == FieldState.EMPTY) {
                continue;
            }

            if (this.fields[iCenter][jCenter - 1].getFieldState() == centerState
                    && this.fields[iCenter][jCenter + 1].getFieldState() == centerState) {
                this.boardState = BoardState.fieldStateToBoardState(centerState);
                return true;
            }
        }

        for (BoardPosition centerPosition : positionsForVChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.fields[iCenter][jCenter].getFieldState();
            if (centerState == FieldState.EMPTY) {
                continue;
            }

            if (this.fields[iCenter - 1][jCenter].getFieldState() == centerState
                    && this.fields[iCenter + 1][jCenter].getFieldState() == centerState) {
                this.boardState = BoardState.fieldStateToBoardState(centerState);
                return true;
            }
        }

        iCenter = 1;
        jCenter = 1;
        centerState = this.fields[iCenter][jCenter].getFieldState();

        if (centerState == FieldState.EMPTY) {
            return false;
        }

        if (this.fields[iCenter - 1][jCenter - 1].getFieldState() == centerState
                && this.fields[iCenter + 1][jCenter + 1].getFieldState() == centerState) {
            this.boardState = BoardState.fieldStateToBoardState(centerState);
            return true;
        }

        if (this.fields[iCenter - 1][jCenter + 1].getFieldState() == centerState
                && this.fields[iCenter + 1][jCenter - 1].getFieldState() == centerState) {
            this.boardState = BoardState.fieldStateToBoardState(centerState);
            return true;
        }


        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.fields[i][j].getFieldState() != FieldState.EMPTY) {
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
