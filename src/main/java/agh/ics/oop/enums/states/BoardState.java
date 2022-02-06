package agh.ics.oop.enums.states;

import agh.ics.oop.enums.Player;
import agh.ics.oop.interfaces.IState;

public enum BoardState implements IState {
    ONGOING,
    X_WIN,
    O_WIN,
    DRAW;

    public static BoardState playerToResult(Player player) {
        return switch (player) {
            case X -> BoardState.X_WIN;
            case O -> BoardState.O_WIN;
        };
    }

    public static BoardState StateToBoardState(IState state) {
        FieldState fieldState = (FieldState) state;
        return switch (fieldState) {
            case X -> BoardState.X_WIN;
            case O -> BoardState.O_WIN;
            case EMPTY -> BoardState.ONGOING;
        };
    }
}
