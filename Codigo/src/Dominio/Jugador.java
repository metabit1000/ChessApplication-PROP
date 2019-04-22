package Dominio;
import ClasesExtra.*;

/**
 *
 * @author Jordi
 */
public abstract class Jugador {
    protected Boolean color; //negro = false, blanco = true
    protected String nombre; 
    
    public Jugador() { }    
    
    
    public Jugador(boolean color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }  
    
    public boolean getcolor(){
        return color;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public abstract Pair getNextMove(Problema p);
}
