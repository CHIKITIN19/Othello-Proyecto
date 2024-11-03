/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.BoardManagerController;

/**
 *
 * @author 9567
 */
public class Board{
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Piece[][] board;
    private BoardManagerController controller;
    
    public Board(BoardManagerController controller) {
        board = new Piece[12][12];
        player1 = new Player("Red", "Player 1");
        player2 = new Player("Purple", "Player 2");
        currentPlayer = player1;
        
        // Lugar donde las fichas inician
        board[5][5] = new Piece("Red");
        board[6][6] = new Piece("Red");
        board[5][6] = new Piece("Purple");
        board[6][5] = new Piece("Purple");
    }

    public Board() {
        this(null);
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
