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
    private static Board instance;
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

    public static Board getInstance() {
        if (instance == null){
            instance = new Board();
        }
        return instance;
    }
    
    public static void resetInstance(){
        instance = null;
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
    private boolean ValidateCapture(int row, int column, int deltaRow, int deltaColumn, String playerColor, boolean flipPiece){
        int j = row + deltaRow;
        int k = column + deltaColumn;
        
        if (j<0 || j>=12 || k<0 || k>=12 || board[j][k] == null) {
            return false;
        }
        if(board[j][k].getColors().equals(playerColor)){
            return flipPiece;
        }
        return ValidateCapture(j,k, deltaRow, deltaColumn, playerColor, true);
    }
    public int PossibleMovents(String playerColor){
        int countMovements = 0;
        if (!CurrentPlayer.getColors().equals(playerColor)) {
            return 0;
        }
        for (int j = 0; j < 12; j++) {
            for (int k = 0; k < 12; k++) {
                if (ValidMovement(j,k)) {
                    countMovements++;
                }
            }
        }
        return countMovements;
    }
    public boolean ValidMovement (int row, int column){
        if(board[row][column] != null){
            return false;
        }
        for(int j = -1; j<=1; j++){
            for(int k = -1; k<=1; k++){
                if(j == 0 && j == 0){
                    continue;
                }
                if(ValidateCapture(row, column, j, k, CurrentPlayer.getColors(), true)){
                    return true;
                }
            }
        }
        return false;
    }
    public void MakeMovements(int row, int column, int deltaRow, int deltaColumn,String playerColor){
        int newRow = row + deltaRow;
        int newColumn = column + deltaColumn;
        
        while(newRow >=0 && newRow<12 && newColumn>= 0 && newColumn<12 && board[newRow][newColumn]
                != null && !board[newRow][newColumn].getColors().equals(playerColor)){
             
            board[newRow][newColumn].FlipColors();
            newRow += deltaRow;
            newColumn += deltaColumn;
        }
    }
    public void PlacePiece(int row, int column){
        if(ValidMovement(row, column)){
            board[row][column] = new Piece(CurrentPlayer.getColors());
        }
        for(int j = -1 ; j<=1; j++){
            for(int k = -1; k<=1; k++){
                if(j==0 && k == 0){
                    continue;
                }
                if(ValidateCapture(row, column, j, k, CurrentPlayer.getColors(), false)){
                    MakeMovements(row, column, j, k, CurrentPlayer.getColors());
                }
            }
            ChangeShift();
        }
    }
    public void ChangeShift(){
        if(CurrentPlayer == player1){
            CurrentPlayer = player2;
        }else{
            CurrentPlayer = player1;
        }
    }
    public int PlayerPiece1(){
        int count1 = 0;
        for (int j = 0; j < 12; j++) {
            for (int k = 0; k < 12; k++) {
                if(board[j][k] != null && board[j][k].getColors().equals(player1.getColors())){
                    count1++;
                }
            }
        }
        return count1;
    }
   public int PlayerPiece2(){
       int count2 = 0;
       for (int j = 0; j < 12; j++) {
           for (int k = 0; k < 12; k++) {
               if(board[j][k] != null && board[j][k].getColors().equals(player2.getColors())){
                   count2++;
               }
           }
       }
       return count2;
   }
   
}
