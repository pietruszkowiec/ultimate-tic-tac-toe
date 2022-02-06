package agh.ics.oop.enums;

import agh.ics.oop.Tuple;

public enum BoardPosition {
    NW,
    N,
    NE,
    W,
    C,
    E,
    SW,
    S,
    SE;

    private static final Tuple[] tuples = {
            new Tuple(0, 0),
            new Tuple(0, 1),
            new Tuple(0, 2),
            new Tuple(1, 0),
            new Tuple(1, 1),
            new Tuple(1, 2),
            new Tuple(2, 0),
            new Tuple(2, 1),
            new Tuple(2, 2)
    };

    public Tuple toTuple() {
        return tuples[this.ordinal()];
    }

    public static BoardPosition tupleToBoardPosition(Tuple tuple) {
        for (int i = 0; i < tuples.length; i++) {
            if (tuples[i].equals(tuple)) {
                return values()[i];
            }
        }
        throw new IllegalStateException("Unexpected tuple value");
    }
}
