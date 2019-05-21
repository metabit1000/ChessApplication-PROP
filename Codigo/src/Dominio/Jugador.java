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
    
    public boolean getColor(){
        //devuleve el color del jugador
        return color;
    }
    
    public String getNombre() {
        //devuelve el nombre del usuario
        return nombre;
    }
    
}
