package agh.ics.oop.app.engine;

import agh.ics.oop.Tuple;
import agh.ics.oop.app.gui.elements.BigBoardGrid;
import agh.ics.oop.boards.BigBoard;
import agh.ics.oop.enums.Player;
import agh.ics.oop.enums.BoardPosition;
import javafx.scene.layout.GridPane;

public class Engine {
    public Player turn;
    public BoardPosition nextBoardPosition = null;
    public BigBoard bigBoard;
    public BigBoardGrid bigBoardGrid;

    public Engine() {
        this.bigBoard = new BigBoard();
        this.bigBoardGrid = new BigBoardGrid(bigBoard, this);
        this.turn = Player.X;
    }

    public void nextTurn(int i, int j) {
        int iPrev = -1;
        int jPrev = -1;
        if (nextBoardPosition != null) {
            iPrev = this.nextBoardPosition.toTuple().i;
            jPrev = this.nextBoardPosition.toTuple().j;
        }
        this.bigBoardGrid.changeFocusOnBoard(iPrev, jPrev, i, j);

        Tuple tuple = new Tuple(i, j);
        this.turn = this.turn.nextTurn();
        this.nextBoardPosition = BoardPosition.tupleToBoardPosition(tuple);

    }

    public GridPane getBigBoardGrid() {
        return bigBoardGrid.getGridPane();
    }
}
