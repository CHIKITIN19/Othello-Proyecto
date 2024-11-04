/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class BoardManager {
    private static BoardManager instance;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Piece[][] board;
    
    public BoardManager() {
        board = new Piece[12][12];
        player1 = new Player("Red", "Player 1");
        player2 = new Player("Purple", "Player 2");
        currentPlayer = player1;
        
        // Inicializar el tablero con las piezas iniciales
        board[5][5] = new Piece("Red");
        board[6][6] = new Piece("Red");
        board[5][6] = new Piece("Purple");
        board[6][5] = new Piece("Purple");
    }
    
    public static BoardManager getInstance() {
        if (instance == null) {
            instance = new BoardManager();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
    
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public boolean isValidMove(int row, int column) {
        if (board[row][column] != null) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (validateCapture(row, column, i, j, currentPlayer.getColors(), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validateCapture(int row, int column, int deltaRow, int deltaColumn, String playerColor, boolean foundOpponentPiece) {
        int newRow = row + deltaRow;
        int newColumn = column + deltaColumn;

        if (newRow < 0 || newRow >= 12 || newColumn < 0 || newColumn >= 12) {
            return false;
        }
        
        if (board[newRow][newColumn] == null) {
            return false;
        }

        if (board[newRow][newColumn].getColors().equals(playerColor)) {
            return foundOpponentPiece;
        } else {
            foundOpponentPiece = true;
        }

        return validateCapture(newRow, newColumn, deltaRow, deltaColumn, playerColor, foundOpponentPiece);
    }

    public void placePiece(int row, int column) {
        if (isValidMove(row, column)) {
            board[row][column] = new Piece(currentPlayer.getColors());

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    if (validateCapture(row, column, i, j, currentPlayer.getColors(), false)) {
                        makeMovements(row, column, i, j, currentPlayer.getColors());
                    }
                }
            }

            changeTurn();
        }
    }

    private void makeMovements(int row, int column, int deltaRow, int deltaColumn, String playerColor) {
        int newRow = row + deltaRow;
        int newColumn = column + deltaColumn;

        while (!board[newRow][newColumn].getColors().equals(playerColor)) {
            board[newRow][newColumn].FlipColors();
            newRow += deltaRow;
            newColumn += deltaColumn;
        }
    }

    public int possibleMovement(String color) {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (isValidMove(i, j) && currentPlayer.getColors().equals(color)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void changeTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public int countPlayer1Pieces() {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (board[i][j] != null && board[i][j].getColors().equals(player1.getColors())) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countPlayer2Pieces() {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (board[i][j] != null && board[i][j].getColors().equals(player2.getColors())) {
                    count++;
                }
            }
        }
        return count;
    }
}