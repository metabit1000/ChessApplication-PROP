package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import Dominio.fichas.Ficha;
import java.util.ArrayList;

/**
 *
 * @author Joan
 */
public class Partida {
    private Problema p = new Problema();
    private Jugador player1;
    private Jugador player2;
    
    /*Para las MaquinaVSMaquina */
    private int puntuacionMaq1; 
    private int puntuacionMaq2; //num partidas ganadas
    private int numMov;
    
    public Partida() {}
    
    public Partida(Usuario j1,Usuario j2,Problema p) {
        //creadora de partida para dos jugadores usuarios
        player1 = j1;
        player2 = j2;
        this.p = p;
    }
    
    public Partida(Usuario j1,Maquina m,Problema  p) {
        //creadora de partida para dos jugadores: un usuario y una maquina
        player1 = j1;
        player2 = m ; 
        this.p = p;
    }
    
    public Partida(Maquina m1, Maquina m2, Problema p) {
        //creadora de partida para dos jugadores maquina
        player1 = m1;
        player2 = m2;
        this.p = p;
        
        puntuacionMaq1 = 0;
        puntuacionMaq2 = 0;
        numMov = 0;
    }
    
     /**
     * pre:dado una partida queremos saber cual es el turno incial
     * post:devuelve quien es el turno incial , si es false -> negro si es true->blanco
     * @return turno
     */
    public int getPuntuacionM1(){
        return puntuacionMaq1;
    }
    
    public int getPuntuacionM2(){
        return puntuacionMaq2;
    }
    
    public boolean getTurnoInicial() {
        return p.getTurno(); //turno inicial del problema
    }
    
    /**
    * pre:dado un problema queremos saber cuantos movimientos debemos hacer para superar el problema
    * post:devolvera el numero de movimientos
    * @return p.getNumMovimientos
    */
    public int getNumMovimientos() {
        return p.getNumMovimientos();  //num movimientos del problema
    }
    
    /**
     * pre:Dado una partida queremos saber todos los posibles movimientos en una Coordenada determinada
     * post: devuelve una arraylist con los movimientos posibles
     * @param c
     * @return res
     */
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        ArrayList<Coordenada> res = p.getFicha(c).posiblesMovimientos(p, c);
        return res;
    }
    
    /**
     * pre:-
     * post:devuelve el problema que esta jugando en la partida
     * @return p
     */
    public Problema getProblema() {
        return p;
    }
    
    public int getNumM() {
        return numMov;
    }
    
    /**
    * pre:Dado un problema p existente
    * post:El atributo privado p sera el mismo que pasamos por parámetros
    * @return p
    */
    public void setProblema(Problema p) {
        this.p = p;
    }
    
    /**
     * pre:-
     * post:Devolveremos el nombre del jugador1 
     * @return 
     */
    public String getNombreJugador1() {
        Usuario aux = new Usuario();
        aux = (Usuario)player1; //lo hago asi para que maquina no tenga nombre
        return aux.getNombre();
    }
    
     /**
     * pre:-
     * post:Devolveremos el nombre del jugador2
     * @return 
     */
    public String getNombreJugador2() {
        Usuario aux = new Usuario();
        aux = (Usuario)player2; //lo hago asi para que maquina no tenga nombre
        return aux.getNombre();
    }
    
    /**
    * pre:Boolean turno diferente null
    * post:Devolveremos si hay mate en ese color
    * @param turno
    * @return 
    */
    public boolean checkMate(boolean turno) {
        return p.checkmate(turno);
    }
    
    /**
    * pre:dado una coordenada valida y diferente a null
    * post:devolveremos el color de la ficha , false-> negra o true->blanca
    * @param c
    * @return 
    */
    public boolean getColor(Coordenada c) {
        return p.getFicha(c).getColor();
    }
    
    /**
    * pre: dado un ranking ya existente queremos insertar el nombre del usuario y su tiempo diferente a null
    * post:un nuevo tiempo y nombre estaran introducidos en el ranking 
    * @param nombre
    * @param tiempo 
    */
    public void actualizarRanking(String nombre,double tiempo) {
        if (p.existeix(nombre)) p.actualizarRanking(nombre, tiempo);
        else p.introducirElemento(nombre,tiempo);
    }
    
    /**
     * pre:Dado una coordenada incial y final diferentes a null y un color diferente a null 
     * post:devolvera 0 si es correcto , -1 si estas en jaque o -2 si no es tu turno 
     * @param color
     * @param cordInicio
     * @param cordFinal
     * @return 
     */
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal){ //color es el turno
        Ficha o = p.getFicha(cordFinal);
        int res = 0; //todo correcto
        if(color == p.getFicha(cordInicio).getColor() && p.moveFicha(cordInicio,cordFinal)){
            if (p.mate(!p.getFicha(cordFinal).getColor())) {
                p.undoFicha(cordFinal,cordInicio,o);
                res = -1; //"Estás en jaque. Vuelve a intentarlo."
            }
        } 
        else res = -2;  //No es tu turno
        return res;
    }
    
    /**
     * pre:-
     * post:Devolvera el mejor movimiento de la máquina 
     * @return 
    */
    
    public Pair<Coordenada,Coordenada> moverFichaMaquina() {
        Maquina m = (Maquina)player2;  //para corregir el error de que usuario no tiene getNextMove()
        Pair<Coordenada,Coordenada> res = m.getNextMove(p); 
        p.moveFicha(res.getKey(), res.getValue()); //lo aplico en dominio
        return res; //devuelvo el mejor movimiento para la capa de presentacion
    }
    
    /**
     * pre:Dado un Problema p existen 
     * post:Haremos jugar las maquinas en n problemas
     * @param p 
    */
    public void playNProblemas(Problema p) {
        this.p = p; //actualizo el problema a jugar
        playMaquinas(false); //juegan y actualizan las variables puntuacion
    }
   
    /**
     * pre:Dado un boolean validar el cual es diferente a null , si es false es un problema no existente y true si ya era existente
     * post:En est metodo haremos jugar las maquinas entre ellas , incrementaremos el contador de la máquina que haya ganado
     * @param validar 
     */
    public void playMaquinas(boolean validar) {
        int cont = 0;
        String coordenada1, coordenada2;
        String c;
        boolean win = false;
        boolean turno = p.getTurno();
        boolean pt = p.getTurno();
        
        int compare;
        if (validar) compare = 50;
        else compare = p.getNumMovimientos();
        while (cont < compare && !win) {
            Pair <Coordenada,Coordenada> moves = new Pair();
            if (turno) {
                Maquina m1 = (Maquina)player1;
                moves = m1.getNextMove(p);
                if (turno == pt) {
                    ++cont;
                }  
            }
            else {
                Maquina m2 = (Maquina)player2;
                moves = m2.getNextMove(p);
                if (turno == pt) {
                    ++cont;
                }
            }
            p.moveFicha(moves.getKey(),moves.getValue());
            if (p.checkmate(turno)){ 
                //ganan las blancas
                win = true;
                ++puntuacionMaq1;
            }
            turno = !turno;
        }
        if (!win) {
            //ganan las negras
            ++puntuacionMaq2;
        }
    }
}
