package agh.ics.oop.enums.states;

import agh.ics.oop.interfaces.IState;

public enum BoardState implements IState {
    ONGOING,
    X_WIN,
    O_WIN,
    DRAW;

    public String toString() {
        return switch (this) {
            case X_WIN -> "X wins";
            case O_WIN -> "O wins";
            case DRAW -> "Draw";
            case ONGOING -> "Game is not over";
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
