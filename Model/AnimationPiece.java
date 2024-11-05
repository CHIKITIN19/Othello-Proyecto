/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.BoardManagerController;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author 9567
 */
public class AnimationPiece implements Runnable {
    private final BoardManagerController controller;
    private final int fila;
    private final int columna;
    private final String colorFinal;

    private final String[] secuenciaPurple = {
        "/IMG/Ficha_3.png",
        "/IMG/Ficha_3.png",
        "/IMG/Ficha_2.png"
    };

    private final String[] secuenciaRoja = {
        "/IMG/Ficha_4.png",
        "/IMG/Ficha_4.png",
        "/IMG/Ficha_1.png"
    };

    public AnimationPiece(BoardManagerController controller, int fila, int columna, String colorFinal) {
        this.controller = controller;
        this.fila = fila;
        this.columna = columna;
        this.colorFinal = colorFinal;
    }

    @Override
    public void run() {
        try {
            JButton boton = controller.getFrmJuego().botones[fila][columna];
            boton.putClientProperty("enAnimacion", true);

            String colorActual = controller.getBoardManager().getBoard()[fila][columna].getColors();
            String[] secuencia;

            if (colorActual.equals("Red") && colorFinal.equals("Purple")) {
                secuencia = secuenciaPurple; 
            } else if (colorActual.equals("Purple") && colorFinal.equals("Red")) {
                secuencia = secuenciaRoja; 
            } else {
                secuencia = colorFinal.equals("Purple") ? secuenciaPurple : secuenciaRoja;
            }

            for (String rutaImagen : secuencia) {
                ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
                Image imagen = iconoOriginal.getImage();
                Image nuevaImagen = imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon iconoFinal = new ImageIcon(nuevaImagen);

                SwingUtilities.invokeLater(() -> boton.setIcon(iconoFinal));
                Thread.sleep(400); 
            }
            
            controller.getBoardManager().getBoard()[fila][columna].setColors(colorFinal);
            boton.putClientProperty("enAnimacion", false); // Finalizar la animación

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

