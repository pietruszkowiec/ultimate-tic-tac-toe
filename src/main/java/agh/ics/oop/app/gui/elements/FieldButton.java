package agh.ics.oop.app.gui.elements;

import agh.ics.oop.enums.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class FieldButton {
    private final int i;
    private final int j;
    private final SmallBoardGrid smallBoardGrid;
    private final Button button;

    public FieldButton(int i, int j, SmallBoardGrid smallBoardGrid) {
        this.i = i;
        this.j = j;
        this.smallBoardGrid = smallBoardGrid;
        this.button = new Button();
        this.button.setOnAction(event -> onClick());
        this.button.setStyle(
                "-fx-background-color: transparent;\n" +
                " -fx-pref-height: 40px;\n" +
                "    -fx-pref-width: 40px;");
    }

    public Button getButton() {
        return this.button;
    }

    private void onClick() {
        this.smallBoardGrid.onClick(this.i, this.j);
    }

    public void changeState(Player player) {
        if (player == Player.X) {
            this.button.setText("X");
        } else {
            this.button.setText("O");
        }
        this.button.setAlignment(Pos.CENTER);
    }
}
