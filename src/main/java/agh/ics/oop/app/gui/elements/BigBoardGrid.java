package agh.ics.oop.app.gui.elements;

import agh.ics.oop.Tuple;
import agh.ics.oop.app.engine.Engine;
import agh.ics.oop.boards.BigBoard;
import agh.ics.oop.boards.SmallBoard;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BigBoardGrid {
    private final Engine engine;
    private final GridPane gridPane;
    private final BigBoard bigBoard;
    private final SmallBoardGrid[][] smallBoardGrids = new SmallBoardGrid[3][3];
    private static final int smallBoardSize = SmallBoardGrid.cellSize * 3 + 10;

    public BigBoardGrid(BigBoard bigBoard, Engine engine) {
        this.bigBoard = bigBoard;
        this.engine = engine;
        this.gridPane = new GridPane();

        this.gridPane.setStyle("-fx-background-color: lightgreen;");

        for (int i = 0; i < 3; i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(smallBoardSize));
            this.gridPane.getRowConstraints().add(new RowConstraints(smallBoardSize));
        }

        SmallBoard[][] smallBoards = this.bigBoard.getSmallBoards();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.smallBoardGrids[i][j] = new SmallBoardGrid(smallBoards[i][j], this.engine, this);
                this.gridPane.add(smallBoardGrids[i][j].getSmallBoardBox(), j, i);
                this.smallBoardGrids[i][j].getGridPane().setAlignment(Pos.CENTER);
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void changeFocusOnBoard(Tuple prevTuple, Tuple nextTuple) {
        if (prevTuple != null) {
            this.smallBoardGrids[prevTuple.i][prevTuple.j].removeBackgroundColor();
        } else {
            this.gridPane.setStyle("");
        }
        if (nextTuple != null) {
            this.smallBoardGrids[nextTuple.i][nextTuple.j].changeBackgroundColor();
        } else {
            this.gridPane.setStyle("-fx-background-color: lightgreen;");
        }
    }

}
