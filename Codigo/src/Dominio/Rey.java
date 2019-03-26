package Dominio;

import ClasesExtra.Coordenada;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Rey extends Ficha{
    public Rey(boolean color,Coordenada posicion) {
        super(color,posicion);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) {
        ArrayList<Coordenada> res = new ArrayList();
        Coordenada c;
        int x = posicion.getX();
        int y = posicion.getY();
        for (int i = -1; i <= 1; i++) { 
            for (int j = -1; j <= 1; j++) {
            
            }
        }
        
        
        return res;
    }
}
