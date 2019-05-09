package Persistencia;

import Dominio.Problema;
import Dominio.Ranking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

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
    
    public void introducirProblema(int id, String fen, int numM, Ranking r) {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No hay ningun archivo abierto.");
        }
         try {
            fw = new FileWriter(archivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            if (archivo.length() == 0) {
                out.write(id+" "+ fen+" "+numM); 
                bw.newLine(); //salto de linea en fichero                
                ArrayList<String> s = r.toArrayDeStrings();
                for (int i = 0; i < s.size(); ++i) {
                    out.write(s.get(i));
                    if (i != s.size()-1) bw.newLine();
                }
            } 
            else {
                bw.newLine(); //salto de linea en fichero
                out.write(id+" "+ fen+" "+numM); 
                bw.newLine(); 
                ArrayList<String> s = r.toArrayDeStrings();
                for (int i = 0; i < s.size(); ++i) {
                    out.write(s.get(i));
                    if (i != s.size()-1) bw.newLine();
                }
            }
            out.close();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error en introducirProblema");
        }
    }
    
    public void modificarRanking(Ranking r) {
    
    }
    
    public Ranking obtenerRanking(int id) {
        return new Ranking();
    }
    
    public boolean existeProblema(int id) {
        return false;
    }
    
    public Problema seleccionarProblema(int id) {
        return new Problema();
    }
}
