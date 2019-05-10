package Persistencia;

import Dominio.*;

/**
 *
 * @author Àlex
 */
public class DriverParaProbar {
    public static void main(String[]args){
        CtrlDatosProblemas cj = new CtrlDatosProblemas();
        Ranking r2 = new Ranking();
        r2.setElemento("jose", 40.0);
        r2.setElemento("belen", 80.0);
        r2.setElemento("carlos", 10.0);
        r2.setElemento("antonio", 100.0);
        r2.setElemento("sara", 5.0);
        cj.modificarRanking(2,r2);
    }  
}
