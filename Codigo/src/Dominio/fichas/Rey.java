package Dominio.fichas;

import Dominio.fichas.Ficha;
import ClasesExtra.Coordenada;
import Dominio.Problema;
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
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) { //sin tener en cuenta el enroque
        ArrayList<Coordenada> res = new ArrayList();
        Coordenada c;
        int x = posicion.getX();
        int y = posicion.getY();
        for (int i = -1; i <= 1; i++) { 
            for (int j = -1; j <= 1; j++) {
                c = new Coordenada(x + i,y + j);
                if (c.esValid() && (p.getFicha(c) == null || (p.getFicha(c) != null 
                    && p.getFicha(c).getColor() != color))) res.add(c);
            }
        }
        return res;
    }
}
