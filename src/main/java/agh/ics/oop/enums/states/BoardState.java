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

    public static BoardState stateToBoardState(IState state) {
        if (state instanceof FieldState fieldState) {
            return switch (fieldState) {
                case X -> BoardState.X_WIN;
                case O -> BoardState.O_WIN;
                case EMPTY -> BoardState.ONGOING;
            };
        } else if (state instanceof BoardState boardState) {
            return boardState;
        } else {
            throw new IllegalArgumentException("Illegal state in StateTOBoardState");
        }
    }
}
