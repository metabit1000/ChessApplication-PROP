package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Rook extends Ficha {
    
    public Rook() {}
    
    public Rook(boolean color,char c) {
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
        Boolean b;
        
        if (p.esValid(c) && p.getFicha(c) != null) { //para comprobar que es una ficha "buena"
        /* ARRIBA (pensando en las blancas) */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x - i, y);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                        for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x - z,y);
                                if (p.getFicha(prueba) != null) b = false;
                        }
                        if (b) res.add(c);
                    }
                } 
            }

            /* ABAJO */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x + i, y);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                        for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x + z,y);
                                if (p.getFicha(prueba) != null) b = false;
                        }
                        if (b) res.add(c);
                    }
                } 
            }

            /* DERECHA */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x, y + i);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                        for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x,y + z);
                                if (p.getFicha(prueba) != null) b = false;
                        }
                        if (b) res.add(c);
                    }
                } 
            }

            /* IZQUIERDA */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x, y - i);
                if (p.esValid(c)) {
                    if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                        for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x,y - z);
                                if (p.getFicha(prueba) != null) b = false;
                        }
                        if (b) res.add(c);
                    }
                } 
            }
        }
        return res;
    }
}
