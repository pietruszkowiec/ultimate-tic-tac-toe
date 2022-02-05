package agh.ics.oop.enums;

public enum Player {
    X,
    O;

    public Player nextTurn() {
        return switch (this) {
            case X -> Player.O;
            case O -> Player.X;
        };
    }
}
