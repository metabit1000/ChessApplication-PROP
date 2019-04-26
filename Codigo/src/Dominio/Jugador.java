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
    
    public boolean getColor(){
        //devuleve el color del jugador
        return color;
    }
    
    public String getNombre() {
        //devuelve el nombre del usuario
        return nombre;
    }
    
    public abstract Pair getNextMove(Problema p);
        // con esta función obtendremos donde se va a mover nuestro jugador , estas coordenadas seran validas i de esta manera el movimiento ser correcto
}
