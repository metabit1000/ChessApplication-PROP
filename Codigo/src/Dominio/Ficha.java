package Dominio;

import ClasesExtra.Coordenada;
import Dominio.Problema;
/**
 *
 * @author Àlex
 */
public abstract class Ficha {
    private Coordenada posicion;
    private final boolean color; //white = true, black = false
    
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
    
    public void move() {
        
    }
}
