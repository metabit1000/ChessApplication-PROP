package Presentacion;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import Persistencia.CtrlDatosProblemas;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class VistaCrearModificarProblema extends javax.swing.JFrame {
    
    private CtrlDatosProblemas ctrlP = new CtrlDatosProblemas();
    private CtrlPresentacionJugar ctrlJ = new CtrlPresentacionJugar();
    private CtrlPresentacionUsuarios usuarios = new CtrlPresentacionUsuarios();
    private int id; //id del problema cargado
    private JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel guiF = new JPanel();
    private JPanel guiDef = new JPanel();
    private JPanel guiFiBo = new JPanel();
    private JPanel guiBo = new JPanel();
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JButton[][] Fichas = new JButton[2][6];
    private JButton [][] swapAux = new JButton [1][1];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private JPanel fichasBoard;
    private JButton Cancel = new JButton();
    private JButton Validar = new JButton();
    private JLabel Pregunta = new JLabel("¿En cuántos movimientos ganarán las blancas?");
    private static final String COLS = "ABCDEFGH";
    private Coordenada posicionInicio, posicionFinal;
    private int casillaInicioPulsada = 0;
    private boolean casillaFinalPulsada = false;
    private char[][] aux;
    private JComboBox Combo = new JComboBox();
    
    private int numMovs;
    
    public void setNumMovs(int j) {
        this.numMovs = j;
    }
    
    public VistaCrearModificarProblema(int id, CtrlPresentacionUsuarios u) {
        this.id = id;
        this.usuarios = u;
        Problema p = ctrlP.obtenerProblema(id);
        this.aux = p.convertirTablero();
        guiFiBo.setLayout(new BoxLayout(guiFiBo, BoxLayout.Y_AXIS));
        guiDef.setLayout(new BoxLayout(guiDef, BoxLayout.X_AXIS));
        Cancel.setText("Cancel");
        Validar.setText("Validar");
        Cancel.setPreferredSize(new Dimension(140, 60));
        Cancel.setFont(new java.awt.Font("Tahoma", 0, 20)); 
        Validar.setPreferredSize(new Dimension(140, 60));
        Validar.setFont(new java.awt.Font("Tahoma", 0, 20)); 
        Validar.setEnabled(true);
        Cancel.setEnabled(true);
        Combo.addItem("1");
        Combo.addItem("2");
        Combo.addItem("3");
        Combo.addItem("4");
        
        Combo.setSize(100, 200);
        
        Combo.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e){  
                
                setNumMovs(Combo.getSelectedIndex()+1);
            }  
        });
        Cancel.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e){  
                VistaMenu m = new VistaMenu(u);
                setVisible(false);
                m.setVisible(true); 
            }  
        });
        Validar.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e){  
                VistaMenu m = new VistaMenu(u);
                setVisible(false);
                m.setVisible(true); 
            }  
        });
        initializeGui();
        introducirProblema(); //introduzco el problema a jugar al tablero
        introducirFichas();
        
        printeameElCHAR(sacarProblema());
        Pregunta.setFont(new java.awt.Font("Tahoma", 0, 20)); 
        guiBo.add(Validar);
        guiBo.add(Cancel);
        guiFiBo.add(guiF);
        guiFiBo.add(Pregunta);
        guiFiBo.add(Combo);
        guiFiBo.add(guiBo);
        guiDef.add(gui);
        guiDef.add(guiFiBo);
        this.add(guiDef);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationByPlatform(true);
        this.pack();
        this.setMinimumSize(this.getSize());
        this.setVisible(true); 
    }

    private final void initializeGui() {
        // set up the main GUI
        
        //cargarImagenes();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
            
        fichasBoard = new JPanel(new GridLayout(0, 6));
        fichasBoard.setBorder(new LineBorder(Color.BLACK));
        guiF.add(fichasBoard);
        
        // create the chess board squares
        Insets buttonMarginF = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < Fichas.length; ii++) {
            for (int jj = 0; jj < Fichas[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMarginF);
                ActionListener a = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { 
                        if (casillaInicioPulsada == 0 && b.getIcon() != null) { //hay pieza para mover y es el primer click
                            if (getPosicionFichas(e) != null) {
                                posicionInicio = getPosicionFichas(e);
                                casillaInicioPulsada = 2;
                            }
                        } 
                        else if (casillaInicioPulsada != 0 && !casillaFinalPulsada) {
                            if (getPosicionFichas(e) != null) {
                                posicionFinal = getPosicionBoton(e);
                                    casillaFinalPulsada = true;
                                    casillaInicioPulsada = 0;
                                
                                
                            }
                            casillaFinalPulsada = false;
                            casillaInicioPulsada = 0;
                        }
                    }
                };
                b.addActionListener(a);
                Fichas[ii][jj] = b;
            }
        }
        
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
                    public void actionPerformed(ActionEvent e) { 
                        if (casillaInicioPulsada == 0 && b.getIcon() != null) { //hay pieza para mover y es el primer click
                            if (getPosicionBoton(e) != null) {
                                posicionInicio = getPosicionBoton(e);
                                casillaInicioPulsada = 1;
                            }
                        } 
                        else if (casillaInicioPulsada != 0 && !casillaFinalPulsada) {
                            posicionFinal = getPosicionBoton(e);
                            casillaFinalPulsada = true;
                            if (casillaInicioPulsada == 1) {
                                moverFicha();
                            }
                            else if (casillaInicioPulsada == 2) {
                                ponerFicha();
                            }
                            casillaFinalPulsada = false;
                            casillaInicioPulsada = 0;
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
            chessBoard.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
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
        
        
        for (int ii = 0; ii < 2; ii++) {
            for (int jj = 0; jj < 6; jj++) {
                fichasBoard.add(Fichas[ii][jj]);
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
    
    private Coordenada getPosicionFichas(ActionEvent e) {
        int resX = 0, resY = 0;
        for (int ii = 0; ii < Fichas.length; ii++) {
            for (int jj = 0; jj < Fichas[ii].length; jj++) {
                if (e.getSource().equals(Fichas[ii][jj])) {
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
    
    private void ponerFicha() {
        chessBoardSquares[posicionFinal.getY()][posicionFinal.getX()].setIcon(Fichas[posicionInicio.getX()][posicionInicio.getY()].getIcon()); //movimiento
    }
    private void swapFicha() {
        Insets buttonMarginS = new Insets(0, 0, 0, 0);
        JButton s = new JButton();
        s.setMargin(buttonMarginS);
        swapAux[0][0] = s;
        swapAux[0][0].setIcon(chessBoardSquares[posicionFinal.getX()][posicionFinal.getY()].getIcon());
        chessBoardSquares[posicionFinal.getY()][posicionFinal.getX()].setIcon(chessBoardSquares[posicionInicio.getX()][posicionInicio.getY()].getIcon()); //movimiento
        chessBoardSquares[posicionInicio.getY()][posicionInicio.getX()].setIcon(swapAux[0][0].getIcon()); //movimiento
    }
    
    //matriz de botones a matriz de chars
    private char[][] sacarProblema() {
        JButton[][] jb = this.chessBoardSquares;
        char[][] r = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoardSquares[j][i].getIcon() != null) {
                    if (chessBoardSquares[j][i].getIcon().toString().equals("K1.png"))  r[i][j] = 'K';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("k.png")) r[i][j] = 'k';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("Q1.png")) r[i][j] = 'Q';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("q.png")) r[i][j] = 'q';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("R1.png")) r[i][j] = 'R';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("r.png")) r[i][j] = 'r';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("N1.png")) r[i][j] = 'N';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("n.png")) r[i][j] = 'n';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("B1.png")) r[i][j] = 'B';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("b.png")) r[i][j] = 'b';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("P1.png")) r[i][j] = 'P';
                    else if (chessBoardSquares[j][i].getIcon().toString().equals("p.png")) r[i][j] = 'p';
                }
                else r[i][j] = '.';
            }
            
        }
        return r;
    }
    
    public void printeameElCHAR(char[][] c) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(c[i][j] +" ");
            }
            System.out.println("");
        }
    }
    
    private final void introducirProblema() {
        char[][] c = aux;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                switch (c[i][j]) {
                    case 'K':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("K1.png"));
                        break;
                    case 'k':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("k.png"));
                        break;
                    case 'Q':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("Q1.png"));
                        break;
                    case 'q':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("q.png"));
                        break;
                    case 'R':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("R1.png"));
                        break;
                    case 'r':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("r.png"));
                        break;
                    case 'N':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("N1.png"));
                        break;
                    case 'n':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("n.png"));
                        break;
                    case 'B':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("B1.png"));
                        break;
                    case 'b':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("b.png"));
                        break;
                    case 'P':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("P1.png"));
                        break;
                    case 'p':
                        chessBoardSquares[j][i].setIcon(new ImageIcon("p.png"));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private final void introducirFichas() {
        Fichas[1][3].setIcon(new ImageIcon("K1.png"));
        Fichas[0][3].setIcon(new ImageIcon("k.png"));
        Fichas[1][2].setIcon(new ImageIcon("Q1.png"));
        Fichas[0][2].setIcon(new ImageIcon("q.png"));
        Fichas[1][0].setIcon(new ImageIcon("R1.png"));
        Fichas[0][0].setIcon(new ImageIcon("r.png"));
        Fichas[1][4].setIcon(new ImageIcon("N1.png"));
        Fichas[0][4].setIcon(new ImageIcon("n.png"));
        Fichas[1][1].setIcon(new ImageIcon("B1.png"));
        Fichas[0][1].setIcon(new ImageIcon("b.png"));
        Fichas[1][5].setIcon(new ImageIcon("P1.png"));
        Fichas[0][5].setIcon(new ImageIcon("p.png"));
        
                
    }
    //            chessPieceImages[1][3].setIcon(K1);
//            chessPieceImages[0][3] = k;
//            chessPieceImages[1][2] = Q1;
//            chessPieceImages[0][2] = q;
//            chessPieceImages[1][0] = R1;
//            chessPieceImages[0][0] = r;
//            chessPieceImages[1][4] = N1;
//            chessPieceImages[0][4] = n;
//            chessPieceImages[1][1] = B1;
//            chessPieceImages[0][1] = b;
//            chessPieceImages[1][5] = P1;
//            chessPieceImages[0][5] = p;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lol = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");

        javax.swing.GroupLayout lolLayout = new javax.swing.GroupLayout(lol);
        lol.setLayout(lolLayout);
        lolLayout.setHorizontalGroup(
            lolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        lolLayout.setVerticalGroup(
            lolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(lol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Runnable r = new Runnable() {

            @Override
            
            public void run() {
                //PartidaVS cb = new VistaPartidaVS(); 
            }
        };
        SwingUtilities.invokeLater(r);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel lol;
    // End of variables declaration//GEN-END:variables
}
