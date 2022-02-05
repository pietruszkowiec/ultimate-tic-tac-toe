package agh.ics.oop.boards;

import agh.ics.oop.Field;
import agh.ics.oop.enums.states.FieldState;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.states.BoardState;
import agh.ics.oop.enums.moves.BigBoardPosition;
import agh.ics.oop.enums.moves.SmallBoardPosition;

public class SmallBoard {
    private BoardState boardState = BoardState.ONGOING;
    public final BigBoardPosition position;
    private final Field[][] fields = new Field[3][3];
    private final static SmallBoardPosition[] positionsForHChecking = {
            SmallBoardPosition.N,
            SmallBoardPosition.C,
            SmallBoardPosition.S
    };
    private final static SmallBoardPosition[] positionsForVChecking = {
            SmallBoardPosition.W,
            SmallBoardPosition.C,
            SmallBoardPosition.E
    };

    public SmallBoard(BigBoardPosition position) {
        this.position = position;
    }

    public boolean makeMove(SmallBoardPosition move, Player player) {
        int i = move.toTuple().i;
        int j = move.toTuple().j;

        if (!fields[i][j].isEmpty()) {
            return false;
        }

        this.fields[i][j].setFieldState(FieldState.playerToFieldState(player));

        return true;
    }

    public boolean checkForChangeOfState() {
        int iCenter;
        int jCenter;
        FieldState centerState;

        for (SmallBoardPosition centerPosition : positionsForHChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.fields[iCenter][jCenter].getFieldState();

            if (this.fields[iCenter][jCenter - 1].getFieldState() == centerState
                    && this.fields[iCenter][jCenter + 1].getFieldState() == centerState) {
                this.boardState = BoardState.fieldStateToBoardState(centerState);
                return true;
            }
        }

        for (SmallBoardPosition centerPosition : positionsForVChecking) {
            iCenter = centerPosition.toTuple().i;
            jCenter = centerPosition.toTuple().j;
            centerState = this.fields[iCenter][jCenter].getFieldState();

            if (this.fields[iCenter - 1][jCenter].getFieldState() == centerState
                    && this.fields[iCenter + 1][jCenter].getFieldState() == centerState) {
                this.boardState = BoardState.fieldStateToBoardState(centerState);
                return true;
            }
        }

        iCenter = 1;
        jCenter = 1;
        centerState = this.fields[iCenter][jCenter].getFieldState();

        if (this.fields[iCenter - 1][jCenter - 1].getFieldState() == centerState
                && this.fields[iCenter + 1][jCenter + 1].getFieldState() == centerState) {
            this.boardState = BoardState.fieldStateToBoardState(centerState);
            return true;
        }

        if (this.fields[iCenter + 1][jCenter + 1].getFieldState() == centerState
                && this.fields[iCenter - 1][jCenter - 1].getFieldState() == centerState) {
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
