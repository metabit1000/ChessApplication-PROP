package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.*;

/**
 *
 * @author Àlex
 */
public class Pawn extends Ficha{
    
    public Pawn() {}
    
    public Pawn(boolean color,char c) {
        super(color,c);
    }
    
    /**
     * pre:Dado un problema y una coordenada cualquiera dentro del tablero del problema
     * post: Devuelve todos los posibles movimientos que puede hacer una ficha según que tipo sea
    */
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c) {
        ArrayList<Coordenada> res = new ArrayList();
        int x = c.getX();
        int y = c.getY();
        
        if (p.esValid(c) && p.getFicha(c) != null) {
            if (!color) { //negras
                /* MOVERSE */
                c = new Coordenada(x + 1, y);
                if (p.esValid(c) && p.getFicha(c) == null) res.add(c); //avanzar para delante 
                if (x == 1 && p.esValid(c) && p.getFicha(c) == null) {  //avanzar casillas para delante; solo en el inicio es posible!
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
                if (x == 6 && p.esValid(c) && p.getFicha(c) == null) {  //avanzar casillas para delante; solo en el inicio es posible!
                    c = new Coordenada(x - 2 ,y);
                    if (p.esValid(c) && p.getFicha(c) == null) res.add(c);
                }

                /* ATACAR */
                c = new Coordenada(x - 1, y + 1);
                if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
                c = new Coordenada(x - 1, y - 1);
                if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).color != color) res.add(c);
            }
        }
        return res;
    }
}
