package agh.ics.oop.boards;

import agh.ics.oop.Field;
import agh.ics.oop.Tuple;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.states.FieldState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmallBoardTest {
    @Test
    public void makeMoveTest() {
        SmallBoard smallBoardEmpty = new SmallBoard(new Tuple(0, 0));
        Player player = Player.O;
        Field field;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field = (Field) smallBoardEmpty.boardFields[i][j];
                assertTrue(field.isEmpty());
                assertTrue(smallBoardEmpty.makeMove(i, j, player));
                assertEquals(field.getState(), FieldState.O);
                assertFalse(smallBoardEmpty.makeMove(i, j, player));
            }
        }
    }
}
