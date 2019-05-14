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
        ArrayList<Problema> res = cj.getAllProblemas();
    }  
}
