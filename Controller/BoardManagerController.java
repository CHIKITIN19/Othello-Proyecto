package Controller;

import Model.Board;
import Model.BoardManager;
import Model.Command.BoardCommand;
import Model.Observer.IObserver;
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
    
    public void executeCommand(BoardCommand command){
        command.execute();
    }
    
}
