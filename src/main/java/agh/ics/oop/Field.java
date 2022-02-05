package agh.ics.oop;

import agh.ics.oop.enums.states.FieldState;

public class Field {
    private FieldState fieldState = FieldState.EMPTY;

    public FieldState getFieldState() {
        return fieldState;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    public boolean isEmpty() {
        return this.fieldState == FieldState.EMPTY;
    }
}
