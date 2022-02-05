package agh.ics.oop.enums.states;

import agh.ics.oop.enums.Player;

public enum BoardState {
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

    public static BoardState fieldStateToBoardState(FieldState fieldState) {
        return switch (fieldState) {
            case X -> BoardState.X_WIN;
            case O -> BoardState.O_WIN;
            case EMPTY -> BoardState.ONGOING;
        };
    }
}
