package agh.ics.oop;

import agh.ics.oop.enums.states.FieldState;
import agh.ics.oop.interfaces.IBoardField;

public class Field implements IBoardField {
    private FieldState fieldState = FieldState.EMPTY;

    public FieldState getState() {
        return fieldState;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    public boolean isEmpty() {
        return this.fieldState == FieldState.EMPTY;
    }
}
