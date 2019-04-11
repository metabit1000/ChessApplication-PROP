package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Tablero;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Queen extends Ficha{
    public Queen(boolean color,Coordenada posicion, Character c) {
        super(color,posicion, c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Tablero p) {
        Rook t = new Rook(color,posicion, c);
        Bishop a = new Bishop(color,posicion, c);
        ArrayList<Coordenada> res = t.posiblesMovimientos(p);
        res.addAll(a.posiblesMovimientos(p));
        return res;
    }
}
