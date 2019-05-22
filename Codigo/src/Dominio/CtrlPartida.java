package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import Persistencia.CtrlDatosProblemas;
import Presentacion.CtrlPresentacionUsuarios;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class CtrlPartida {
    private CtrlDatosProblemas ctrlP = new CtrlDatosProblemas();
    private Partida game = new Partida();
    
    public CtrlPartida() {}
    
    public CtrlPartida(int tipoJuego,int dificultad,int id,CtrlPresentacionUsuarios u) { //creo la partida
        /*dificultad: 0 -> no tiene, 1 -> facil, 2 -> dificil
        tipoJuego: 0 ->  Jugar, ContraMaquina, 1 -> 1vs1, 2 -> competicion*/
        
        Problema p = ctrlP.obtenerProblema(id);
        if (tipoJuego == 0 && dificultad == 0){
            game = new Partida(new Usuario(true,u.getUserLogged()," "),new Usuario(false,u.getGuest()," "),p);  
        }
        else if (tipoJuego == 1 && dificultad == 0) {
            game = new Partida(new Usuario(true,u.getUserLogged()," "),new MaquinaEasy(false,3),p);
        }
        else if (tipoJuego == 1 && dificultad == 1) {
            game = new Partida(new Usuario(true,u.getUserLogged()," "),new MaquinaHard(false,3),p);
        }
    }
    
    public char[][] getTablero() {
        Problema p = game.getProblema();
        char[][] res = p.convertirTablero();
        return res;
    }
   
    public boolean getColor(Coordenada c) {
        return game.getColor(c);
    }
    
    public boolean getTurnoInicial() {
        return game.getTurnoInicial();
    }
    
    public int getNumMovimientos() {
        return game.getNumMovimientos();
    }
    
    public String getNombreJugador1() {
        return game.getNombreJugador1();
    }
    
    public String getNombreJugador2() {
        return game.getNombreJugador2();
    }
    
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        return game.posiblesMovimientos(c);
    }
    
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal) {
        return game.moverFicha(color, cordInicio, cordFinal);
    }
    
    public Pair<Coordenada,Coordenada> moverFichaMaquina() {
        return game.moverFichaMaquina();
    }   
    
    public boolean checkMate(boolean turno) {
        return game.checkMate(turno);
    }
    
    public void actualizarRanking(String nombre,double tiempo) {
        game.actualizarRanking(nombre, tiempo);
    }
    
    public void resetTablero(int id) {
        Problema aux = ctrlP.obtenerProblema(id);
        game.setProblema(aux); //reseteo el problema
    }
}
