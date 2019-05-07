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
    
    /**
     * Función que abre el archivo
     * @throws java.io.IOException
     */
    public void abrirArchivo() throws IOException {
        archivo = new File(path);
        if (!archivo.exists()) archivo.createNewFile();
    }

    /**
     * Función que cierra el archivo
     * @throws java.io.IOException
     */
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
    
    private void escribirRanking(Ranking r) {
        
    }
    
    public boolean existeProblema(int id) {
        return false;
    }
    
    public Problema seleccionarProblema(int id) {
        return new Problema();
    }
}
