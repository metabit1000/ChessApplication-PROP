package Dominio;

import ClasesExtra.*;

/**
 *
 * @author Àlex
 */
public abstract class Maquina extends Jugador {
    int d;
    
    public Maquina() {}
    
    public Maquina(Boolean color,int profundidad) {
        super(color);
        d = profundidad;
    }
  
    public abstract Pair getNextMove(Problema p);
}
