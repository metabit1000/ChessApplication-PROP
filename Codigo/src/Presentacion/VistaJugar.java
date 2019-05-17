package Presentacion;

import ClasesExtra.Coordenada;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class VistaJugar {
    private static CtrlPresentacionJugar ctrlJ = new CtrlPresentacionJugar();
    private static int id; //id del problema cargado
    private static JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private Coordenada posicionInicio,posicionFinal;
    private boolean casillaInicioPulsada = false,casillaFinalPulsada = false;

    VistaJugar(int id) {
        this.id = id;
        initializeGui();
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
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                if ((jj % 2 == 1 && ii % 2 == 1)|| (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                ActionListener a = new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //if jugador contra jugador
                        if (ctrlJ.getTurnoInicial()) {
                            if (!casillaInicioPulsada && b.getIcon() != null) { //hay pieza para mover y es el primer click
                                posicionInicio = getPosicionBoton(e);
                                casillaInicioPulsada = true;
                            }
                            else if (casillaInicioPulsada && !casillaFinalPulsada) {
                                posicionFinal = getPosicionBoton(e);
                                if (movimientoPosibleOk()) {
                                    casillaFinalPulsada = true; 
                                    moverFicha(); //en presentacion
                                    //moverFicha(posicionInicio,posicionFinal); //en dominio
                                } 
                                else {
                                    casillaFinalPulsada = false; //para que vuelva a probar
                                    JOptionPane.showMessageDialog(null, "Ese no es un movimiento correcto, vuelva a intentarlo");
                                }  
                            }
                            else if (!casillaInicioPulsada && !casillaFinalPulsada){  //no se donde ponerlo xd...aqui tendria que picar dos veces el usuario
                                casillaInicioPulsada = false;
                                casillaFinalPulsada = false;
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
                        chessBoard.add(new JLabel("" + (8-ii),SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
        introducirProblema(); //introduzco el problema a jugar al tablero
    }
    
    private Coordenada getPosicionBoton(ActionEvent e) {
        int resX = 0,resY = 0;
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                if (e.getSource().equals(chessBoardSquares[jj][ii])) {
                    resX = ii;
                    resY = jj;
                } 
            }        
        } 
        return new Coordenada(resX,resY);
    }
    
    private void moverFicha() {
        chessBoardSquares[posicionFinal.getY()][posicionFinal.getX()].setIcon(chessBoardSquares[posicionInicio.getY()][posicionInicio.getX()].getIcon()); //movimiento
        chessBoardSquares[posicionInicio.getY()][posicionInicio.getX()].setIcon(null); //borrar
    }
    
    private boolean movimientoPosibleOk() {
        boolean b  = false;
        ArrayList<Coordenada> res = ctrlJ.posiblesMovimientos(posicionInicio);
        for (int i = 0; i < res.size(); ++i)
            if (res.get(i).equals(posicionFinal)) b = true;
        return b;
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
        char[][] c = ctrlJ.getTablero(2); //id 2 para probar
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                switch (c[i][j]) {
                    case 'K':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][0]));
                        break;
                    case 'k':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][0]));
                        break;
                    case 'Q':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][1]));
                        break;
                    case 'q':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][1]));
                        break;
                    case 'T':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][2]));
                        break;
                    case 't':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][2]));
                        break;
                    case 'N':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][3]));
                        break;
                    case 'n':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][3]));
                        break;
                    case 'B':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][4]));
                        break;
                    case 'b':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][4]));
                        break;
                    case 'P':
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][5]));
                        break;
                    case 'p': 
                        chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][5]));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                VistaJugar cb = new VistaJugar(id); //esto no se si esta bien de cara a coger el id de la anterior vista
                JFrame f = new JFrame("");
                f.add(gui);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
                String turno;
                if (ctrlJ.getTurnoInicial()) turno = "blancas.";
                else turno = "negras.";
                JOptionPane.showMessageDialog(null, "El turno es de las " + turno);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
