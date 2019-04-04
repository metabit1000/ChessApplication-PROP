package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Torre extends Ficha {
    public Torre(boolean color,Coordenada posicion) {
        super(color,posicion);
    }
     @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) {
        ArrayList<Coordenada> res = new ArrayList();
        Coordenada c;
        int x = posicion.getX();
        int y = posicion.getY();
        
        /* ARRIBA (pensando en las blancas) */
        for (int i = 1; i <= x; ++i) {
            c = new Coordenada(x - i, y);
            if (p.esValid(c)) {
                if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) 
                    res.add(c);
            } 
        }
        
        /* ABAJO */
        for (int i = 1; i <= 7; ++i) {
            c = new Coordenada(x + i, y);
            if (p.esValid(c)) {
                if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) 
                    res.add(c);
            } 
        }
        
        /* DERECHA */
        for (int i = 1; i <= 7; ++i) {
            c = new Coordenada(x, y + i);
            if (p.esValid(c)) {
                if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) 
                    res.add(c);
            } 
        }
        
        /* IZQUIERDA */
        for (int i = 1; i <= y; ++i) {
            c = new Coordenada(x, y - 1);
            if (p.esValid(c)) {
                if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) 
                    res.add(c);
            } 
        }
        return res;
    }
}
