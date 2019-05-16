package Dominio;

import Persistencia.CtrlDatosProblemas;

/**
 *
 * @author Àlex
 */
public class CtrlPartida {
    private CtrlDatosProblemas ctrlP = new CtrlDatosProblemas();
    private Partida game = new Partida();
    
    public CtrlPartida() {}
    
    public CtrlPartida(int tipoJuego,int dificultad,int id) {
        /*dificultad: 0 -> no tiene, 1 -> facil, 2 -> dificil
        tipoJuego: 0 ->  Jugar, ContraMaquina, 1 -> 1vs1, 2 -> competicion
        Problema p = ctrlP.getProblema(id);
        if (tipoJuego == 0 && dificultad == 0){
            game = new Partida(Usuario1,Usuario2,p); AUN NADA
        }*/
    }
    
    public char[][] getTablero(int id) {
        Problema p = ctrlP.obtenerProblema(id);
        char[][] res = p.convertirTablero();
        return res;
    }
    
    public void moverFicha() {
        //game.moverFicha(destino,final);
    }
}
