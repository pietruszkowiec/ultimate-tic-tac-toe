package agh.ics.oop.enums.moves;

import agh.ics.oop.Tuple;

public enum SmallBoardPosition {
    NW,
    N,
    NE,
    W,
    C,
    E,
    SW,
    S,
    SE;

    public Tuple toTuple() {
        return switch (this) {
            case NW -> new Tuple(0, 0);
            case N -> new Tuple(0, 1);
            case NE -> new Tuple(0, 2);
            case W -> new Tuple(1, 0);
            case C -> new Tuple(1, 1);
            case E -> new Tuple(1, 2);
            case SW -> new Tuple(2, 0);
            case S -> new Tuple(2, 1);
            case SE -> new Tuple(2, 2);
        };
    }
}
