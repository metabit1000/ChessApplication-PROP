package Dominio;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jordi
 */
public class Jugador {
   private Boolean color; //negro = false, blanco = true
    
    public Jugador() { }    
    
    
    public Jugador(boolean color ) {
        this.color = color ;
    }  
    public boolean getcolor(){
        return color;
    }
   
}
