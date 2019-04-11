package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Tablero;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class King extends Ficha{
    public King(boolean color,Coordenada posicion, Character c) {
        super(color,posicion, c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Tablero p) { //sin tener en cuenta el enroque
        ArrayList<Coordenada> res = new ArrayList();
        Coordenada c;
        int x = posicion.getX();
        int y = posicion.getY();
        for (int i = -1; i <= 1; i++) { 
            for (int j = -1; j <= 1; j++) {
                c = new Coordenada(x + i,y + j);
                if (p.esValid(c) && (p.getFicha(c) == null || (p.getFicha(c) != null 
                    && p.getFicha(c).getColor() != color))) res.add(c);
            }
        }
        return res;
    }
}
