package Presentacion;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import Dominio.CtrlPartida;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class CtrlPresentacionJugar {
    private CtrlPartida ctrlP = new CtrlPartida(); //prueba
    
    public CtrlPresentacionJugar() {}
    
    public CtrlPresentacionJugar(int id, CtrlPresentacionUsuarios u, String tipo, int dificultad) { 
        //aqui inicializo el id, tipo y tal
        if (tipo.equals("PVS")) ctrlP = new CtrlPartida(0,dificultad,id,u);
        else if (tipo.equals("PJ")) ctrlP = new CtrlPartida(1,dificultad,id,u);
    }
    
    public char[][] getTablero() {
        char[][] res = ctrlP.getTablero();
        return res;
    }
    
    public boolean getColor(Coordenada c) {
        return ctrlP.getColor(c);
    }
    
    public int getNumMovimientos() {
        return ctrlP.getNumMovimientos();
    }
    
    public String getNombreJugador1() {
        return ctrlP.getNombreJugador1();
    }
    
    public String getNombreJugador2() {
        return ctrlP.getNombreJugador2();
    }
    
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        return ctrlP.posiblesMovimientos(c);
    }
    
    public boolean getTurnoInicial() {
        return ctrlP.getTurnoInicial();
    }
    
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal) {
        return ctrlP.moverFicha(color, cordInicio, cordFinal);
    }
    
    public Pair<Coordenada,Coordenada> moverFichaMaquina() {
        return ctrlP.moverFichaMaquina();
    }   
    
    public boolean checkMate(boolean turno) {
        return ctrlP.checkMate(turno);
    }
    
    public void actualizarRanking(String nombre,double tiempo) {
        ctrlP.actualizarRanking(nombre, tiempo);
    }
    
    public void resetTablero(int id) {
        ctrlP.resetTablero(id);
    }
}
