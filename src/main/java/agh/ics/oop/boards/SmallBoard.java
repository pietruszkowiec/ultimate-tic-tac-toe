package agh.ics.oop.boards;

import agh.ics.oop.Field;
import agh.ics.oop.interfaces.IBoardField;
import agh.ics.oop.Tuple;
import agh.ics.oop.enums.states.FieldState;
import agh.ics.oop.enums.Player;

public class SmallBoard extends AbstractBoard implements IBoardField {
    public final Tuple bigBoardPositionTuple;

    public SmallBoard(Tuple bigBoardPositionTuple) {
        this.boardFields = new Field[3][3];
        this.bigBoardPositionTuple = bigBoardPositionTuple;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.boardFields[i][j] = new Field();
            }
        }
    }

    public Tuple getBigBoardPositionTuple() {
        return bigBoardPositionTuple;
    }

    public boolean makeMove(int i, int j, Player player) {
        Field field = (Field) this.boardFields[i][j];
        if (!(field.isEmpty())) {
            return false;
        }

        field.setFieldState(FieldState.playerToFieldState(player));

        return true;
    }
}
