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
 * @author 9567
 */
public class Board{
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Piece[][] board; 
    
    public Board() {
        board = new Piece[12][12];
        player1 = new Player("White", "Player 1");
        player2 = new Player("Black", "Player 2");
        currentPlayer = player1;
        
        // Lugar donde las fichas inician
        board[5][5] = new Piece("White");
        board[6][6] = new Piece("White");
        board[5][6] = new Piece("Black");
        board[6][5] = new Piece("Black");
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
