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
    
    /**
     * pre:Dado un problema y una coordenada cualquiera dentro del tablero del problema
     * post: Devuelve todos los posibles movimientos que puede hacer una ficha según que tipo sea
    */
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
