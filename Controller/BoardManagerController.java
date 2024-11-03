package Controller;

import Model.AnimationPiece;
import Model.Board;
import Model.BoardManager;
import Model.Command.BoardCommand;
import Model.Command.UpdateBoardCommand;
import Model.Observer.IObserver;
import Model.Player;
import View.FrmJuego;
import View.Vista;

public class BoardManagerController {
    private Vista view;
    private BoardManager boardManager;
    private Board board;
    private FrmJuego frmJuego;
    private static BoardManagerController instance;
    private AnimationPiece animation;

    public BoardManagerController(Board board, FrmJuego frmJuego, Vista view) {
        boardManager = BoardManager.getInstance();
        this.view = view;
        this.animation = new AnimationPiece(this);
        this.board = board;
        this.frmJuego = frmJuego;
        if (view instanceof IObserver iObserver) {
            boardManager.addObserver(iObserver);
        }
    }
    
    public static BoardManagerController getInstance(Board board, FrmJuego frmJuego, Vista view) {
        if (instance == null) {
            instance = new BoardManagerController(board, frmJuego, view);
        }
        return instance;
    }
    
    public Player setWinner(){
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
        BoardCommand command = new UpdateBoardCommand(frmJuego, board);
        board = new Board(this);
        executeCommand(command);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    public Player getPlayer1() {
        return board.getPlayer1();
    }

    public Player getPlayer2() {
        return board.getPlayer2();
    }

    public Player getPlayerCurrent() {
        return board.getCurrentPlayer();
    }

    public int countTokensPlayer() {
        return boardManager.countPlayer1Pieces();
    }

    public int countTokensPlayer2() {
        return boardManager.countPlayer2Pieces();
    }
    
    public void executeCommand(BoardCommand command){
        command.execute();
    } 
}
