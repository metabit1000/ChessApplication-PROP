package Dominio;
import ClasesExtra.*;

/**
 *
 * @author Jordi
 */
public class Jugador {
    protected Boolean color; //negro = false, blanco = true
    protected String nombre; 
    
    public Jugador() { }    
    
    public Jugador(boolean color) {
        this.color = color;
    }
    
    public Jugador(boolean color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }  
    /**
 * pre:Dado un Jugador con un color queremos obtener su color
 * post:devuleve un booleano el cual nos devuelve false si es negro y si es blanco es true 
 * @return color
 */    
    public boolean getColor(){
        //devuleve el color del jugador
        return color;
    }
    /**
     * pre:Dado un jugador queremos obtener su nombre 
     * post:Devolvemo el nombre del jugador
     * @return nombre
     */
    public String getNombre() {
        //devuelve el nombre del usuario
        return nombre;
    }
    
}
