package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


/**
 *
 * @author Àlex
 */
public class CtrlDatosUsuarios {
    File archivo;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    String path = "UsuariosRegistrados.txt";
    
    public CtrlDatosUsuarios(){
        archivo = null;
        fr = null;
        br = null;
        abrirArchivo();
    }
    
    public void abrirArchivo() {
        archivo = new File(path);
        if (!archivo.exists()) try {
            archivo.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
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
    
    public void escribirUsuario(String nombre, String password) {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No hay ningun archivo abierto.");
        }
        try {
            fw = new FileWriter(archivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            if (archivo.length() == 0) out.write(nombre+" "+ password); //en caso de estar vacio...
            else {
                bw.newLine(); //salto de linea en fichero
                out.append(nombre +" "+ password);
            }
            out.close();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public boolean usuarioRegistrado(String nombre, String password)  {
        String cadena = nombre+" "+ password;
        boolean b = false;
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null) {
                   if(linea.equals(cadena)) b = true;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return b;
    }
    
    public void modificarPassword(String nombre, String password, String newPassword) {
        String cadenaCambiar = nombre+" "+password;
        String cadenaNueva = nombre+" "+newPassword;
        File nuevo = new File("random.txt"); //fichero auxiliar
        BufferedWriter bw;
        try {
            if (archivo.exists()) {
                br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null) {
                    if(linea.equals(cadenaCambiar)) Escribir(nuevo,cadenaNueva);
                    else Escribir(nuevo,linea);    
                }
                br.close(); 
                borrar(archivo); //borro archivo anterior
                nuevo.renameTo(archivo); //Renombro el archivo con el anterior
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
