package agh.ics.oop.enums.states;

import agh.ics.oop.enums.Player;
import agh.ics.oop.interfaces.IState;

public enum FieldState implements IState {
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
