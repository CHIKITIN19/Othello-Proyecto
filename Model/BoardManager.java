/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import Controller.BoardManagerController;
import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class BoardManager {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Piece[][] board;
    private BoardManagerController controller;

    public BoardManager(BoardManagerController controller, String player1Name, String player2Name) {
        this.controller = controller;
        board = new Piece[12][12];
        player1 = new Player("Red", player1Name);
        player2 = new Player("Purple", player2Name);
        currentPlayer = player1;


        board[5][5] = new Piece("Red");
        board[6][6] = new Piece("Red");
        board[5][6] = new Piece("Purple");
        board[6][5] = new Piece("Purple");
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
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


    
    //Metodo recursivo evaluar posiciones
    private boolean evaluationPositionRecursivo(int row, int column, int deltaRow, int deltaColumn, String playerColor, boolean foundOpponentPiece) {
        int i = row + deltaRow;
        int j = column + deltaColumn;

        if (i < 0 || i >= 12 || j < 0 || j >= 12) {
            return false;
        }

        if (board[i][j] == null) {
            return false;
        }

        if (board[i][j].getColors().equals(currentPlayer.getColors())) {
            return foundOpponentPiece;
        } else {
            foundOpponentPiece = true;
        }

        return evaluationPositionRecursivo(i, j, deltaRow, deltaColumn, playerColor, foundOpponentPiece);
    }
    
    
        public boolean isValidMove(int row, int column) {
        if (board[row][column] != null) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (evaluationPositionRecursivo(row, column, i, j, currentPlayer.getColors(), false)) {
                   
                    return true;
                }
            }
        }
        return false;
    }

        //Coloca la ficha
    public void placePiece(int row, int column) {
        if (isValidMove(row, column)) {
            board[row][column] = new Piece(currentPlayer.getColors());

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (evaluationPositionRecursivo(row, column, i, j, currentPlayer.getColors(), false)) {
                        updatePieceRecursivo(row, column, i, j, currentPlayer.getColors());
                    }
                }
            }
            changeTurn();
        }
    }

    // Método Recursivo modificar fichas
    private void updatePieceRecursivo(int row, int column, int deltaRow, int deltaColumn, String playerColor) {
        int i = row + deltaRow;
        int j = column + deltaColumn;

        
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return; 
        }
        
        if (board[i][j].getColors().equals(playerColor)) {
            return; 
        }
        
        String nuevoColor = playerColor.equals("Purple") ? "Purple" : "Red";
        controller.animationUpdatePiece(i, j, nuevoColor);
        board[i][j].FlipColors();
        updatePieceRecursivo(i, j, deltaRow, deltaColumn, playerColor);
    }

    public void changeTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public int possibleMovement(String color) {
        int movements = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (isValidMove(i, j)) {
                    if (currentPlayer.getColors().equals(color)) {
                        movements++;
                    }
                }
            }
        }

        return movements;
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

    public void resetBoard() {
        this.board = new Piece[12][12];
        for (int i = 5; i <= 6; i++) {
            for (int j = 5; j <= 6; j++) {
                if (i == 5 && j == 5) {
                    this.board[i][j] = new Piece("Red");
                } else if (i == 6 && j == 6) {
                    this.board[i][j] = new Piece("Red");
                } else if (i == 5 && j == 6) {
                    this.board[i][j] = new Piece("Purple");
                } else if (i == 6 && j == 5) {
                    this.board[i][j] = new Piece("Purple");
                }
            }
        }
        this.currentPlayer = player1;
    }
    
    public void setController(BoardManagerController controller) {
        this.controller = controller;
    }
}
