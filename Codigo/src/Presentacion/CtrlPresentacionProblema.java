/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Dominio.Problema;
import Persistencia.CtrlDatosProblemas;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class CtrlPresentacionProblema {
    Problema p = new Problema();
    CtrlDatosProblemas cdp = new CtrlDatosProblemas();
    
    public char[][] convertirTablero() {
        return p.convertirTablero();
    }
    public char[][] obtenerYconvertirTablero(int id) {
        Problema p = cdp.obtenerProblema(id);
        return p.convertirTablero();
    }
    public String dameFEN(char[][] c) {
        p.convertirMatrizFichas(c);
        p.setTurno(true);
        return p.matrixToFen();
    }
}
