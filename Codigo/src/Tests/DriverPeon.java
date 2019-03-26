package Tests;

import Dominio.Peon;
import Dominio.Problema;
import ClasesExtra.Coordenada;
import java.util.ArrayList;
/**
 *
 * @author Àlex
 */
public class DriverPeon {
    public void testConstructor() {
        Peon p = new Peon(true,new Coordenada(7,4));
    }
    public void testPosiblesMovimientos() {
        Peon p = new Peon(true,new Coordenada(7,4));
        Problema probl = new Problema(p,new Peon(false,new Coordenada(6,5)));
        ArrayList<Coordenada> res = new ArrayList();
        res = p.posiblesMovimientos(probl);
    } 
    public static void main (String [] args) {
        Peon p = new Peon(true,new Coordenada(1,4));
        Peon p2 = new Peon(false,new Coordenada(6,5));
        Problema probl = new Problema(p,p2);
        ArrayList<Coordenada> res = new ArrayList();
        res = p.posiblesMovimientos(probl);
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
    }
}
