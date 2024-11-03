/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Command;

import Controller.BoardManagerController;
import Model.Board;
import Model.Player;
import View.FrmJuego;

/**
 *
 * @author sebas
 */
public class MakeMovementCommand extends BoardCommand {
    FrmJuego frmJuego;
    int file;
    int row;
    BoardManagerController controller;

    public MakeMovementCommand(FrmJuego frmJuego,BoardManagerController controller,Board board, int file, int row) {
        super(board);
        this.frmJuego= frmJuego;
        this.file = file;
        this.row = row;
        this.controller = controller;
    }

    @Override
    public void execute() {
        if (boardManager.isValidMove(row, file)) {
            boardManager.placePiece(row, file);
            BoardCommand command = new UpdateBoardCommand(frmJuego, board);
            controller.executeCommand(command);
            frmJuego.UpdateShift();
            frmJuego.UpdateGameScore();
            
            if (boardManager.possibleMovement(boardManager.getCurrentPlayer().getColors()) == 0) {
                Player winner = controller.setWinner();
                if (winner != null) {
                    frmJuego.showMessage("El jugador: "+winner.getName()+" Ganó");
                }else{
                    frmJuego.showMessage("Empate");
                }
            }
        }else{
            frmJuego.showMessage("Error, el movimiento no es válido");
        }
    }
    
}
