package Dominio;

import Persistencia.CtrlDatosProblemas;
import Persistencia.CtrlDatosUsuarios;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Joan
 */
public class CtrlProblemas {
    private CtrlDatosProblemas problems = new CtrlDatosProblemas();
    
    /* Restricciones de las fichas */
    private static final int numr = 2;
    private static final int numR = 2;
    private static final int numbW = 1;
    private static final int numBW = 1;
    private static final int numbB = 1;
    private static final int numBB = 1;
    private static final int numn = 2;
    private static final int numN = 2;
    private static final int nump = 8;
    private static final int numP = 8;
    private static final int numk = 1;
    private static final int numK = 1;
    private static final int numq = 1;
    private static final int numQ = 1;
    
    public CtrlProblemas() {}
    
    /**
     * pre:Dado una id de un problema
     * post:Devuelve el map que contien el ranking de este problema
     * @param id
     * @return 
     */
    public Map<String,Double> getmap(int id ){
        Ranking a = new Ranking();
        a = problems.obtenerRanking(id);
        return a.getmap();
    }
    
    public void introducirProblemanuevo(int numMovs, String Fen){
        Ranking r = new Ranking();
        problems.introducirProblema((getAllProblemasJuegoSize()+1), Fen, numMovs,r);
    }
    
    public void modificarProblema(int numMovs,String fen,String nom ,int id ){
        CtrlDatosUsuarios ctrlU = new CtrlDatosUsuarios();
        Ranking r = new Ranking();
        problems.modificarProblema((ctrlU.getProblemasCreados(nom).get(id)),fen, numMovs, r);
    }
       
    public void introducirProblemaCreado(String nom){
        CtrlDatosUsuarios ctrlU = new CtrlDatosUsuarios();
        ctrlU.introducirProblemaCreado(nom,getAllProblemasJuegoSize());
    }
    
    public int getAllProblemasJuegoSize(){
        ArrayList<Problema> res = getAllProblemasJuego();
        return res.size();
    }
    
    public boolean Validar(int id, String fen, int numMov) {
        Ranking r = new Ranking();
        Problema c = new Problema(id,fen,numMov,r);	
        return c.validarProblema();
    }
    
    public char[][] convertirTablero() {
        Problema p = new Problema();	        
        return p.convertirTablero();    
    }
    
    public char[][] obtenerYconvertirTablero(int id) {
        Problema p = problems.obtenerProblema(id);	
        return p.convertirTablero();
    }
    
    public String dameFEN(char[][] c) {
        Problema p = new Problema();	        
        p.convertirMatrizFichas(c);	        
        p.setTurno(true);	
        return p.matrixToFen();
    }
    
    /**
     * pre:Dado una id de un  problema
     * post:Retornaremos que si existe esa id en algun problema
     * @param id
     * @return 
     */
    public boolean existProblem(int id) {
        //retorna true si el problema con id id existe en el mapa de Problemas
        return problems.existeProblema(id);
    }
    
    /**
     * pre:-
     * post:Devuelve un arraylist con todos los problemas
     * @return 
     */
    public ArrayList<Problema> getAllProblemasJuego() {
        ArrayList<Problema> res = new ArrayList();
        res = problems.getAllProblemas();
        return res;
    }
    
    /**
     * pre:Dado Problema crear diferente a null
     * post:Devuelve true si cumple las condiciones necesarias para crear un problema sino false
     * @param crear
     * @return 
     */
    public int cumpleRestriccionFichas(char[][] crear) {
        //devuelve falso si viola algunas de las restricciones basicas de un tablero de ajedrez: es decir, retornará falso en el caso de que
        //existan mas de 2 alfiles, caballos, torres, mas de 8 peones, mas de 1 reina y no haya un rey. Estas restricciones se miran por color.
        int r = 0; 
        int n = 0; 
        int k = 0;
        int p = 0;
        int q = 0;
        int bW = 0;
        int bB = 0;
        int R = 0;
        int N = 0;
        int K = 0;
        int P = 0;
        int Q = 0;
        int BW = 0;
        int BB = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (crear[i][j] != '.') {
                    switch(crear[i][j]) {
                        case 'r':
                            ++r;
                            if (r > numr) {
                                return 1;
                            }
                            break;
                        case 'R':
                            ++R;
                            if (R > numR) {
                                return 2;
                            }
                            break;
                        case 'q':
                            ++q;
                            if (q > numq) {
                                return 3;
                            }
                            break;
                        case 'Q':
                            ++Q;
                            if (Q > numQ) {
                                return 4;
                            }
                            break;
                        case 'b':
                            if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                                ++bW;
                                if (bW > numbW) {
                                    return 5;
                                }
                            } else {
                                ++bB;
                                if (bB > numbB) {
                                    return 6;
                                }
                            }
                            break;
                        case 'B':
                            if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                                ++BW;
                                if (BW > numBW) {
                                    return 7;
                                }
                            } else {
                                ++BB;
                                if (BB > numBB) {
                                    return 8;
                                }
                            }
                            break;
                        case 'n':
                            ++n;
                            if (n > numn) {
                                return 9;
                            }
                            break;
                        case 'N':
                            ++N;
                            if (N > numN) {
                                return 10;
                            }
                            break;
                        case 'p':
                            ++p;
                            if (p > nump) {
                                return 11;
                            }
                            if (i == 0) {
                                return 12;
                            }
                            break;
                        case 'P':
                            ++P;
                            if (P > numP) {
                                return 13;
                            }
                            if (i == 7) {
                                return 14;
                            }
                            break;
                        case 'k':
                            ++k;
                            break;
                        case 'K':
                            ++K;
                            break;
                    }
                    
                }
            }
        }
        if (K != numK) {
            return 15;
        }
        if (k != numk) {
            return 16;
        }
        Problema JaqueMate = new Problema();
        JaqueMate.convertirMatrizFichas(crear);
        if (JaqueMate.checkmate(true)) return 18;
        if (JaqueMate.checkmate(false)) return 19;
        if (JaqueMate.mate(true)) return 17;
        return 0;
    }
    
    /**
     * pre:Dado un int id diferente a null 
     * post:Devuelve el Problema que corresponde a la id si existe esta sino devulve un problema vacio
     * @param id
     * @return 
     */
    public Problema getProblema(int id) {
        Problema res = new Problema();
        if (existProblem(id)) {
            res = problems.obtenerProblema(id);
        } 
        return res;
    }
    
    /**
     * pre:Dado un int id y un Ranking newR diferente a null
     * post:Actualizara el ranking del problema
     * @param id
     * @param newR 
     */
    public void actualizarRanking(int id, Ranking newR) {
        if (existProblem(id)) {
            problems.modificarRanking(id, newR);
        }
    }

    public int getnumMov(int i) {
        ArrayList<Problema>l = getAllProblemasJuego();
        return l.get(i).getNumMovimientos();
    }

    public int getId(int i) {
        ArrayList<Problema>l = getAllProblemasJuego();
        return l.get(i).getId();
    }
    
    public int getIdCreado(int i,String nom ) {
        CtrlDatosUsuarios ctrlU = new CtrlDatosUsuarios();
        return ctrlU.getProblemasCreados(nom).get(i-1);
    }
}
