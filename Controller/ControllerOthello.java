package Controller;

import Model.Board;
import View.Vista;

public class ControllerOthello {
    private Vista view;
    private Board board;

    public ControllerOthello(Vista view) {
        board = Board.getInstance();
        this.view = view;
    }
    
    
}
