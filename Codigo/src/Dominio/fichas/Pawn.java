package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Tablero;
import java.util.*;

/**
 *
 * @author Àlex
 */
public class Pawn extends Ficha{
    
    public Pawn() {}
    
    public Pawn(boolean color,Coordenada posicion, Character c) {
        super(color,posicion, c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Tablero p) {
        ArrayList<Coordenada> res = new ArrayList();
        Coordenada c;
        int x = posicion.getX();
        int y = posicion.getY();
        
        if (!color) { //negras
            /* MOVERSE */
            c = new Coordenada(x + 1, y);
            if (p.esValid(c) && p.getFicha(c) == null) res.add(c); //avanzar para delante 
            if (x == 1 && p.esValid(c)) {  //avanzar casillas para delante; solo en el inicio es posible!
                c = new Coordenada(x + 2 ,y);
                if (p.esValid(c) && p.getFicha(c) == null) res.add(c);
            }
            
            /* ATACAR */
            c = new Coordenada(x + 1, y + 1);
            if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
            c = new Coordenada(x + 1, y - 1);
            if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
        }
        else { //blancas
            /* MOVERSE */
            c = new Coordenada(x - 1, y);
            if (p.esValid(c) && p.getFicha(c) == null) res.add(c); //avanzar para delante 
            if (x == 6 && p.esValid(c)) {  //avanzar casillas para delante; solo en el inicio es posible!
                c = new Coordenada(x - 2 ,y);
                if (p.esValid(c) && p.getFicha(c) == null) res.add(c);
            }
            
            /* ATACAR */
            c = new Coordenada(x - 1, y + 1);
            if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
            c = new Coordenada(x - 1, y - 1);
            if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
        }
        return res;
    }
}
