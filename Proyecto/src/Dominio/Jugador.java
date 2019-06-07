package Dominio;

/**
 *
 * @author Jordi
 */
public class Jugador {
    protected Boolean color; //negro = false, blanco = true 
    
    public Jugador() {}    
    
    public Jugador(boolean color) {
        this.color = color;
    }
 
    /**
    * pre:-
    * post:devuleve un booleano el cual nos devuelve false si es negro y si es blanco es true 
    * @return color
    */    
    public boolean getColor(){
        //devuleve el color del jugador
        return color;
    }    
}
