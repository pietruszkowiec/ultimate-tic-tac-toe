package agh.ics.oop.app;

import agh.ics.oop.Tuple;
import agh.ics.oop.app.gui.App;
import agh.ics.oop.app.gui.elements.BigBoardGrid;
import agh.ics.oop.boards.BigBoard;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.states.BoardState;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Engine {
    public Player turn;
    public Tuple nextBoardPositionTuple = null;
    public final App app;
    public final BigBoard bigBoard;
    public final BigBoardGrid bigBoardGrid;
    private final Label label;
    private boolean finished = false;

    public Engine(App app) {
        this.app = app;
        this.bigBoard = new BigBoard();
        this.bigBoardGrid = new BigBoardGrid(bigBoard, this);
        this.turn = Player.X;
        this.label = new Label(this.turn + " turn");
        this.label.setFont(new Font(16));
    }

    public void nextTurn(Tuple nextTuple) {
        Tuple prevTuple = null;

        if (this.nextBoardPositionTuple != null) {
            prevTuple = this.nextBoardPositionTuple;
        }

        if (this.bigBoard.getSmallBoardState(nextTuple) == BoardState.ONGOING) {
            this.bigBoardGrid.changeFocusOnBoard(prevTuple, nextTuple);
            this.nextBoardPositionTuple = nextTuple;
        } else {
            this.bigBoardGrid.changeFocusOnBoard(prevTuple, null);
            this.nextBoardPositionTuple = null;
        }

        if (this.finished) {
            this.bigBoardGrid.changeFocusOnBoard(null, null);
            System.out.println("Game has ended");
            this.app.closeApp();
            return;
        }

        this.turn = this.turn.nextTurn();
        this.label.setText(this.turn + " turn");
    }

    public BorderPane getMainBox() {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(this.label);
        borderPane.setCenter(this.bigBoardGrid.getGridPane());
        BorderPane.setAlignment(this.label, Pos.CENTER);
        return borderPane;
    }

    public void finishGame() {
        this.label.setText(this.bigBoard.getState() + "");
        this.finished = true;
    }
}
