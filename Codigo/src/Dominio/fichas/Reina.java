package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Reina extends Ficha{
    public Reina(boolean color, Coordenada c) {
        super(color,c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p) {
        Torre t = new Torre(color,posicion);
        Alfil a = new Alfil(color,posicion);
        ArrayList<Coordenada> res = t.posiblesMovimientos(p);
        res.addAll(a.posiblesMovimientos(p));
        return res;
    }
}
