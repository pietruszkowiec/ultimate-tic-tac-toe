package agh.ics.oop.enums.states;

import agh.ics.oop.enums.Player;

public enum FieldState {
    X,
    O,
    EMPTY;

    public static FieldState playerToFieldState(Player player) {
        return switch (player) {
            case X -> FieldState.X;
            case O -> FieldState.O;
        };
    }
}
