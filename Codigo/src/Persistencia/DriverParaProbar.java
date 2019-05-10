package Persistencia;

import Dominio.*;

/**
 *
 * @author Àlex
 */
public class DriverParaProbar {
    public static void main(String[]args){
        CtrlDatosProblemas cj = new CtrlDatosProblemas();
        Problema r = new Problema();
        /*r.setElemento("pablito", 10.0);
        r.setElemento("alex", 20.0);
        r.setElemento("joan", 30.0);
        r.setElemento("jordi", 80.0);
        cj.introducirProblema(1,"3K4/8/8/p2k4/pp1B4/N5N1/P2Q4/8 w",3,r); */
        r = cj.obtenerProblema(1);
        System.out.println(r.getId()+ " "+ r.getFen()+ " "+ r.getNumMovimientos());
        Ranking rank = r.getRanking();
        rank.getclasificacion();
    }  
}
