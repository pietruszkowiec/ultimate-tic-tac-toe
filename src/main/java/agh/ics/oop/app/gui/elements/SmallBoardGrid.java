package agh.ics.oop.app.gui.elements;

import agh.ics.oop.Tuple;
import agh.ics.oop.app.engine.Engine;
import agh.ics.oop.boards.SmallBoard;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.BoardPosition;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SmallBoardGrid {
    private final Engine engine;
    private final BigBoardGrid bigBoardGrid;
    private final GridPane gridPane;
    private final SmallBoard smallBoard;
    private final FieldButton[][] fields = new FieldButton[3][3];
    public static final int cellSize = 40;

    public SmallBoardGrid(SmallBoard smallBoard, Engine engine, BigBoardGrid bigBoardGrid) {
        this.smallBoard = smallBoard;
        this.engine = engine;
        this.bigBoardGrid = bigBoardGrid;
        this.gridPane = new GridPane();
        this.gridPane.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
            this.gridPane.getRowConstraints().add(new RowConstraints(cellSize));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = new FieldButton(i, j, this);
                this.gridPane.add(fields[i][j].getButton(), j, i);
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void onClick(int i, int j) {
        Player player = this.engine.turn;
        BoardPosition boardPosition;
        if(this.smallBoard.makeMove(i, j, player)) {
            this.fields[i][j].changeState(player);
            boardPosition = BoardPosition.tupleToBoardPosition(new Tuple(i, j));
            this.engine.nextTurn(i, j);
            System.out.println(this.smallBoard.bigBoardPosition + " / " + boardPosition);
        }
    }

    public void changeBackgroundColor() {
        this.gridPane.setStyle("-fx-background-color: lightgreen;");
    }

    public void removeBackgroundColor() {
        this.gridPane.setStyle("");
    }
}
