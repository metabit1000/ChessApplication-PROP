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
    /**
     * pre:-
     * post:Devuelve el color de la ficha , true->Blanco false->Negro
     * @return 
     */
    public Boolean getColor() {
        return color;
    }
    /**
     * pre:Dado el boolean c diferente a null
     * post:canviamos el color de la ficha al valor del boolean c 
     * @param c 
     */
    public void setColor(Boolean c) {
        color = c;
    }
       /**
        * pre:-
        * post:Devuelve la id de la ficha
        * @return 
        */
    public Character getID() {
        return c;
    }
    
   
    /**
     * pre:Dado un problema y una coordenada cualquiera dentro del tablero del problema
     * post: Devuelve todos los posibles movimientos que puede hacer una ficha según que tipo sea
     * @param p
     * @param c
     * @return 
     */
    public abstract ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c);  
}
