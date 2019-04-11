package Dominio.fichas;

import java.util.*;
import ClasesExtra.Coordenada;
import Dominio.Tablero;
/**
 *
 * @author Àlex
 */
public abstract class Ficha {
    protected Coordenada posicion;
    protected Boolean color; //white = true, black = false
    protected Character c; //white = true, black = false
    
    public Ficha() {}
    
    public Ficha(Boolean color,Coordenada posicion, char cfen) {
        this.color = color;
        this.posicion = posicion;
        this.c = cfen;
    }
    
    public Coordenada getPosicion()  {
        return posicion;
    }
    
    public Boolean getColor() {
        return color;
    }
    
    public void setColor(Boolean c) {
        color = c;
    }
    
    
    
    public void setPosicion(Coordenada p) {
        posicion = p;
    }
    
    public abstract ArrayList<Coordenada> posiblesMovimientos(Tablero p);  
}
