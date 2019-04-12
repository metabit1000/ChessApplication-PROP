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
    protected Character c; //Mayus = blancas, minus = negras(para el fen)
    
    public Ficha() {}
    
    public Ficha(Boolean color,char cfen) {
        this.color = color;
        this.c = cfen;
    }
     
    public Boolean getColor() {
        return color;
    }
    
    public void setColor(Boolean c) {
        color = c;
    }
       
    public Character getCfen() {
        return c;
    }
    
    public abstract ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c);  
}
