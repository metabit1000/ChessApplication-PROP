package Persistencia;

import Dominio.Ranking;

/**
 *
 * @author Àlex
 */
public class DriverParaProbar {
    public static void main(String[]args){
        CtrlDatosProblemas cj = new CtrlDatosProblemas();
        Ranking r = new Ranking();
        r.setElemento("pablito", 10.0);
        r.setElemento("alex", 20.0);
        r.setElemento("joan", 30.0);
        r.setElemento("jordi", 80.0);
        cj.escribirRanking(r);
    }  
}
