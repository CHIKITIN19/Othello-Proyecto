/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.BoardManagerController;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author 9567
 */
public class AnimationPiece {
    private final BoardManagerController controller;
    private final String[] secuenciaNegra = {
        "/IMG/fichaVolteo2.png",
        "/IMG/fichaVolteo2.png",
        "/IMG/ficha_1png"
    };
    
    private final String[] secuenciaRoja = {
        "/IMG/fichaVolteo.png",
        "/IMG/fichaVolteo.png",
        "/IMG/ficha2.png"
    };

    public AnimationPiece(BoardManagerController controller) {
        this.controller = controller;
    }
    
//    public void animarCambioFicha(int fila, int columna, String colorFinal) {
//    new Thread(() -> {
//        try {
//            //el getOthello es el controller
//            //al igual que el getTablero
//            JButton boton = controller.getOthello().botonesTablero[fila][columna];
//            boton.putClientProperty("enAnimacion", true); // Iniciar la animación
//            
//            // Verificamos el color de la ficha en el tablero antes de la animación
//            String colorActual = controller.getTablero().getTablero()[fila][columna].getColor();
//            String[] secuencia;
//
//            
//            if (colorActual.equals("FICHA ROJA") && colorFinal.equals("FICHA NEGRA")) {
//                secuencia = secuenciaNegra; // Animación de ficha negra
//            } else if (colorActual.equals("FICHA NEGRA") && colorFinal.equals("FICHA ROJA")) {
//                secuencia = secuenciaRoja; // Animación de ficha roja
//            } else {
//                secuencia = colorFinal.equals("FICHA NEGRA") ? secuenciaNegra : secuenciaRoja;
//            }
//
//            for (String rutaImagen : secuencia) {
//                ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
//                Image imagen = iconoOriginal.getImage();
//                Image nuevaImagen = imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//                ImageIcon iconoFinal = new ImageIcon(nuevaImagen);
//
//                boton.setIcon(iconoFinal);
//                Thread.sleep(800);
//            }
//
//            // Actualizar el estado de la ficha en el tablero
//            //para el controller
//            controller.getTablero().getTablero()[fila][columna].setColor(colorFinal);
//
//            // Finalmente, actualiza el tablero para reflejar los cambios
//            //para el controller
//            controller.updateTablero();
//
//            boton.putClientProperty("enAnimacion", false); // Finalizar la animación
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }).start();
//}
}
