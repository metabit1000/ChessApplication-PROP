package Persistencia;

import Dominio.*;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class DriverParaProbar {
    public static void main(String[]args){
        CtrlDatosProblemas  cj= new CtrlDatosProblemas();
        Ranking res = new Ranking();
        res.setElemento("joan", 30.0);
        res.setElemento("alex", 10.0);
        cj.modificarRanking(2,res);
    }  
}
