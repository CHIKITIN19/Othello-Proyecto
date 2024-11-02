package Controller;

import Model.Board;
import Model.BoardManager;
import View.Vista;

public class BoardManagerController {
    private Vista view;
    private BoardManager boardManager;

    public BoardManagerController(Vista view) {
        boardManager = BoardManager.getInstance();
        this.view = view;
    }
    
    
}
