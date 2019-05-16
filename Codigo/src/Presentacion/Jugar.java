package Presentacion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class Jugar {
    private CtrlPresentacionJugar ctrlP = new CtrlPresentacionJugar();
    private static int id; //id del problema cargado
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";

    Jugar(int id) {
        this.id = id;
        initializeGui();
    }

    private final void initializeGui() {
        // set up the main GUI
        
        //cargar imagenes
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)|| (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
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
        // fill the black non-pawn piece row
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
    }

    private final JComponent getChessBoard() {
        return chessBoard;
    }

    private final JComponent getGui() {
        return gui;
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
        char[][] c = ctrlP.getTablero(id);
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (c[i][j] == 'r') {
                    chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][2])); 
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                Jugar cb = new Jugar(id); //esto no se si esta bien de cara a coger el id de la anterior vista

                JFrame f = new JFrame("");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}