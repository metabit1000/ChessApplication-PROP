package Persistencia;

import Dominio.*;

/**
 *
 * @author Àlex
 */
public class DriverParaProbar {
    public static void main(String[]args){
        CtrlDatosProblemas  cj= new CtrlDatosProblemas();
        Ranking res = new Ranking();
        //res.setElemento("jose",1.0);
        cj.introducirProblema(11,"6k1/p1p2rpp/1q6/2p5/4P3/PQ6/1P4PP/3R3K w",1,res);
    }  
}
