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

public class VistaPartida extends javax.swing.JFrame {

    private CtrlPresentacionJugar ctrlJ = new CtrlPresentacionJugar();
    private CtrlPresentacionUsuarios usuarios = new CtrlPresentacionUsuarios();
    private int id; //id del problema cargado
    private JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private Coordenada posicionInicio, posicionFinal;
    private boolean casillaInicioPulsada = false, casillaFinalPulsada = false;
    private String tipo; //PVS -> jugador vs jugador, PJ -> jugador vs maquina
    private int movimientosPartida = 0; //movimientos que lleva la partida al jugar
    private boolean turno;
    private char[][] aux;
    
    /* Variables para calcular el tiempo */
    private long tiempoJ1 = 0; 
    private long tiempoJ2 = 0; //se inicializa a 0 al inicio
    private long startTime = 0;
    
    public VistaPartida(int id, CtrlPresentacionUsuarios u, String tipo, int dificultad) {
        this.id = id;
        this.usuarios = u;
        ctrlJ = new CtrlPresentacionJugar(id,u,tipo,dificultad);
        this.tipo = tipo;
        turno = ctrlJ.getTurnoInicial(); //se inicializa con el turno inicial del problema
        aux = ctrlJ.getTablero(); //guardo el tablero en una variable auxiliar para luego resetearlo
        startTime = System.nanoTime(); //empiezo a contar
        initializeGui();
        
        introducirProblema(); //introduzco el problema a jugar 
        
        this.add(gui);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
        this.setMinimumSize(this.getSize());
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
                        
                        if (tipo.equals("PVS")) { 
                            if (!casillaInicioPulsada && b.getIcon() != null) { //hay pieza para mover y es el primer click
                                posicionInicio = getPosicionBoton(e);
                                casillaInicioPulsada = true;
                            } 
                            
                            else if (casillaInicioPulsada && !casillaFinalPulsada) {
                                posicionFinal = getPosicionBoton(e);
                                if (movimientoPosibleOk()) {
                                    casillaFinalPulsada = true;
                                    res = ctrlJ.moverFicha(turno, posicionInicio, posicionFinal); //en dominio
                                    if (res == -1) {
                                        JLabel label = new JLabel("Estás en jaque, vuelve a intentarlo");
                                        label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                        JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                        casillaFinalPulsada = false;
                                        casillaInicioPulsada = false;
                                    } else if (res == -2) {
                                        JLabel label = new JLabel("No es tu turno. Es el turno de las " + obtenerTurno());
                                        label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                        JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        moverFicha(); //en presentacion
                                        if (turno == ctrlJ.getTurnoInicial()) ++movimientosPartida; //aumento el numero de movimientos 
                                        
                                        if (ctrlJ.checkMate(turno) && movimientosPartida == ctrlJ.getNumMovimientos()) {
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                            
                                            ctrlJ.actualizarRanking(ctrlJ.getNombreJugador1(), (double)tiempoJ1/1000000000); 
                                            JLabel label = new JLabel("Ganan las " + obtenerTurno() + " Con un tiempo de "+(double)tiempoJ1/1000000000+" segundos.");
                                            label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                            JOptionPane.showMessageDialog(null, label, "Tenemos un ganador", JOptionPane.INFORMATION_MESSAGE);
                                            
                                            
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
                                            else tiempoJ2 += (System.nanoTime() - startTime); //jugador2
                                            
                                            turno = !turno; //gana el contrincante, cambio el turno para sacarlo por pantalla
                                            ctrlJ.actualizarRanking(ctrlJ.getNombreJugador2(), (double)tiempoJ2/1000000000);
                                            JLabel label = new JLabel("Problema no superado en el numero de movimientos del problema. Ganan las " + obtenerTurno() + " Con un tiempo de "+(double)tiempoJ2/1000000000+" segundos.");
                                            label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                            JOptionPane.showMessageDialog(null, label, "Tenemos un ganador", JOptionPane.INFORMATION_MESSAGE);
                                            
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
                                            startTime = System.nanoTime(); //empiezo a contar
                                        }
                                    }
                                } else if (ctrlJ.getColor(posicionInicio) != turno && !movimientoPosibleOk()) { //caso especial que no contemplaba 
                                    JLabel label = new JLabel("No es tu turno. Es el turno de las " + obtenerTurno());
                                    label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                    JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                } else { 
                                    JLabel label = new JLabel("Este no es un movimiento correcto");
                                    label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                    JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                }
                                
                                //reseteo para el siguiente movimiento
                                casillaFinalPulsada = false;
                                casillaInicioPulsada = false;
                            }
                        }
                        /*HUMANO VS MAQUINA */
                        else { //tipo == PJ
                            if (!casillaInicioPulsada && b.getIcon() != null) { //hay pieza para mover y es el primer click (aqui la maquina NO entra)
                                posicionInicio = getPosicionBoton(e);
                                casillaInicioPulsada = true;
                            } 
                            else if (casillaInicioPulsada && !casillaFinalPulsada) { 
                                posicionFinal = getPosicionBoton(e);
                                if (movimientoPosibleOk()) {
                                    casillaFinalPulsada = true;
                                    res = ctrlJ.moverFicha(turno, posicionInicio, posicionFinal); //en dominio
                                    if (res == -1) {
                                        JLabel label = new JLabel("Estás en jaque, vuelve a intentarlo");
                                        label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                        JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                        casillaFinalPulsada = false;
                                        casillaInicioPulsada = false;
                                    } else if (res == -2) {
                                        JLabel label = new JLabel("No es tu turno. Es el turno de las " + obtenerTurno());
                                        label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                        JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        moverFicha(); //en presentacion
                                        if (turno == ctrlJ.getTurnoInicial()) ++movimientosPartida; //aumento el numero de movimientos 
                                        
                                        if (ctrlJ.checkMate(turno) && movimientosPartida == ctrlJ.getNumMovimientos()) {
                                            if (turno == ctrlJ.getTurnoInicial()) tiempoJ1 += (System.nanoTime() - startTime); //jugador1
                                            ctrlJ.actualizarRanking(ctrlJ.getNombreJugador1(), (double)tiempoJ1/1000000000);  //solo actualizo el jugador si gana
                                            
                                            JLabel label = new JLabel("Ganan las " + obtenerTurno() + " Con un tiempo de "+(double)tiempoJ1/1000000000+" segundos.");
                                            label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                            JOptionPane.showMessageDialog(null, label, "Tenemos un ganador", JOptionPane.INFORMATION_MESSAGE); 
                                            //ctrlJ.actualizarRanking(ctrlJ.getNombreJugador1(), (double)tiempoJ1/1000000000);  //solo actualizo el jugador si gana
                                            
                                            VistaProblemasJug m = new VistaProblemasJug(usuarios); //para seguir teniendo los mismos registrados
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
                                            JLabel label = new JLabel("Ganan las " + obtenerTurno() + " Problema no superado en el número de movimientos del problema");
                                            label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                            JOptionPane.showMessageDialog(null, label, "Tenemos un ganador", JOptionPane.INFORMATION_MESSAGE);
                                            
                                            VistaProblemasJug m = new VistaProblemasJug(usuarios); //para seguir teniendo los mismos registrados
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
                                            
                                            //PARTE QUE JUEGA LA MAQUINA
                                            
                                            Pair<Coordenada,Coordenada> movMaquina = ctrlJ.moverFichaMaquina(); //cojo el movimento mejor y muevo en dominio
                                            posicionInicio = movMaquina.getKey();
                                            posicionFinal = movMaquina.getValue();
                                            moverFicha(); //muevo en dominio
                                            turno = !turno; 
                                            
                                            startTime = System.nanoTime(); //empiezo a contar
                                        }
                                    }
                                } 
                                else if (ctrlJ.getColor(posicionInicio) != turno && !movimientoPosibleOk()) { //caso especial que no contemplaba
                                    JLabel label = new JLabel("No es tu turno. Es el turno de las " + obtenerTurno());
                                    label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                    JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
                                } 
                                else {
                                    JLabel label = new JLabel("Este no es un movimiento correcto");
                                    label.setFont(new Font("Dialog", Font.PLAIN, 18));
                                    JOptionPane.showMessageDialog(null, label, "Movimiento incorrecto", JOptionPane.WARNING_MESSAGE);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPartida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        Runnable r = new Runnable() {

            @Override
            
            public void run() {
                //VistaPartidaVS cb = new VistaPartidaVS(); 
            }
        };
        SwingUtilities.invokeLater(r);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
