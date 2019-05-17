package Dominio;

import ClasesExtra.Coordenada;
import Persistencia.CtrlDatosProblemas;
import java.util.ArrayList;

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
        tipoJuego: 0 ->  Jugar, ContraMaquina, 1 -> 1vs1, 2 -> competicion*/
        Problema p = ctrlP.obtenerProblema(id);
        if (tipoJuego == 0 && dificultad == 0){
            game = new Partida(new Usuario(),new Usuario(),p); 
        }
    }
    
    public char[][] getTablero(int id) {
        Problema p = game.getProblema();
        char[][] res = p.convertirTablero();
        return res;
    }
    
    public void moverFicha() {
        //game.moverFicha(destino,final);
    }
    
    public boolean getTurnoInicial() {
        return game.getTurnoInicial();
    }
    
    public int getNumMovimientos() {
        return game.getNumMovimientos();
    }
    
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        return game.posiblesMovimientos(c);
    }
}
