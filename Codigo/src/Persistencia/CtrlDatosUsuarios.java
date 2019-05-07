package Persistencia;

import Dominio.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

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
    
    public CtrlDatosUsuarios() {
        archivo = null;
        fr = null;
        br = null;
    }
    
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
    
    public void anadirUsuario(Usuario u) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No hay ningun archivo abierto.");
        }
        fw = new FileWriter(archivo,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        if (archivo.length() == 0) out.write(u.getNombre()+" "+ u.getPassword()); //en caso de estar vacio...
        else {
            bw.newLine(); //salto de linea en fichero
            out.append(u.getNombre()+" "+ u.getPassword());
        }
        out.close();
        bw.close();
    }
    
    public void modificarPassword(Usuario u, String newPassword) throws IOException {
        String cadenaCambiar= u.getNombre()+" "+ u.getPassword();
        String cadenaNueva = u.getNombre()+" "+ newPassword;
        File nuevo = new File("random.txt"); //fichero auxiliar
        BufferedWriter bw;
        if (archivo.exists()) {
            br = new BufferedReader(new FileReader(archivo));
            String linea;
            while((linea = br.readLine()) != null) {
                if(linea.equals(cadenaCambiar)) Escribir(nuevo,cadenaNueva);
                else Escribir(nuevo,linea);
            }
            br.close(); 
            borrar(archivo); //borro archivo anterior
            nuevo.renameTo(archivo); //Renombro el arvhico con el anterior
        }
        else System.out.println("No existe el fichero");
    }
    
    private void borrar (File Ffichero){
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

        }catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
