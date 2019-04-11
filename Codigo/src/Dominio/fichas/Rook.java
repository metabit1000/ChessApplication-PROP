package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Tablero;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Rook extends Ficha {
    public Rook(boolean color,Coordenada posicion, char c) {
        super(color,posicion, c);
    }
     @Override
    public ArrayList<Coordenada> posiblesMovimientos(Tablero p) {
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
