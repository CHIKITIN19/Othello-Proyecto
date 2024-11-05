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
    private static BoardManagerController instance; 
    private Vista view;
    private BoardManager boardManager;
    private FrmJuego frmJuego;
    private AnimationPiece animation;

    
    private BoardManagerController(FrmJuego frmJuego, Vista view, String player1Name, String player2Name) {
        this.frmJuego = frmJuego; 
        this.view = view;
        boardManager = new BoardManager(this, player1Name, player2Name);
    }

    
    public static BoardManagerController getInstance(FrmJuego frmJuego, Vista view, String player1Name, String player2Name) {
        if (instance == null) {
            instance = new BoardManagerController(frmJuego, view, player1Name, player2Name);
        }
        return instance;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
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

    public void move(int fila, int columna) {
        try {
            if (boardManager.isValidMove(fila, columna)) {
                boardManager.placePiece(fila, columna);
                updateBoard();
                frmJuego.UpdateShift();
                frmJuego.UpdateGameScore();

                if (boardManager.possibleMovement(boardManager.getPlayer1().getColors()) == 0 &&
                    boardManager.possibleMovement(boardManager.getPlayer2().getColors()) == 0) {

                    Player ganador = setWinner();
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

public synchronized void updateBoard() {
    Piece[][] currentBoard = boardManager.getBoard();
    ImageIcon redPieceIcon = new ImageIcon(getClass().getResource("/IMG/Ficha_1.png"));
    ImageIcon purplePieceIcon = new ImageIcon(getClass().getResource("/IMG/Ficha_2.png"));
    Image redImage = redPieceIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    Image purpleImage = purplePieceIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon redPiece = new ImageIcon(redImage);
    ImageIcon purplePiece = new ImageIcon(purpleImage);
    
    for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 12; j++) {
            JButton boton = frmJuego.botones[i][j];
            boton.setBackground(Color.WHITE); 
        }
    }

    for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 12; j++) {
            JButton boton = frmJuego.botones[i][j];
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


    public void animationUpdatePiece(int fila, int columna, String colorFinal) {
        
        AnimationPiece animation = new AnimationPiece(this, fila, columna, colorFinal);
        new Thread(animation).start();
    }

    public FrmJuego getFrmJuego() {
        return frmJuego;
    }
    public void reset() {
        System.out.println("Reiniciando el tablero...");
        boardManager.resetBoard(); // Reiniciar el estado del BoardManager
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                frmJuego.botones[i][j].setIcon(null); 
                frmJuego.botones[i][j].setBackground(Color.WHITE);
            }
        }
        updateBoard(); // Actualizar el tablero después de reiniciar
        System.out.println("Tablero reiniciado.");
    }
}
