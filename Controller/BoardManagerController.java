package Controller;

import Model.AnimationPiece;
import Model.BoardManager;

import Model.Piece;
import Model.Player;
import View.FrmJuego;
import View.Vista;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BoardManagerController {
    private Vista view;
    private BoardManager boardManager;
    private FrmJuego frmJuego;
    private static BoardManagerController instance;
    private AnimationPiece animation;

    public BoardManager getBoardManager() {
        return boardManager;
    }
    

    public BoardManagerController(FrmJuego frmJuego, Vista view) {
        boardManager = BoardManager.getInstance();
        this.view = view;
        this.frmJuego = frmJuego;
        this.animation = new AnimationPiece(this);
    }
    
    public static BoardManagerController getInstance(FrmJuego frmJuego, Vista view) {
        if (instance == null) {
            instance = new BoardManagerController(frmJuego, view);
        }
        return instance;
    }
    
    public Player setWinner() {
        int chipsPlayer1 = boardManager.countPlayer1Pieces();
        int chipsPlayer2 = boardManager.countPlayer2Pieces();

        if (chipsPlayer1 > chipsPlayer2) {
            return boardManager.getPlayer1();
        } else if (chipsPlayer2 > chipsPlayer1) {
            return boardManager.getPlayer2();
        } else {
            if (boardManager.possibleMovement(boardManager.getCurrentPlayer().getColors()) == 0) {
                return null;
            } else {
                boardManager.changeTurn();
                return setWinner();
            }
        }
    }
    
    public void createBoard() {
        // Ya no es necesario, ya que el tablero se crea en BoardManager
        updateBoard();
    }

    public Player getPlayer1() {
        return boardManager.getPlayer1();
    }

    public Player getPlayer2() {
        return boardManager.getPlayer2();
    }

    public Player getPlayerCurrent() {
        return boardManager.getCurrentPlayer();
    }

    public int countTokensPlayer() {
        return boardManager.countPlayer1Pieces();
    }

    public int countTokensPlayer2() {
        return boardManager.countPlayer2Pieces();
    }
    
 
    
    public void hacerMovimiento(int fila, int columna) {
        try {
            if (boardManager.isValidMove(fila, columna)) { // Verifica si el movimiento es válido
                String colorActual = boardManager.getBoard()[fila][columna] != null 
                    ? boardManager.getBoard()[fila][columna].getColors() 
                    : null;

                boardManager.placePiece(fila, columna);

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) 
                            continue; 
                        if (boardManager.validateCapture(fila, columna, i, j, boardManager.getCurrentPlayer().getColors(), false)) {
                            
                            animateCapturedPieces(fila, columna, i, j, colorActual);
                        }
                    }
                }

                updateBoard(); 
                frmJuego.UpdateShift(); 
                frmJuego.UpdateGameScore(); 
                
                // Verifica si hay movimientos posibles para el jugador actual
                if (boardManager.possibleMovement(boardManager.getCurrentPlayer().getColors()) == 0) {
                    Player ganador = setWinner(); // Determina el ganador
                    if (ganador != null) {
                        view.show(ganador.getName() + " ha ganado");
                    } else {
                        view.show("¡Empate!");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void animateCapturedPieces(int fila, int columna, int deltaRow, int deltaColumn, String colorActual) {
        int newRow = fila + deltaRow;
        int newColumn = columna + deltaColumn;

        while (boardManager.getBoard()[newRow][newColumn] != null && 
               !boardManager.getBoard()[newRow][newColumn].getColors().equals(colorActual)) {
            
            String colorFinal = boardManager.getCurrentPlayer().getColors();
            animation.animarCambioFicha(newRow, newColumn, colorFinal);
            newRow += deltaRow;
            newColumn += deltaColumn;
        }
    }
    public synchronized void updateBoard() {
        Piece[][] currentBoard = boardManager.getBoard(); // Accede al tablero desde BoardManager
        ImageIcon redPieceIcon = new ImageIcon(getClass().getResource("/IMG/Ficha_1.png"));
        ImageIcon purplePieceIcon = new ImageIcon(getClass().getResource("/IMG/Ficha_2.png"));
        Image redImage = redPieceIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Image purpleImage = purplePieceIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon redPiece = new ImageIcon(redImage);
        ImageIcon purplePiece = new ImageIcon(purpleImage);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                JButton boton = frmJuego.botonesTablero[i][j];
                if (currentBoard[i][j] != null) {
                    
                    if (currentBoard[i][j].getColors().equals("Red")) {
                        boton.setIcon(redPiece);
                    } else {
                        boton.setIcon(purplePiece);
                    }
                } else {
                    
                    boton.setIcon(null);
                    if (boardManager.isValidMove(i, j)) {
                        boton.setBackground(Color.BLUE);
                    } else {
                        boton.setBackground(Color.GREEN);
                    }
                }
            }
        }

        
        frmJuego.UpdateShift();
        frmJuego.UpdateGameScore();
    }

    public FrmJuego getFrmJuego() {
        return frmJuego;
    }
}
