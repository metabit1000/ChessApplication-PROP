package Presentacion;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class VistaPartidaVS extends javax.swing.JFrame {

    private CtrlPresentacionJugar ctrlJ = new CtrlPresentacionJugar();
    private CtrlPresentacionUsuarios usuarios = new CtrlPresentacionUsuarios();
    private int id; //id del problema cargado
    private static JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private Coordenada posicionInicio, posicionFinal;
    private boolean casillaInicioPulsada = false, casillaFinalPulsada = false;
    private int tipo = 0; //0 -> jugador vs jugador, 1 -> jugador vs maquina
    private int movimientosPartida = 0; //movimientos que lleva la partida al jugar
    private boolean turno;
    private char[][] aux;
    
    /* Variables para calcular el tiempo */
    private long tiempoJ1 = 0; 
    private long tiempoJ2 = 0; //se inicializa a 0 al inicio
    private long startTime = 0;
    
    public VistaPartidaVS(int id, CtrlPresentacionUsuarios u, String tipo) {
        this.id = id;
        this.usuarios = u;
        ctrlJ = new CtrlPresentacionJugar(id,u,tipo);
        turno = ctrlJ.getTurnoInicial(); //se inicializa con el turno inicial del problema
        aux = ctrlJ.getTablero(); //guardo el tablero en una variable auxiliar para luego resetearlo
        
        initializeGui();
        
        introducirProblema(); //introduzco el problema a jugar 
        
        
        this.add(gui);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
        this.setMinimumSize(this.getSize());
        this.setVisible(true); 
    }

    private final void initializeGui() {
        // set up the main GUI
        cargarImagenes();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
            
        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.decode("#EFDAB7"));
                } else {
                    b.setBackground(Color.decode("#B38865")); //color background casillas
                }
                ActionListener a = new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {  //DONDE PONGO EL TURNO INICIAL??
                        int res = 0; 
                        
                        /*HUMANO VS HUMANO */
                        if (tipo == 0) { 
                            if (!casillaInicioPulsada && b.getIcon() != null) { //hay pieza para mover y es el primer click
                                posicionInicio = getPosicionBoton(e);
                                casillaInicioPulsada = true;
                                startTime = System.nanoTime(); //empiezo a contar
                            } 
                            
                            else if (casillaInicioPulsada && !casillaFinalPulsada) {
                                posicionFinal = getPosicionBoton(e);
                                if (movimientoPosibleOk()) {
                                    casillaFinalPulsada = true;
                                    res = ctrlJ.moverFicha(turno, posicionInicio, posicionFinal); //en dominio
                                    if (res == -1) {
                                        JOptionPane.showMessageDialog(null, "Estás en jaque. Vuelve a intentarlo.");
                                        casillaFinalPulsada = false;
                                        casillaInicioPulsada = false;
                                    } else if (res == -2) {
                                        JOptionPane.showMessageDialog(null, "No es tu turno. Es el turno de las " + obtenerTurno());
                                    } else {
                                        moverFicha(); //en presentacion
                                        if (turno == ctrlJ.getTurnoInicial()) ++movimientosPartida; //aumento el numero de movimientos 
                                        
                                        if (ctrlJ.checkMate(turno) && movimientosPartida == ctrlJ.getNumMovimientos()) {
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                            
                                            JOptionPane.showMessageDialog(null, "Ganan las " + obtenerTurno()); 
                                            ctrlJ.actualizarRanking(ctrlJ.getNombreJugador1(), (double)tiempoJ1/1000000000); 
                                            
                                            VistaProblemasVS m = new VistaProblemasVS(usuarios); //para seguir teniendo los mismos registrados
                                            setVisible(false);
                                            ctrlJ.resetTablero(id); //reseteo tablero en dominio
                                            turno = ctrlJ.getTurnoInicial(); 
                                            movimientosPartida = 0;
                                            //introducirProblema(); //reseteo el problema para proximas partidas
                                            m.setVisible(true); //vuelvo atras
                                        }
                                        else if (movimientosPartida == ctrlJ.getNumMovimientos()){
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                            
                                            turno = !turno; //gana el contrincante, cambio el turno para sacarlo por pantalla
                                            JOptionPane.showMessageDialog(null, "Ganan las " + obtenerTurno() + " Problema no superado en el número de movimientos del problema"); 
                                            ctrlJ.actualizarRanking(ctrlJ.getNombreJugador2(), (double)tiempoJ2/1000000000);
                                            
                                            VistaProblemasVS m = new VistaProblemasVS(usuarios); //para seguir teniendo los mismos registrados
                                            setVisible(false);
                                            ctrlJ.resetTablero(id); //reseteo tablero en dominio
                                            turno = ctrlJ.getTurnoInicial(); 
                                            movimientosPartida = 0;
                                            introducirProblema(); //reseteo el problema para proximas partidas
                                            m.setVisible(true); //vuelvo atras
                                        }
                                        else {
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                            
                                            turno = !turno; //si es correcto el movimiento y no es final de partida, pasa el turno al siguiente
                                            JOptionPane.showMessageDialog(null, "El turno es de las " + obtenerTurno());  //anuncio el siguiente turno
                                        }
                                    }
                                } else if (ctrlJ.getColor(posicionInicio) != turno && !movimientoPosibleOk()) { //caso especial que no contemplaba
                                    if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                    else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                    
                                    JOptionPane.showMessageDialog(null, "No es tu turno. Es el turno de las " + obtenerTurno());
                                } else {
                                    if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                    else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                    
                                    JOptionPane.showMessageDialog(null, "Ese no es un movimiento correcto, vuelva a intentarlo");
                                }
                                
                                //reseteo para el siguiente movimiento
                                casillaFinalPulsada = false;
                                casillaInicioPulsada = false;
                            }
                        }
                        /*HUMANO VS MAQUINA */
                        else { 
                            if (!casillaInicioPulsada && b.getIcon() != null) { //hay pieza para mover y es el primer click (aqui la maquina NO entra)
                                posicionInicio = getPosicionBoton(e);
                                casillaInicioPulsada = true;
                                startTime = System.nanoTime(); //empiezo a contar para el usuario
                            } 
                            else if (casillaInicioPulsada && !casillaFinalPulsada) { 
                                posicionFinal = getPosicionBoton(e);
                                if (movimientoPosibleOk()) {
                                    casillaFinalPulsada = true;
                                    res = ctrlJ.moverFicha(turno, posicionInicio, posicionFinal); //en dominio
                                    if (res == -1) {
                                        JOptionPane.showMessageDialog(null, "Estás en jaque. Vuelve a intentarlo.");
                                        casillaFinalPulsada = false;
                                        casillaInicioPulsada = false;
                                    } else if (res == -2) {
                                        JOptionPane.showMessageDialog(null, "No es tu turno. Es el turno de las " + obtenerTurno());
                                    } else {
                                        moverFicha(); //en presentacion
                                        if (turno == ctrlJ.getTurnoInicial()) ++movimientosPartida; //aumento el numero de movimientos 
                                        
                                        if (ctrlJ.checkMate(turno) && movimientosPartida == ctrlJ.getNumMovimientos()) {
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            
                                            JOptionPane.showMessageDialog(null, "Ganan las " + obtenerTurno()); 
                                            ctrlJ.actualizarRanking(ctrlJ.getNombreJugador1(), (double)tiempoJ1/1000000000);  //solo actualizo el jugador si gana
                                            
                                            VistaProblemasVS m = new VistaProblemasVS(usuarios); //para seguir teniendo los mismos registrados
                                            setVisible(false);
                                            ctrlJ.resetTablero(id); //reseteo tablero en dominio
                                            turno = ctrlJ.getTurnoInicial(); 
                                            movimientosPartida = 0;
                                            introducirProblema(); //reseteo el problema para proximas partidas
                                            m.setVisible(true); //vuelvo atras
                                        }
                                        else if (movimientosPartida == ctrlJ.getNumMovimientos()){
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            
                                            turno = !turno; //gana el contrincante, cambio el turno para sacarlo por pantalla
                                            JOptionPane.showMessageDialog(null, "Ganan las " + obtenerTurno() + " Problema no superado en el número de movimientos del problema"); 
                                            
                                            VistaProblemasVS m = new VistaProblemasVS(usuarios); //para seguir teniendo los mismos registrados
                                            setVisible(false);
                                            ctrlJ.resetTablero(id); //reseteo tablero en dominio
                                            turno = ctrlJ.getTurnoInicial(); 
                                            movimientosPartida = 0;
                                            introducirProblema(); //reseteo el problema para proximas partidas
                                            m.setVisible(true); //vuelvo atras
                                        }
                                        else {
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            
                                            turno = !turno; //si es correcto el movimiento y no es final de partida, pasa el turno al siguiente
                                            JOptionPane.showMessageDialog(null, "El turno es de las " + obtenerTurno());  //anuncio el siguiente turno
                                            
                                            //PARTE QUE JUEGA LA MAQUINA
                                            
                                            Pair<Coordenada,Coordenada> movMaquina = ctrlJ.moverFichaMaquina(); //cojo el movimento mejor y muevo en dominio
                                            posicionInicio = movMaquina.getKey();
                                            posicionFinal = movMaquina.getValue();
                                            moverFicha(); //muevo en dominio
                                            turno = !turno; 
                                            JOptionPane.showMessageDialog(null, "El turno es de las " + obtenerTurno());
                                        }
                                    }
                                } 
                                else if (ctrlJ.getColor(posicionInicio) != turno && !movimientoPosibleOk()) { //caso especial que no contemplaba
                                    if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                    
                                    JOptionPane.showMessageDialog(null, "No es tu turno. Es el turno de las " + obtenerTurno());
                                } 
                                else {
                                    if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                    
                                    JOptionPane.showMessageDialog(null, "Ese no es un movimiento correcto, vuelva a intentarlo");
                                }

                                casillaFinalPulsada = false;
                                casillaInicioPulsada = false;
                            }    
                        }
                    }
                };

                b.addActionListener(a);
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
        }

        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (8 - ii), SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }
    
    private Coordenada getPosicionBoton(ActionEvent e) {
        int resX = 0, resY = 0;
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                if (e.getSource().equals(chessBoardSquares[jj][ii])) {
                    resX = ii;
                    resY = jj;
                }
            }
        }
        return new Coordenada(resX, resY);
    }
    
    private void moverFicha() {
        chessBoardSquares[posicionFinal.getY()][posicionFinal.getX()].setIcon(chessBoardSquares[posicionInicio.getY()][posicionInicio.getX()].getIcon()); //movimiento
        chessBoardSquares[posicionInicio.getY()][posicionInicio.getX()].setIcon(null); //borrar el anterior
    }

    private boolean movimientoPosibleOk() {
        boolean b = false;
        ArrayList<Coordenada> res = ctrlJ.posiblesMovimientos(posicionInicio);
        for (int i = 0; i < res.size(); ++i) {
            if (res.get(i).equals(posicionFinal)) {
                b = true;
            }
        }
        return b;
    }

    private String obtenerTurno() {
        String turn;
        if (turno) {
            turn = "blancas.";
        } else {
            turn = "negras.";
        }
        return turn;
    }

    private final void cargarImagenes() {
        try {
            BufferedImage bi = ImageIO.read(new File("fichas.png"));
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    chessPieceImages[i][j] = bi.getSubimage(j * 64, i * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    private final void introducirProblema() {
        char[][] c = aux;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                switch (c[i][j]) {
                    case 'K':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][3]));
                        break;
                    case 'k':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][3]));
                        break;
                    case 'Q':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][2]));
                        break;
                    case 'q':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][2]));
                        break;
                    case 'R':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][0]));
                        break;
                    case 'r':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][0]));
                        break;
                    case 'N':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][4]));
                        break;
                    case 'n':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][4]));
                        break;
                    case 'B':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][1]));
                        break;
                    case 'b':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][1]));
                        break;
                    case 'P':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][5]));
                        break;
                    case 'p':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][5]));
                        break;
                    default:
                        chessBoardSquares[j][i].setIcon(null);
                        break;
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        fg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout fgLayout = new javax.swing.GroupLayout(fg);
        fg.setLayout(fgLayout);
        fgLayout.setHorizontalGroup(
            fgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        fgLayout.setVerticalGroup(
            fgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(fg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(fg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Runnable r = new Runnable() {

            @Override
            
            public void run() {
                //VistaPartidaVS cb = new VistaPartidaVS(); 
            }
        };
        SwingUtilities.invokeLater(r);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel fg;
    // End of variables declaration                   
}
