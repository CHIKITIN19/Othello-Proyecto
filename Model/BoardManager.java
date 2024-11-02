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

    public BoardManager() {
        this.board = new Board();
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
    
}
