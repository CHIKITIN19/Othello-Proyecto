package Controller;

import Model.Board;
import Model.BoardManager;
import Model.Command.BoardCommand;
import Model.Observer.IObserver;
import Model.Player;
import View.Vista;

public class BoardManagerController {
    private Vista view;
    private BoardManager boardManager;

    public BoardManagerController(Vista view) {
        boardManager = BoardManager.getInstance();
        this.view = view;
        if (view instanceof IObserver iObserver) {
            boardManager.addObserver(iObserver);
        }
    }
    
    public Player setWinner(){
        int chipsPlayer1 = boardManager.countPlayer1Pieces();
        int chipsPlayer2 = boardManager.countPlayer2Pieces();

        if (chipsPlayer1 > chipsPlayer2) {
            return boardManager.getPlayer1();
        } else if (chipsPlayer2 > chipsPlayer1) {
            return boardManager.getPlayer2();
        } else {
            if (boardManager.possibleMovement(boardManager.getCurrentPlayer().getColors()) == 0) {
                return null;
            } else {
                boardManager.changeTurn();
                return setWinner();
            }
        }
    }
    
    public void executeCommand(BoardCommand command){
        command.execute();
    }
    
}
