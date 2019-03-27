package Dominio;

import ClasesExtra.Coordenada;
import java.util.*;

/**
 *
 * @author Àlex
 */
public class Peon extends Ficha{
    
    public Peon(boolean color,Coordenada posicion) {
        super(color,posicion);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) {
        ArrayList<Coordenada> res = new ArrayList();
        Coordenada c;
        int x = posicion.getX();
        int y = posicion.getY();
        
        if (!color) { //negras
            /* MOVERSE */
            c = new Coordenada(x + 1, y);
            if (c.esValid() && p.getFicha(c) == null) res.add(c); //avanzar para delante 
            if (x == 1 && c.esValid()) {  //avanzar casillas para delante; solo en el inicio es posible!
                c = new Coordenada(x + 2 ,y);
                if (c.esValid() && p.getFicha(c) == null) res.add(c);
            }
            
            /* ATACAR */
            c = new Coordenada(x + 1, y + 1);
            if (c.esValid() && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
            c = new Coordenada(x + 1, y - 1);
            if (c.esValid() && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
        }
        else { //blancas
            /* MOVERSE */
            c = new Coordenada(x - 1, y);
            if (c.esValid() && p.getFicha(c) == null) res.add(c); //avanzar para delante 
            if (x == 6 && c.esValid()) {  //avanzar casillas para delante; solo en el inicio es posible!
                c = new Coordenada(x - 2 ,y);
                if (c.esValid() && p.getFicha(c) == null) res.add(c);
            }
            
            /* ATACAR */
            c = new Coordenada(x - 1, y + 1);
            if (c.esValid() && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
            c = new Coordenada(x - 1, y - 1);
            if (c.esValid() && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
        }
        return res;
    }
}
