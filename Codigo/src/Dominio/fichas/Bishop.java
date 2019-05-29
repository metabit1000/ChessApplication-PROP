package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Bishop extends Ficha{
    public Bishop() {}
    
    public Bishop(boolean color,char c) {
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
        if (p.esValid(c) && p.getFicha(c) != null) {
            /* ARRIBA/IZQUIERDA (pensando en las blancas) */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x - i,y - i);
                    if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                            for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x - z,y - z);
                                if (p.getFicha(prueba) != null) b = false;
                            }
                            if (b) res.add(c);
                        }
                    }
            }
            /* ABAJO/IZQUIERDA */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x + i,y - i);
                    if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                            for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x + z,y - z);
                                if (p.getFicha(prueba) != null) b = false;
                            }
                            if (b) res.add(c);
                        }
                    }
            }
            /* ABAJO/DERECHA */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x + i,y + i);
                if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                            for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x + z,y + z);
                                if (p.getFicha(prueba) != null) b = false;
                            }
                            if (b) res.add(c);
                        }
                }
            }
            /* ARRIBA/DERECHA */
            for (int i = 1; i <= 7; ++i) {
                b = true;
                c = new Coordenada(x - i,y + i);
                if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color)) {
                            for (int z = 1; z < i; ++z){
                                Coordenada prueba = new Coordenada(x - z,y + z);
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
