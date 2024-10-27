/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 9567
 */
public class Board {
    private Player player1;
    private Player player2;
    private Player CurrentPlayer;
    private Piece[][] board;
    
    public Board(){
        board = new Piece[12][12];
        player1 = new Player("White", "Player 1");
        player2 = new Player("Black", "Player 2");
        CurrentPlayer = player1;
        //Lugar donde las fichas inician
        board[5][5] = new Piece("White");
        board[6][6] = new Piece("White");
        board[5][6] = new Piece("Black");
        board[6][5] = new Piece("Black");
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

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return CurrentPlayer;
    }

    public void setCurrentPlayer(Player CurrentPlayer) {
        this.CurrentPlayer = CurrentPlayer;
    }
    //Caso base si sale del tablero, encuentra una posicion vacia
    //O simplemente no encuentra un rodeo, termina 
    private boolean Movements(int row, int column, int deltaRow, int deltaColumn, String playerColor, boolean flipPiece){
        int j = row + deltaRow;
        int k = column + deltaColumn;
        
        if (j<0 || j>=12 || k<0 || k>=12 || board[j][k] == null) {
            return false;
        }
        if(board[j][k].getColors().equals(playerColor)){
            return flipPiece;
        }
        return Movements(j,k, deltaRow, deltaColumn, playerColor, true);
    }
}
