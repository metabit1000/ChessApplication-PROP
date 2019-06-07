package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class King extends Ficha{
    public King() {}
    
    public King(boolean color,char c) {
        super(color,c);
    }
    
    /**
     * pre:Dado un problema y una coordenada cualquiera dentro del tablero del problema
     * post: Devuelve todos los posibles movimientos que puede hacer una ficha según que tipo sea
    */
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c) { //sin tener en cuenta el enroque
        ArrayList<Coordenada> res = new ArrayList();
        int x = c.getX();
        int y = c.getY();
        
        if (p.esValid(c) && p.getFicha(c) != null) {
            for (int i = -1; i <= 1; i++) { 
                for (int j = -1; j <= 1; j++) {
                    c = new Coordenada(x + i,y + j);
                    if (p.esValid(c) && (p.getFicha(c) == null || (p.getFicha(c) != null 
                        && p.getFicha(c).getColor() != color))) res.add(c);
                }
            }
        }
        return res;
    }
}
