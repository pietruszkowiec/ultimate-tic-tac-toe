package agh.ics.oop;

public class Tuple {
    public final int i;
    public final int j;

    public Tuple(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Tuple that)) {
            return false;
        }
        return this.i == that.i && this.j == that.j;
    }
}
