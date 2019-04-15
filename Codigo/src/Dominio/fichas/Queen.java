package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Queen extends Ficha{
    
    public Queen() {}
    
    public Queen(boolean color,char c) {
        super(color,c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada cor) {
        Rook t = new Rook(color,c);
        Bishop a = new Bishop(color,c);
        ArrayList<Coordenada> res = new ArrayList();
        if (p.esValid(cor) && p.getFicha(cor) != null) {
            res = t.posiblesMovimientos(p,cor);
            res.addAll(a.posiblesMovimientos(p,cor));
        }
        return res;
    }
}
