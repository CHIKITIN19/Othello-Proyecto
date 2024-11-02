/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.Observer.IObserved;
import Model.Observer.IObserver;
import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class BoardManager implements IObserved {
    private static BoardManager instance;
    ArrayList<IObserver> observers;
    private Board board;

    private BoardManager() {
        this.board = new Board();
        this.observers = new ArrayList<>();
    }
    
    
    public static BoardManager getInstance() {
        if (instance == null){
            instance = new BoardManager();
        }
        return instance;
    }
    
    public static void resetInstance(){
        instance = null;
    }
    
    
     @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver(Board board) {
        for (IObserver observer:observers) {
            observer.update(board);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
    
    public boolean isValidMove(int row, int column) {
        if (board.getBoard()[row][column] != null) {
            return false;
        }
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j == 0 && k == 0) {
                    continue;
                }
                if (validateCapture(row, column, j, k, board.getCurrentPlayer().getColors(), true)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateCapture(int row, int column, int deltaRow, int deltaColumn, String playerColor, boolean flipPiece) {
        int j = row + deltaRow;
        int k = column + deltaColumn;
        Piece[][] boardArray = board.getBoard();

        if (j < 0 || j >= 12 || k < 0 || k >= 12 || boardArray[j][k] == null) {
            return false;
        }
        if (boardArray[j][k].getColors().equals(playerColor)) {
            return flipPiece;
        }
        return validateCapture(j, k, deltaRow, deltaColumn, playerColor, true);
    }

    public void placePiece(int row, int column) {
        if (isValidMove(row, column)) {
            board.getBoard()[row][column] = new Piece(board.getCurrentPlayer().getColors());
        }
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j == 0 && k == 0) {
                    continue;
                }
                if (validateCapture(row, column, j, k, board.getCurrentPlayer().getColors(), false)) {
                    makeMovements(row, column, j, k, board.getCurrentPlayer().getColors());
                }
            }
        }
        changeTurn();
        notifyObserver(board);  // Notifica a los observadores después de hacer un movimiento
    }

    private void makeMovements(int row, int column, int deltaRow, int deltaColumn, String playerColor) {
        int newRow = row + deltaRow;
        int newColumn = column + deltaColumn;
        Piece[][] boardArray = board.getBoard();

        while (newRow >= 0 && newRow < 12 && newColumn >= 0 && newColumn < 12
                && boardArray[newRow][newColumn] != null
                && !boardArray[newRow][newColumn].getColors().equals(playerColor)) {
            boardArray[newRow][newColumn].FlipColors();
            newRow += deltaRow;
            newColumn += deltaColumn;
        }
    }

    public void changeTurn() {
        board.setCurrentPlayer(
            board.getCurrentPlayer() == board.getPlayer1() ? board.getPlayer2() : board.getPlayer1()
        );
    }

    // Cuenta las piezas de juagdor1
    public int countPlayer1Pieces() {
        int count = 0;
        Piece[][] boardArray = board.getBoard();
        for (int j = 0; j < 12; j++) {
            for (int k = 0; k < 12; k++) {
                if (boardArray[j][k] != null && boardArray[j][k].getColors().equals(board.getPlayer1().getColors())) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // Cuenta las piezas de juagdor2
    public int countPlayer2Pieces() {
        int count = 0;
        Piece[][] boardArray = board.getBoard();
        for (int j = 0; j < 12; j++) {
            for (int k = 0; k < 12; k++) {
                if (boardArray[j][k] != null && boardArray[j][k].getColors().equals(board.getPlayer2().getColors())) {
                    count++;
                }
            }
        }
        return count;
    }
}
