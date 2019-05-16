package Dominio;

import Persistencia.CtrlDatosProblemas;

/**
 *
 * @author Àlex
 */
public class CtrlPartida {
    CtrlDatosProblemas ctrlP = new CtrlDatosProblemas();
   
    public char[][] getTablero(int id) {
        Problema p = ctrlP.obtenerProblema(id);
        char[][] res = p.convertirTablero();
        return res;
    }
}
