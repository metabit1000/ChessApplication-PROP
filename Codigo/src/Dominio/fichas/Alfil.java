package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Alfil extends Ficha{
    public Alfil(boolean color,Coordenada posicion) {
        super(color,posicion);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) {
        ArrayList<Coordenada> res = new ArrayList();
        int x = posicion.getX();
        int y = posicion.getY();
        Coordenada c;
        
        /* ARRIBA/IZQUIERDA (pensando en las blancas) */
            //seguir aqui
        /* ABAJO/DERECHA */
        
        /* ARRIBA/DERECHA */
        
        /* ABAJO/IZQUIERDA */
        return res;
    }  
}
