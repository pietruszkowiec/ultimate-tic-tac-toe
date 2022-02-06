package agh.ics.oop.app.gui.elements;

import agh.ics.oop.Tuple;
import agh.ics.oop.app.engine.Engine;
import agh.ics.oop.boards.SmallBoard;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.BoardPosition;
import agh.ics.oop.enums.states.BoardState;
import javafx.scene.layout.*;

public class SmallBoardGrid {
    private final Engine engine;
    private final BorderPane smallBoardBox;
    private final GridPane gridPane;
    private final SmallBoard smallBoard;
    private final FieldButton[][] fields = new FieldButton[3][3];
    public static final int cellSize = 60;

    public SmallBoardGrid(SmallBoard smallBoard, Engine engine) {
        this.smallBoard = smallBoard;
        this.engine = engine;
        this.gridPane = new GridPane();
        this.smallBoardBox = new BorderPane();
        this.smallBoardBox.setCenter(this.gridPane);

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

    public Pane getSmallBoardBox() {
        return smallBoardBox;
    }

    public void onClick(int i, int j) {
        if (this.engine.nextBoardPositionTuple != null
                && !(this.engine.nextBoardPositionTuple.equals(this.smallBoard.getBigBoardPositionTuple()))) {
            return;
        }
        Player player = this.engine.turn;
        BoardPosition boardPosition;
        Tuple tuple;
        if (this.smallBoard.getBoardState() == BoardState.ONGOING && this.smallBoard.makeMove(i, j, player) ) {
            this.fields[i][j].changeState(player);
            tuple = new Tuple(i, j);


            String playerImageFile;
            if(this.smallBoard.checkForChangeOfState()) {
                if (this.smallBoard.getBoardState() == BoardState.DRAW) {

                    this.smallBoardBox.setStyle("-fx-background-color: yellow;\n" +
                            "-fx-background-image: url(DRAW.png);\n" +
                            "-fx-background-size: " + BigBoardGrid.smallBoardSize + "px "
                                + BigBoardGrid.smallBoardSize + "px;");
                } else {
                    playerImageFile = player + ".png";
                    this.smallBoardBox.setStyle("-fx-background-color: lightblue;\n" +
                            "-fx-background-image: url(" + playerImageFile +");\n" +
                            "-fx-background-size: " + BigBoardGrid.smallBoardSize + "px "
                                + BigBoardGrid.smallBoardSize + "px;");
                }
            }

            this.engine.nextTurn(tuple);

            boardPosition = BoardPosition.tupleToBoardPosition(this.smallBoard.bigBoardPositionTuple);
            System.out.print(boardPosition + " / ");
            boardPosition = BoardPosition.tupleToBoardPosition(tuple);
            System.out.println(boardPosition);

        }
    }

    public void changeBackgroundColor() {
        this.gridPane.setStyle("-fx-background-color: lightgreen;");
    }

    public void removeBackgroundColor() {
        this.gridPane.setStyle("");
    }
}
