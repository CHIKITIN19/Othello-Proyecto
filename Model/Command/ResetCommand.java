/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Command;

import Model.Board;
import View.FrmJuego;
import java.awt.Color;

/**
 *
 * @author sebas
 */
public class ResetCommand extends BoardCommand {
    FrmJuego frmJuego;

    public ResetCommand(FrmJuego frmJuego,Board board) {
        super(board);
        this.frmJuego = frmJuego;
    }

    @Override
    public void execute() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                frmJuego.botonesTablero[i][j].setIcon(null);
                frmJuego.botonesTablero[i][j].setBackground(Color.WHITE);
            }
        }
    }
    
}
