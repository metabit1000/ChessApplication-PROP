package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Tablero;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Bishop extends Ficha{
    public Bishop(boolean color,Coordenada posicion, char c) {
        super(color,posicion, c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Tablero p) {
        ArrayList<Coordenada> res = new ArrayList();
        int x = posicion.getX();
        int y = posicion.getY();
        Coordenada c;
        
        /* ARRIBA/IZQUIERDA (pensando en las blancas) */
        for (int i = 1; i <= x; ++i) {
            c = new Coordenada(x - i,y - i);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                        res.add(c);
                }
        }
        /* ABAJO/IZQUIERDA */
        for (int i = 1; i <= 7; ++i) {
            c = new Coordenada(x + i,y - i);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                        res.add(c);
                }
        }
        /* ARRIBA/DERECHA */
        for (int i = 1; i <= x; ++i) {
            c = new Coordenada(x + i,y + i);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                        res.add(c);
                }
        }
        /* ABAJO/DERECHA */
        for (int i = 1; i <= 7; ++i) {
            c = new Coordenada(x - i,y + i);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                        res.add(c);
                }
        }
        return res;
    }  
}
