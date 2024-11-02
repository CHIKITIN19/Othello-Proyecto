/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Command;

import Model.Board;
import Model.BoardManager;

/**
 *
 * @author sebas
 */
public abstract class BoardCommand {
    protected final Board board;
    protected final BoardManager boardManager;

    public BoardCommand(Board board) {
        this.board = board;
        this.boardManager = BoardManager.getInstance();
    }
    
    public abstract void execute();
    
}
