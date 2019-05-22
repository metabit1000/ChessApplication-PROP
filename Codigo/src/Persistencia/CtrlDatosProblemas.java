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
                out.write(id+" "+ fen+" "+numM); //escribo fen y otros
                bw.newLine(); //salto de linea en fichero                
                ArrayList<String> s = r.toArrayDeStrings(); 
                for (int i = 0; i < s.size(); ++i) { //escribo ranking
                    out.write(s.get(i));
                    bw.newLine();
                }
                out.write("."); //para saber que finaliza el ranking ahi
            } 
            else {
                bw.newLine(); //salto de linea en fichero
                out.write(id+" "+ fen+" "+numM); //escribo fen y otros
                bw.newLine(); 
                ArrayList<String> s = r.toArrayDeStrings();
                for (int i = 0; i < s.size(); ++i) {  //escribo ranking
                    out.write(s.get(i));
                    bw.newLine(); 
                }
                out.write("."); //para saber que finaliza el ranking ahi
            }
            fw.close();
            out.close();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error en introducirProblema");
        }
    }
    
    public Ranking obtenerRanking(int id) {
        String numId = String.valueOf(id); //para convertir a string el id
        Ranking res = new Ranking();
        boolean b = false;
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] lineaDivididaId = linea.split(" "); 
                    if(lineaDivididaId[0].equals(numId)) {
                        String linea2 = null;
                        while (!b && (linea2 = br.readLine()) != null) {
                            String[] lineaDivididaRank = linea2.split(" "); 
                            if (lineaDivididaRank[0].equals(".")) b = true; 
                            else res.setElemento(lineaDivididaRank[0], Double.parseDouble(lineaDivididaRank[1]));
                        }
                        break; //para que no continue buscando en el fichero
                   }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }
    
    public boolean existeProblema(int id) {
        String numId = String.valueOf(id); //para convertir a string el id
        boolean b = false;
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null) {
                   String[] lineaDividida = linea.split(" "); //para dividir la linea
                   if(lineaDividida[0].equals(numId)) b = true; //obtengo el primer valor que es el ID
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return b;
    }
    
    public Problema obtenerProblema(int id) {
        String numId = String.valueOf(id); //para convertir a string el id
        Problema res = new Problema();
        String fen;
        int numMovimientos;
        Ranking aux = new Ranking();
        boolean b = false;
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] lineaDivididaId = linea.split(" "); 
                    if(lineaDivididaId[0].equals(numId)) {
                        fen = lineaDivididaId[1]+" "+ lineaDivididaId[2];
                        numMovimientos = Integer.parseInt(lineaDivididaId[3]);
                        String linea2 = null;
                        while (!b && (linea2 = br.readLine()) != null) {
                            String[] lineaDivididaRank = linea2.split(" "); 
                            if (lineaDivididaRank[0].equals(".")) b = true; 
                            else aux.setElemento(lineaDivididaRank[0], Double.parseDouble(lineaDivididaRank[1]));
                        }
                        res = new Problema(id,fen,numMovimientos,aux);
                        break; //para que no continue buscando en el fichero
                   }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }
    
    public ArrayList<Problema> getAllProblemas() {
        ArrayList<Problema> res = new ArrayList();
        Problema probl = new Problema();
        String fen;
        int numMovimientos;
        int id;
        Ranking aux = new Ranking();
        boolean b = false;
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] lineaDivididaId = linea.split(" ");
                    id = Integer.parseInt(lineaDivididaId[0]);
                    fen = lineaDivididaId[1]+" "+ lineaDivididaId[2];
                    numMovimientos = Integer.parseInt(lineaDivididaId[3]);
                    String linea2 = null;
                    while (!b && (linea = br.readLine()) != null) { //el ranking
                        String[] lineaDivididaRank = linea.split(" "); 
                        if (lineaDivididaRank[0].equals(".")) b = true; 
                        else aux.setElemento(lineaDivididaRank[0], Double.parseDouble(lineaDivididaRank[1])); 
                    }
                    b = false; //para el siguiente problema
                    probl = new Problema(id,fen,numMovimientos,aux);
                    aux = new Ranking();
                    res.add(probl);
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }
    
    public void modificarRanking(int id,Ranking newR) {
        String numId = String.valueOf(id); //para convertir a string el id
        File nuevo = new File("random.txt"); //fichero auxiliar que cambiara de nombre
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                BufferedWriter bw = new BufferedWriter(new FileWriter(nuevo,true));
                String linea;
                while((linea = br.readLine()) != null) {
                    String[] lineaDivididaId = linea.split(" "); 
                    if(lineaDivididaId[0].equals(numId)) {   
                        ArrayList<String> s = newR.toArrayDeStrings();
                        Escribir(nuevo,linea);
                        String linea2 = null;
                        for (int i = 0; i < s.size(); ++i) Escribir(nuevo,s.get(i));
                        Escribir(nuevo,".");//para saber que finaliza el ranking ahi
                        boolean aux = false;
                        while (!aux &&(linea2 = br.readLine()) != null) { //para seguir en el siguiente problema
                            String[] lineaDivididaAux = linea2.split(" "); 
                            if (lineaDivididaAux[0].equals(".")) aux = true;
                        }
                        linea = linea2;
                    }   
                    else {
                        Escribir(nuevo,linea);
                    }    
                }  
                br.close(); 
                bw.close();
                borrar(archivo); //borro archivo anterior
                nuevo.renameTo(archivo);  //Renombro el archivo con el anterior. NO ME LO RENOMBRA!!
            }   
            else System.out.println("No existe el fichero");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private void borrar(File Ffichero){
        try {
            if(Ffichero.exists()){
                Ffichero.delete();
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    private void Escribir(File fFichero,String cadena)
    {
        // Declaramos un buffer de escritura
        BufferedWriter bw;
        try
        {
            // Comprobamos si el archivo no existe y si es asi creamos uno nuevo.
         if(!fFichero.exists())
         {
             fFichero.createNewFile();
         }
           // Luego de haber creado el archivo procedemos a escribir dentro de el.
            bw = new BufferedWriter(new FileWriter(fFichero,true));
            if (fFichero.length() == 0) bw.write(cadena);
            else {
                bw.newLine(); //salto de linea en fichero
                bw.write(cadena);
            }
            bw.close();

        }catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
