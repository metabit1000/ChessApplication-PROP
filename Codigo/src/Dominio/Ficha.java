package Dominio;

import java.util.*;
import ClasesExtra.Coordenada;
/**
 *
 * @author Àlex
 */
public abstract class Ficha {
    protected Coordenada posicion;
    protected boolean color; //white = true, black = false
    
    public Ficha(boolean color,Coordenada posicion) {
        this.color = color;
        this.posicion = posicion;
    }
    
    public Coordenada getPosicion()  {
        return posicion;
    }
    
    public boolean getColor() {
        return color;
    }
    
    public void setPosicion(Coordenada p) {
        posicion = p;
    }
    
    public abstract ArrayList<Coordenada> posiblesMovimientos(Problema p);  
}
