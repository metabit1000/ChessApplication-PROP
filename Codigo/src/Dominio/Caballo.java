package Dominio;

import ClasesExtra.Coordenada;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Caballo extends Ficha{
    
    public Caballo(boolean color,Coordenada posicion) {
        super(color,posicion);
    }
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) {
        ArrayList<Coordenada> res = new ArrayList();
        int x = posicion.getY();
        int y = posicion.getY();
        Coordenada c;
        
        /* ABAJO */
        c = new Coordenada(x + 2, y + 1);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        c = new Coordenada(x + 2, y - 1);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        
        /* DERECHA */
        c = new Coordenada(x + 1, y + 2);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        c = new Coordenada(x - 1, y + 2);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        
        /* IZQUIERDA */
        c = new Coordenada(x + 1, y - 2);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        c = new Coordenada(x - 1, y - 2);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        
        /* ARRIBA */
        c = new Coordenada(x - 2, y + 1);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        c = new Coordenada(x - 2, y - 1);
        if (c.esValid() && (p.getFicha(c) == null || 
                (p.getFicha(c) != null && p.getFicha(c).getColor() != color))) res.add(c);
        
        return res;
    }
}
