/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Command;

import Model.Board;
import Model.Piece;
import View.FrmJuego;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author sebas
 */
public class UpdateBoardCommand extends BoardCommand {
    FrmJuego frmJuego;

    public UpdateBoardCommand(FrmJuego frmJuego,Board board) {
        super(board);
        this.frmJuego = frmJuego;
    }

    @Override
    public void execute() {
        Piece[][] currentBoard = board.getBoard();
        ImageIcon redPiece = new ImageIcon(getClass().getResource("/IMG/Ficha_1.png"));
        ImageIcon purplePiece = new ImageIcon(getClass().getResource("/IMG/Ficha_2.png"));
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (currentBoard[i][j] !=null) {
                    if (currentBoard[i][j].getColors().equals("Red")) {
                        frmJuego.botonesTablero[i][j].setIcon(redPiece);
                    }else{
                        frmJuego.botonesTablero[i][j].setIcon(purplePiece);
                    }
                }else{
                    if (boardManager.isValidMove(i, j)) {
                        frmJuego.botonesTablero[i][j].setBackground(Color.BLUE);
                    }else{
                        frmJuego.botonesTablero[i][j].setBackground(Color.red);
                    }
                }
            }
            frmJuego.UpdateShift();
            frmJuego.UpdateGameScore();
        }
    }
    
}
