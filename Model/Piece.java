/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 9567
 */
public class Piece {
    private String colors;

    public Piece(String colors) {
        this.colors = colors;
    }

    public String getColors() {
        return colors;
    }
    
    //Metodo para voltear color de las fichas
    public void FlipColors(){
        if(colors.equals("White")){
            colors = "Black";
        }else{
            if(colors.equals("Black")){
                colors = "White";
            }
        }
    }
}
