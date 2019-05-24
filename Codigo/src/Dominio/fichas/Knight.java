package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Knight extends Ficha{
    
    public Knight() {}
    
    public Knight(boolean color, char c) {
        super(color,c);
    }
    @Override
    /**
     *  * pre:Dado un problema y una coordenada cualquiera dentro del tablero del problema
     * post: Devuelve todos los posibles movimientos que puede hacer una ficha según que tipo sea
     */
    public ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c) {
        ArrayList<Coordenada> res = new ArrayList();
        int x = c.getX();
        int y = c.getY();

        if (p.esValid(c) && p.getFicha(c) != null) {
            /* ABAJO */
            c = new Coordenada(x + 2, y + 1);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
            c = new Coordenada(x + 2, y - 1);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);

            /* DERECHA */
            c = new Coordenada(x + 1, y + 2);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
            c = new Coordenada(x - 1, y + 2);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);

            /* IZQUIERDA */
            c = new Coordenada(x + 1, y - 2);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
            c = new Coordenada(x - 1, y - 2);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);

            /* ARRIBA */
            c = new Coordenada(x - 2, y + 1);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
            c = new Coordenada(x - 2, y - 1);
            if (p.esValid(c) && (p.getFicha(c) == null || 
                    (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        }
        return res;
    }
}
