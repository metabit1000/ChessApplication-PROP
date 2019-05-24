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
    
    /* Constructora para Jug VS Jug y Jug VS Maq */
    public CtrlPartida(int tipoJuego,int dificultad,int id,CtrlPresentacionUsuarios u) { 
        /*dificultad: 0 -> no tiene, 1 -> facil, 2 -> dificil
        tipoJuego: 0 ->  Jugar, ContraMaquina, 1 -> 1vs1 */
        
        Problema p = ctrlP.obtenerProblema(id);
        if (tipoJuego == 0 && dificultad == 0){
            game = new Partida(new Usuario(true,u.getUserLogged()," "),new Usuario(false,u.getGuest()," "),p);  
        }
        else if (tipoJuego == 1 && dificultad == 1) {
            game = new Partida(new Usuario(true,u.getUserLogged()," "),new MaquinaEasy(false,p.getNumMovimientos()),p);
        }
        else if (tipoJuego == 1 && dificultad == 2) {
            game = new Partida(new Usuario(true,u.getUserLogged()," "),new MaquinaHard(false,p.getNumMovimientos()),p);
        }
    }
    
    /* Constructora para Maquina VS Maquina */
    public CtrlPartida(int difBlancas, int difNegras,int problInicial) { 
        Problema p = ctrlP.obtenerProblema(problInicial); //problema inicial
        System.out.println(p.getNumMovimientos());
        if (difBlancas == 1 && difNegras == 1) {
            game = new Partida(new MaquinaEasy(true,p.getNumMovimientos()),new MaquinaEasy(false,p.getNumMovimientos()),p);
        } 
        else if (difBlancas == 1 && difNegras == 2) {
            game = new Partida(new MaquinaEasy(true,p.getNumMovimientos()),new MaquinaHard(false,p.getNumMovimientos()),p);
        }
        
        else if (difBlancas == 2 && difNegras == 1) {
            game = new Partida(new MaquinaHard(true,p.getNumMovimientos()),new MaquinaEasy(false,p.getNumMovimientos()),p);
        }
        
        else if (difBlancas == 2 && difNegras == 2) {
            game = new Partida(new MaquinaHard(true,3),new MaquinaHard(false,3),p);
        }
    }
    /**
     * pre:Dado un vector de int no vacio
     * post:Simularemos las partidas maquina vs maquina de los problemas que encontramos en la variable res
     * @param res 
     */
    public void playNProblemas(int res[]) {
        for (int i = 0; i < res.length; i++) {
            Problema p = ctrlP.obtenerProblema(res[i]+1); //+1 porque en presentacion no lo puedo hacer y es necesario para coger bien el id
            game.playNProblemas(p);
        }
    }
   /**
    * pre:-
    * post:Devuelve la puntuación de la máquina 1
    * @return 
    */
    
    public int getPuntuacionM1() {
        return game.getPuntuacionM1();
    }
    
    /**
     * pre:-
     * post:Devuelve la puntuación de la máquina 2
     * @return 
     */
    
    public int getPuntuacionM2() {
        return game.getPuntuacionM2();
    }
    
    /**
     * pre:-
     * post: Devuelve el tablero del problema de tipo char
     * @return 
     */
    public char[][] getTablero() {
        Problema p = game.getProblema();
        char[][] res = p.convertirTablero();
        return res;
    }
   /**
    * pre: Dado una Coordena c valida
    * post:Retorna el color de la coordena c 
    * @param c
    * @return 
    */
    public boolean getColor(Coordenada c) {
        return game.getColor(c);
    }
   /**
    * pre:-
    * post:Devuelve el turno inicial , es decir , que color sera el que empieze la partida
    * @return 
    */
    public boolean getTurnoInicial() {
        return game.getTurnoInicial();
    }
    /**
     * pre:-
     * post:Devuelve el numero de movimientos en los cuales se puede superar el problema
     * @return 
     */
    public int getNumMovimientos() {
        return game.getNumMovimientos();
    }
     /**
     * pre:-
     * post:Devuelve el nombre del Jugador 1 
     * @return 
     */
    public String getNombreJugador1() {
        return game.getNombreJugador1();
    }
    /**
     * pre:-
     * post:Devuelve el nombre del Jugador 3
     * @return 
     */
    public String getNombreJugador2() {
        return game.getNombreJugador2();
    }
    /**
     * pre:Dada una coordenada valida
     * post: Devuelve el arraylist con todas las coordenas posibles en las cuales la ficha que esta en la coordenada c 
     * @param c
     * @return 
     */
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        return game.posiblesMovimientos(c);
    }
    /**
     * pre:Dado un color diferente a null y dos coordenadas validas
     * post:devuelve un int en el cual si es 0 el movimiento es correcto , si es -1 estas en jaque y si es -2 no es tu turno
     * @param color
     * @param cordInicio
     * @param cordFinal
     * @return 
     */
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal) {
        return game.moverFicha(color, cordInicio, cordFinal);
    }
     /**
     * pre:-
     * post:La maquina mueve su ficha 
     * @return 
     */
    public Pair<Coordenada,Coordenada> moverFichaMaquina() {
        return game.moverFichaMaquina();
    }   
    /**
     * pre:Dado un boolean turno diferente a null 
     * post:Devuelve si hace mate 
     * @param turno
     * @return 
     */
    public boolean checkMate(boolean turno) {
        return game.checkMate(turno);
    }
    /**
     * pre:Dado un nombre y un tiempo difrente a null
     * post:Se añade el nombre y el tiempo en el ranking de el problema 
     * @param nombre
     * @param tiempo 
     */
    public void actualizarRanking(String nombre,double tiempo) {
        game.actualizarRanking(nombre, tiempo);
    }
    /**
     * pre: Dada una id de un problema existente
     * post:Modifica el problema poniendolo como estaba en un inicio
     * @param id 
     */
    public void resetTablero(int id) {
        Problema aux = ctrlP.obtenerProblema(id);
        game.setProblema(aux); //reseteo el problema
    }
}
