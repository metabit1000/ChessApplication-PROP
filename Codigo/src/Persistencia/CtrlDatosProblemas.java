package Persistencia;

import Dominio.Problema;
import Dominio.Ranking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Àlex
 */
public class CtrlDatosProblemas {
    File archivo;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    String path = "ProblemasGuardados.txt";
    
    public CtrlDatosProblemas(){
        archivo = null;
        fr = null;
        br = null;
        abrirArchivo();
    }
    
    public void abrirArchivo()  {
        archivo = new File(path);
        if (!archivo.exists()) try {
            archivo.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void cerrarArchivo() throws IOException {
        archivo = null;
        if (null != fr) fr.close();
        if (null != fw) fw.close();

    }
    
    public void introducirProblema(String id, String fen, int numM) {
    
    }
    
    public void modificarRanking(Ranking r) {
    
    }
    
    public Ranking obtenerRanking(int id) {
        return new Ranking();
    }
    
    public void escribirRanking(Ranking r) {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No hay ningun archivo abierto.");
        }
        try {
            fw = new FileWriter(archivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.write(r.toString());
            out.close();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean existeProblema(int id) {
        return false;
    }
    
    public Problema seleccionarProblema(int id) {
        return new Problema();
    }
}
