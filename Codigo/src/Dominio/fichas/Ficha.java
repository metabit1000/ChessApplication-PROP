package Dominio.fichas;

import java.util.*;
import ClasesExtra.Coordenada;
import Dominio.Problema;
/**
 *
 * @author Àlex
 */
public abstract class Ficha {
    protected Boolean color; //white = true, black = false
    protected Character c; //se usa para pintar la matriz por pantalla
    
    public Ficha() {}
    
    public Ficha(Boolean color, char cfen) {
        this.color = color;
        this.c = cfen;
    }
     
    public Boolean getColor() {
        return color;
    }
    
    public void setColor(Boolean c) {
        color = c;
    }
       
    public Character getID() {
        return c;
    }
    
    /* Dado un problema y una coordenada cualquiera dentro del tablero del problema, 
    devuelve todos los posibles movimientos que puede hacer una ficha según que tipo sea*/
    public abstract ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c);  
}
