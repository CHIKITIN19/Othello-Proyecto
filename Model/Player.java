/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 9567
 */
public class Player {
    private int piece;
    private String colors;
    private String name;
    
    public Player(String colors, String name){
        this.name= name;
        this.colors=colors;
    }

    public String getName() {
        return name;
    }

    public String getColors() {
        return colors;
    }
    
    public void setName(String name){
        if(name != null && !name.isEmpty()){
            this.name= name;
        }
    }
}
