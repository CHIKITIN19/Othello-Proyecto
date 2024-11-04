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
public class AnimationPiece {
    private final BoardManagerController controller;
    private final String[] secuenciaNegra = {
        "/IMG/Ficha_1.png",
        "/IMG/Ficha_1.png",
        "/IMG/Ficha_1.png"
    };
    
    private final String[] secuenciaRoja = {
        "/IMG/Ficha_2.png",
        "/IMG/Ficha_2.png",
        "/IMG/Ficha_1.png"
    };

    public AnimationPiece(BoardManagerController controller) {
        this.controller = controller;
    }
    
   public void animarCambioFicha(int fila, int columna, String colorFinal) {
        new Thread(() -> {
            try {
                JButton boton = controller.getFrmJuego().botonesTablero[fila][columna];
                boton.putClientProperty("enAnimacion", true);

                // Determinar la secuencia de animación
                String colorActual = controller.getBoardManager().getBoard()[fila][columna].getColors();
                String[] secuencia;

                if (colorActual.equals("Red") && colorFinal.equals("Purple")) {
                    secuencia = secuenciaRoja; // Cambiar de rojo a negro
                } else if (colorActual.equals("Purple") && colorFinal.equals("Red")) {
                    secuencia = secuenciaNegra; // Cambiar de negro a rojo
                } else {
                    // Si no es una conversión válida, simplemente salimos
                    return;
                }

                // Animación de cambio de color
                for (String rutaImagen : secuencia) {
                    ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
                    Image imagen = iconoOriginal.getImage();
                    Image nuevaImagen = imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    ImageIcon iconoFinal = new ImageIcon(nuevaImagen);

                    SwingUtilities.invokeLater(() -> boton.setIcon(iconoFinal));
                    Thread.sleep(400); // Tiempo entre cada cambio de imagen
                }

                // Actualizar el color final de la ficha en el tablero
                controller.getBoardManager().getBoard()[fila][columna].setColors(colorFinal);
                // Actualiza la visualización del tablero
                controller.updateBoard();
                boton.putClientProperty("enAnimacion", false);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
